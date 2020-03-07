package com.adelean.elasticsearch.word2vec.upload;

import com.adelean.elasticsearch.word2vec.IndexInitializer;
import com.adelean.elasticsearch.word2vec.model.ModelService;
import com.adelean.elasticsearch.word2vec.utils.ActionFutures;
import com.adelean.elasticsearch.word2vec.utils.ActionPromise;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.delete.DeleteRequestBuilder;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.PlainListenableActionFuture;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.Singleton;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.threadpool.ThreadPool;
import org.nd4j.shade.guava.collect.ImmutableMap;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static com.adelean.elasticsearch.word2vec.model.ModelService.MODEL_STORE_INDEX;

@Singleton
public final class UploadService {
    private static final Logger LOGGER = LogManager.getLogger(UploadService.class);

    public static final String PART_INDEX = ".word2vec_uploads_parts";

    private static final String ERR_UNKNOWN_UPLOAD = "Unknown upload %s";
    private static final TimeValue MIN_1 = new TimeValue(60000);

    private final Client client;
    private final ThreadPool threadPool;
    private final IndexInitializer indexInitializer;
    private final ModelService modelService;
    private final Map<UUID, String> uploads = new ConcurrentHashMap<>();

    @Inject
    public UploadService(
            Client client,
            ThreadPool threadPool,
            IndexInitializer indexInitializer,
            ModelService modelService) {
        this.client = client;
        this.threadPool = threadPool;
        this.indexInitializer = indexInitializer;
        this.modelService = modelService;
    }

    public ListenableActionFuture<UUID> startUpload(String model) {
        ListenableActionFuture<Void> ensureIndexExist = indexInitializer.ensureIndexExist(PART_INDEX);
        return ActionPromise
                .promise(ensureIndexExist)
                .mapResponse(ignored -> {
                    UUID uploadId = UUID.randomUUID();
                    uploads.put(uploadId, model);
                    LOGGER.info("Starting upload {} for model {}", uploadId, model);
                    return uploadId;
                })
                .toFuture();
    }

    public ListenableActionFuture<String> storePart(UUID uploadId, long partNumber, String dataBase64) {
        String model = uploads.get(uploadId);

        if (model == null) {
            return ActionFutures.failed(StorePartException.unknownUpload(uploadId));
        }

        String docId = uploadId.toString() + '-' + partNumber;
        ImmutableMap<String, Object> source = ImmutableMap.of(
                "uploadId", uploadId.toString(),
                "partNumber", partNumber,
                "data", dataBase64);

        PlainListenableActionFuture<String> future = PlainListenableActionFuture.newListenableFuture();
        client
                .prepareIndex()
                .setIndex(PART_INDEX)
                .setType("_doc")
                .setId(docId)
                .setSource(source)
                .execute(new ActionListener<IndexResponse>() {
                    @Override
                    public void onResponse(IndexResponse indexResponse) {
                        LOGGER.info("Stored part number {} for model {}; upload {}", partNumber, model, uploadId);
                        future.onResponse(model);
                    }

                    @Override
                    public void onFailure(Exception storePartFailure) {
                        String err = String.format("Failed to store part number %s for model %s; upload: %s",
                                partNumber, model, uploadId);
                        LOGGER.error(err, storePartFailure);
                        StorePartException storePartException = new StorePartException(err);
                        future.onFailure(storePartException);
                    }
                });

        return future;
    }

    public ListenableActionFuture<String> finishUpload(UUID uploadId) {
        String model = uploads.remove(uploadId);

        if (model == null) {
            return ActionFutures.failed(FinishUploadException.unknownUpload(uploadId));
        }

        PlainListenableActionFuture<String> future = PlainListenableActionFuture.newListenableFuture();

        LOGGER.info("Finished upload {} for model {}", uploadId, model);

        ActionPromise
                .promise(modelService.ensureModelIndexExist())
                .then(flushUploadPartsIndex())
                .then(ignored -> copyModelPartsAsync(model, uploadId))
                .then(deleteUploadPartsQuery(uploadId))
                .mapResponse((ignored) -> model)
                .then(future);

        return future;
    }

    /**
     * Forces write of documents from translog.
     */
    private DeleteRequestBuilder flushUploadPartsIndex() {
        return client
                .prepareDelete(PART_INDEX, "_doc", "fake-id")
                .setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
    }

    private DeleteByQueryRequestBuilder deleteUploadPartsQuery(UUID uploadId) {
        return DeleteByQueryAction
                .INSTANCE
                .newRequestBuilder(client)
                .source(PART_INDEX)
                .filter(QueryBuilders.matchQuery("uploadId", uploadId.toString()));
    }

    private ListenableActionFuture<Void> copyModelPartsAsync(String model, UUID uploadId) {
        PlainListenableActionFuture<Void> future = PlainListenableActionFuture.newListenableFuture();

        threadPool.generic().execute(() -> {
            this.copyModelParts(model, uploadId);
            future.onResponse(null);
        });

        return future;
    }

    private void copyModelParts(String model, UUID uploadId) {
        SearchResponse scrollResp = client
                .prepareSearch(PART_INDEX)
                .setQuery(QueryBuilders.termQuery("uploadId", uploadId.toString()))
                .addSort("partNumber", SortOrder.ASC)
                .setScroll(MIN_1)
                .setSize(100)
                .get();

        Long copiedParts = 0L;

        do {
            SearchHit[] hits = scrollResp.getHits().getHits();
            BulkRequestBuilder bulkRequest = client.prepareBulk();

            for (SearchHit hit : hits) {
                Map<String, Object> sourceMap = hit.getSourceAsMap();
                Long partNumber = ((Number) sourceMap.get("partNumber")).longValue();
                String data = (String) sourceMap.get("data");
                int size = originalLengthInBytes(data);

                IndexRequest indexRequest = new IndexRequest(MODEL_STORE_INDEX, "_doc", partNumber.toString())
                        .source(ImmutableMap.of(
                                "model", model,
                                "partNumber", partNumber,
                                "size", size,
                                "data", data));
                bulkRequest.add(indexRequest);
                copiedParts++;
            }

            bulkRequest.get();

            scrollResp = client
                    .prepareSearchScroll(scrollResp.getScrollId())
                    .setScroll(MIN_1)
                    .execute()
                    .actionGet();
        } while (scrollResp.getHits().getHits().length > 0);

        LOGGER.debug("Copied {} parts of model {}", copiedParts, model);
    }

    /**
     * @see <a href="https://blog.aaronlenoir.com/2017/11/10/get-original-length-from-base-64-string/">
     *     Get original length from a Base 64 string</a>
     */
    static int originalLengthInBytes(String dataBase64)
    {
        if (StringUtils.isEmpty(dataBase64)) {
            return 0;
        }

        int length = dataBase64.length();
        int paddingCount = 0;

        if (dataBase64.charAt(length - 1) == '=') {
            paddingCount++;
        }

        if (length > 1 && dataBase64.charAt(length - 2) == '=') {
            paddingCount++;
        }

        return (3 * (length / 4)) - paddingCount;
    }

    public static final class StorePartException extends Exception {
        private StorePartException(String message) {
            super(message);
        }

        private static StorePartException unknownUpload(UUID uploadId) {
            String err = String.format(ERR_UNKNOWN_UPLOAD, uploadId);
            return new StorePartException(err);
        }
    }

    public static final class FinishUploadException extends Exception {
        FinishUploadException(String message) {
            super(message);
        }

        private static FinishUploadException unknownUpload(UUID uploadId) {
            String err = String.format(ERR_UNKNOWN_UPLOAD, uploadId);
            return new FinishUploadException(err);
        }
    }
}

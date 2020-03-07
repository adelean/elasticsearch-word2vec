package com.adelean.elasticsearch.word2vec.model;

import com.adelean.elasticsearch.word2vec.IndexInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.support.PlainListenableActionFuture;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.Table;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.Singleton;
import org.elasticsearch.common.unit.ByteSizeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.sum.Sum;

import static com.adelean.elasticsearch.word2vec.utils.ActionPromise.doAction;

@Singleton
public final class ModelService {
    private static final Logger LOGGER = LogManager.getLogger(ModelService.class);

    public static final String MODEL_STORE_INDEX = ".word2vec_models_store";
    private static final String[] HEADERS = { "Model", "Status", "Parts", "Size" };

    private final Client client;
    private final IndexInitializer indexInitializer;

    @Inject
    public ModelService(Client client, IndexInitializer indexInitializer) {
        this.client = client;
        this.indexInitializer = indexInitializer;
    }

    public ListenableActionFuture<Table> modelTable() {
        PlainListenableActionFuture<Table> future = PlainListenableActionFuture.newListenableFuture();

        Table table = new Table();
        addHeaders(table);

        SearchRequestBuilder searchRequestBuilder = client
                .prepareSearch(MODEL_STORE_INDEX)
                .setSize(0)
                .addAggregation(AggregationBuilders
                        .terms("models")
                        .field("model")
                        .subAggregation(AggregationBuilders
                                .sum("size")
                                .field("size")));

        doAction(searchRequestBuilder)
                .mapResponse(searchResponse -> {
                    Terms modelsAgg = searchResponse.getAggregations().get("models");

                    for (Terms.Bucket entry : modelsAgg.getBuckets()) {
                        String model = entry.getKey().toString();
                        long parts = entry.getDocCount();
                        String status = status(model);
                        Sum sizeAgg = entry.getAggregations().get("size");
                        double size = sizeAgg.getValue();

                        table.startRow();
                        table.addCell(model);
                        table.addCell(status);
                        table.addCell(parts);
                        table.addCell(new ByteSizeValue(Double.valueOf(size).longValue()));
                        table.endRow();
                    }

                    return table;
                })
                .then(future);

        return future;
    }

    public void deleteModel(String model) {
        // TODO: code this method
    }

    public ListenableActionFuture<Void> ensureModelIndexExist() {
        return indexInitializer.ensureIndexExist(MODEL_STORE_INDEX);
    }

    public DeleteByQueryRequestBuilder deleteModelQuery(String model) {
        return DeleteByQueryAction
                .INSTANCE
                .newRequestBuilder(client)
                .source(MODEL_STORE_INDEX)
                .filter(QueryBuilders.matchQuery("model", model));
    }

    private static void addHeaders(Table table) {
        table.startHeaders();
        for (String header : HEADERS) {
            table.addCell(header);
        }
        table.endHeaders();
    }

    private String status(String model) {
        return ModelLoader.isLoaded(model) ? "loaded" : "unloaded";
    }
}

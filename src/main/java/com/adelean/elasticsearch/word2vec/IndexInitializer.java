package com.adelean.elasticsearch.word2vec;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.ClusterChangedEvent;
import org.elasticsearch.cluster.ClusterStateListener;
import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.adelean.elasticsearch.word2vec.model.ModelService.MODEL_STORE_INDEX;
import static com.adelean.elasticsearch.word2vec.upload.UploadService.PART_INDEX;

public final class IndexInitializer implements ClusterStateListener {
    public static final Logger LOGGER = LogManager.getLogger(IndexInitializer.class);

    private static final String INDEX_RESOURCES = "com/adelean/elasticsearch/word2vec/indices/";

    private final ClusterService clusterService;
    private final Client client;

    @Inject
    public IndexInitializer(ClusterService clusterService, Client client) {
        this.clusterService = clusterService;
        this.client = client;
        clusterService.addListener(this);
    }

    @Override
    public void clusterChanged(ClusterChangedEvent event) {
        clusterService.removeListener(this);    // unregister itself after first call
        ensureIndexExist(PART_INDEX);
        ensureIndexExist(MODEL_STORE_INDEX);
    }

    private void ensureIndexExist(String index) {
        IndicesExistsRequest indicesExistsRequest = new IndicesExistsRequest(index);
        client
                .admin()
                .indices()
                .exists(indicesExistsRequest, new ActionListener<IndicesExistsResponse>() {
                    @Override
                    public void onResponse(IndicesExistsResponse indicesExistsResponse) {
                        boolean indexExists = indicesExistsResponse.isExists();
                        if (!indexExists) {
                            String source = loadIndexSource(index);
                            createIndex(index, source);
                        }
                    }

                    @Override
                    public void onFailure(Exception failure) {
                        LOGGER.error("Cannot check existence of index {}", index);
                    }
                });
    }

    private String loadIndexSource(String index) {
        String indexResource = INDEX_RESOURCES +  index + ".index.json";

        try {
            URI uri = Objects.requireNonNull(IndexInitializer
                    .class
                    .getClassLoader()
                    .getResource(indexResource))
                    .toURI();
            return IOUtils.toString(uri, StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException unexpectedException) {
            LOGGER.error("Cannot load definition of index {} from resource {}", index, indexResource);
            throw new RuntimeException(unexpectedException);
        }
    }

    private void createIndex(String index, String source) {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(index)
                .source(source, XContentType.JSON);
        client.admin().indices().create(createIndexRequest);
    }
}

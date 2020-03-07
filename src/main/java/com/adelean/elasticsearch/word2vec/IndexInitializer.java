package com.adelean.elasticsearch.word2vec;

import com.adelean.elasticsearch.word2vec.utils.ActionPromise;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.Singleton;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Singleton
public final class IndexInitializer {
    public static final Logger LOGGER = LogManager.getLogger(IndexInitializer.class);

    private static final String INDEX_RESOURCES = "com/adelean/elasticsearch/word2vec/indices/";

    private final Client client;

    @Inject
    public IndexInitializer(Client client) {
        this.client = client;
    }

    public ListenableActionFuture<Void> ensureIndexExist(String index) {
        IndicesExistsRequestBuilder indexExistsRequest = client
                .admin()
                .indices()
                .prepareExists(index);

        return ActionPromise
                .doAction(indexExistsRequest)
                .then((IndicesExistsResponse indexExistsResponse, ActionListener<CreateIndexResponse> listener) -> {
                    boolean indexExists = indexExistsResponse.isExists();
                    if (!indexExists) {
                        String source = loadIndexSource(index);
                        createIndexRequest(index, source).execute(listener);
                    } else {
                        listener.onResponse(null);
                    }

                })
                .mapResponse(ignored -> (Void) null)
                .toFuture();
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

    private CreateIndexRequestBuilder createIndexRequest(String index, String source) {
        return client
                .admin()
                .indices()
                .prepareCreate(index)
                .setSource(source, XContentType.JSON);
    }
}

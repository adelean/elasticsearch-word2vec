package com.adelean.elasticsearch.word2vec.model;

import com.adelean.elasticsearch.word2vec.PrivilegedExecutor;
import com.adelean.elasticsearch.word2vec.utils.ScrollInputStream;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

import java.io.InputStream;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class ModelLoader {
    private static final String MODELS_INDEX = ".word2vec_models_store";
    private static Client client;
    private static final Map<String, WordVectorsPluginImpl> models = new ConcurrentHashMap<>();

    public ModelLoader(Client client) {
        ModelLoader.client = client;
    }

    public static boolean isLoaded(String model) {
        return models.containsKey(model);
    }

    public static WordVectorsPluginImpl getModel(String model) {
        return models.computeIfAbsent(model, ModelLoader::loadModel);
    }

    private static WordVectorsPluginImpl loadModel(String model) {
        InputStream inputStream = new ModelInputStream(model, client);
        return PrivilegedExecutor
                .getInstance()
                .execute(() -> new WordVectorsPluginImpl(
                        ModelReader.readAsBinaryNoLineBreaks(inputStream)));
    }

    private static final class ModelInputStream extends ScrollInputStream {
        public ModelInputStream(String model, Client client) {
            super(modelSearchRequest(model, client), client);
        }

        private static SearchRequestBuilder modelSearchRequest(String model, Client client) {
            return client
                    .prepareSearch(MODELS_INDEX)
                    .setQuery(QueryBuilders.termQuery("model", model))
                    .addSort("partNumber", SortOrder.ASC);
        }

        @Override
        protected byte[] extract(SearchHit searchHit) {
            String data = (String) searchHit.getSourceAsMap().get("data");
            return Base64.getDecoder().decode(data);
        }
    }
}

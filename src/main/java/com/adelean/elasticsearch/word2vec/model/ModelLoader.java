package com.adelean.elasticsearch.word2vec.model;

import static com.adelean.elasticsearch.word2vec.PrivilegedExecutor.executePrivileged;

import java.io.InputStream;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

import com.adelean.elasticsearch.word2vec.utils.ScrollInputStream;

public final class ModelLoader {
    private static final Logger LOGGER = LogManager.getLogger(ModelLoader.class);

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
        LOGGER.info("Loading model '{}'...", model);

        try (InputStream inputStream = new ModelInputStream(model, client)) {
            return executePrivileged(() -> {
                Word2Vec word2vec = WordVectorSerializer.readAsBinary(inputStream);
                return new WordVectorsPluginImpl(word2vec);
            });
        } catch (Exception readBinaryException) {
            try (InputStream inputStream = new ModelInputStream(model, client)) {
                return executePrivileged(() -> {
                    Word2Vec word2vec = WordVectorSerializer.readAsBinaryNoLineBreaks(inputStream);
                    return new WordVectorsPluginImpl(word2vec);
                });
            } catch (Exception readModelException) {
                throw new RuntimeException("Unable to guess input file format. Please use corresponding loader directly");
            }
        }
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

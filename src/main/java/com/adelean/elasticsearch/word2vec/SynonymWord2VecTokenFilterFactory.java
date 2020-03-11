package com.adelean.elasticsearch.word2vec;

import com.adelean.elasticsearch.word2vec.model.ModelLoader;
import com.adelean.elasticsearch.word2vec.model.WordVectorsPluginImpl;
import org.apache.lucene.analysis.TokenStream;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

import java.util.Objects;

public class SynonymWord2VecTokenFilterFactory extends AbstractTokenFilterFactory {
    private static final int DEFAULT_NEAREST_WORDS = 5;
    private static final int DEFAULT_NEAREST_WORDS_RCS = 10;
    private static final String ERR_MISSING_MODEL =
            "Token filter %s of type synonym_word2vec missing mandatory setting 'model'";

    private final String model;
    private final int nearestWords;
    private final Double threshold;     // 0 <= threshold <= 1
    private final boolean rcs;          // relative cosine similarity

    public SynonymWord2VecTokenFilterFactory(
            IndexSettings indexSettings,
            Environment environment,
            String name,
            Settings settings) {
        super(indexSettings, name, settings);
        this.model = settings.get("model");
        this.rcs = settings.getAsBoolean("rcs", false);
        this.nearestWords = settings.getAsInt("nearest_words",
                rcs ? DEFAULT_NEAREST_WORDS_RCS : DEFAULT_NEAREST_WORDS);
        this.threshold = settings.getAsDouble("threshold",
                rcs ? (1.0 / nearestWords) : null);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        Objects.requireNonNull(model, String.format(ERR_MISSING_MODEL, name()));
        WordVectorsPluginImpl w2vModel = ModelLoader.getModel(model);

        if (rcs) {
            return new Word2vecTokenFilter.ByRelativeCosineSimilarity(tokenStream, w2vModel, nearestWords, threshold);
        }

        return threshold != null
                ? new Word2vecTokenFilter.ByThreshold(tokenStream, w2vModel, threshold)
                : new Word2vecTokenFilter.NearestWords(tokenStream, w2vModel, nearestWords);
    }
}

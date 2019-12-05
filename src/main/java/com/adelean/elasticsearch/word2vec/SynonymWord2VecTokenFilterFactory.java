package com.adelean.elasticsearch.word2vec;

import com.adelean.elasticsearch.word2vec.model.ModelLoader;
import org.apache.lucene.analysis.TokenStream;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.env.Environment;
import org.elasticsearch.index.IndexSettings;
import org.elasticsearch.index.analysis.AbstractTokenFilterFactory;

import java.util.Objects;

public class SynonymWord2VecTokenFilterFactory extends AbstractTokenFilterFactory {
    private static final int DEFAULT_NEAREST_WORDS = 5;
    private static final String ERR_MISSING_MODEL =
            "Token filter %s of type synonym_word2vec missing mandatory setting 'model'";

    private final String model;
    private final int nearestWords;

    public SynonymWord2VecTokenFilterFactory(
            IndexSettings indexSettings,
            Environment environment,
            String name,
            Settings settings) {
        super(indexSettings, name, settings);
        this.model = settings.get("model");
        this.nearestWords = settings.getAsInt("nearest_words", DEFAULT_NEAREST_WORDS);
    }

    @Override
    public TokenStream create(TokenStream tokenStream) {
        Objects.requireNonNull(model, String.format(ERR_MISSING_MODEL, name()));
        Word2Vec w2vModel = ModelLoader.getModel(model);
        return new Word2vecTokenFilter(tokenStream, w2vModel, nearestWords);
    }
}

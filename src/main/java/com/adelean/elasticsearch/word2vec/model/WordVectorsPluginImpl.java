package com.adelean.elasticsearch.word2vec.model;

import static com.adelean.elasticsearch.word2vec.PrivilegedExecutor.executePrivileged;

import org.deeplearning4j.models.embeddings.reader.impl.BasicModelUtils;
import org.deeplearning4j.models.embeddings.reader.impl.PluginModelUtils;
import org.deeplearning4j.models.embeddings.wordvectors.WordVectorsImpl;
import org.deeplearning4j.models.word2vec.VocabWord;

import java.util.Collection;
import java.util.Collections;

/**
 * Extension of {@link WordVectorsImpl} that adds methods to find nearest words by threshold.
 */
public final class WordVectorsPluginImpl extends WordVectorsImpl<VocabWord> {
    private final WordVectorsImpl<VocabWord> baseModel;
    private final PluginModelUtils<VocabWord> modelUtils;

    public WordVectorsPluginImpl(WordVectorsImpl<VocabWord> baseModel) {
        this.baseModel = baseModel;
        this.modelUtils = new PluginModelUtils<>((BasicModelUtils<VocabWord>) baseModel.getModelUtils());
    }

    @Override
    public Collection<String> wordsNearest(String word, int n) {
        return executePrivileged(() -> baseModel.wordsNearest(word, n));
    }

    @Override
    public double similarity(String word, String word2) {
        return executePrivileged(() -> baseModel.similarity(word, word2));
    }

    public Collection<String> wordsNearestThreshold(String word, double threshold) {
        return executePrivileged(() -> modelUtils.wordsNearest(
                Collections.singleton(word), Collections.emptyList(), threshold));
    }
}

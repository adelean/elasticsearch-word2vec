package com.adelean.elasticsearch.word2vec;

import com.adelean.elasticsearch.word2vec.model.WordVectorsPluginImpl;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class Word2vecTokenFilter extends TokenFilter {
    protected final WordVectorsPluginImpl model;

    private final CharTermAttribute termAttr = addAttribute(CharTermAttribute.class);
    private final PositionIncrementAttribute posIncAtt = addAttribute(PositionIncrementAttribute.class);
    private AttributeSource.State current;
    private final Queue<String> synonyms = new LinkedList<>();

    Word2vecTokenFilter(TokenStream input, WordVectorsPluginImpl model) {
        super(input);
        this.model = model;
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (synonyms.isEmpty()) {
            if (!input.incrementToken()) {
                return false;
            }

            Collection<String> syn = synonyms(termAttr.toString());
            synonyms.addAll(syn);

            current = captureState();

            return true;
        } else {
            restoreState(current);

            String synonym = synonyms.poll();
            termAttr.setEmpty().append(synonym);
            posIncAtt.setPositionIncrement(0);

            return true;
        }
    }

    protected abstract List<String> synonyms(String word);

    public static final class NearestWords extends Word2vecTokenFilter {
        private final int nearestWords;

        public NearestWords(TokenStream input, WordVectorsPluginImpl model, int nearestWords) {
            super(input, model);
            this.nearestWords = nearestWords;
        }

        @Override
        protected List<String> synonyms(String word) {
            return Collections.unmodifiableList(
                    new ArrayList<>(
                            model.wordsNearest(word, nearestWords)));
        }
    }

    public static final class ByThreshold extends Word2vecTokenFilter {
        private final double threshold;

        public ByThreshold(TokenStream input, WordVectorsPluginImpl model, double threshold) {
            super(input, model);
            this.threshold = threshold;
        }

        @Override
        protected List<String> synonyms(String word) {
            return Collections.unmodifiableList(
                    new ArrayList<>(
                            model.wordsNearestThreshold(word, threshold)));
        }
    }
}

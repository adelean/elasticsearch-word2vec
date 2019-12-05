package com.adelean.elasticsearch.word2vec;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.util.AttributeSource;
import org.deeplearning4j.models.word2vec.Word2Vec;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public final class Word2vecTokenFilter extends TokenFilter {
    private final CharTermAttribute termAttr = addAttribute(CharTermAttribute.class);
    private final PositionIncrementAttribute posIncAtt = addAttribute(PositionIncrementAttribute.class);
    private AttributeSource.State current;
    private final Word2Vec model;
    private final int nearestWords;
    private final Queue<String> synonyms = new LinkedList<>();

    Word2vecTokenFilter(TokenStream input, Word2Vec model, int nearestWords) {
        super(input);
        this.model = model;
        this.nearestWords = nearestWords;
    }

    @Override
    public boolean incrementToken() throws IOException {
        if (synonyms.isEmpty()) {
            if (!input.incrementToken()) {
                return false;
            }

            Collection<String> syn = model.wordsNearest(termAttr.toString(), nearestWords);
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
}

package com.adelean.elasticsearch.word2vec.model;

import static com.adelean.inject.resources.core.InjectResources.resource;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WordVectorsPluginImplTest {
    private static final Word2Vec testModel = resource()
            .withPath("models/word2vec.c.output.model.bin")
            .asInputStream()
            .parse(WordVectorSerializer::readAsBinaryNoLineBreaks);

    @Test
    @DisplayName("Test get top N nearest words")
    public void testWordsNearestByTopN() {

        /* Given */
        WordVectorsPluginImpl model = new WordVectorsPluginImpl(testModel);

        /* When */
        Collection<String> nearestWords = model.wordsNearest("hot", 5);

        /* Then */
        assertThat(nearestWords)
                .isNotNull()
                .isNotEmpty()
                .hasSize(5)
                .containsExactly("dry", "wet", "steel", "rockets", "water");
    }

    @Test
    @DisplayName("Test get nearest words by threshold")
    public void testWordsNearestByThreshold() {

        /* Given */
        WordVectorsPluginImpl model = new WordVectorsPluginImpl(testModel);

        /* When */
        Collection<String> nearestWords = model.wordsNearestThreshold("hot", 0.94);

        /* Then */
        assertThat(nearestWords)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .containsExactly("dry", "wet");
    }
}

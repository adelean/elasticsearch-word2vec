package com.adelean.elasticsearch.word2vec.model;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializerPluginImpl;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.elasticsearch.common.io.PathUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class WordVectorsPluginImplTest {
    private final Word2Vec TEST_MODEL = testModel();

    @Test
    @DisplayName("Test get top N nearest words")
    public void testWordsNearestByTopN() {

        /* Given */
        WordVectorsPluginImpl model = new WordVectorsPluginImpl(TEST_MODEL);

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
        WordVectorsPluginImpl model = new WordVectorsPluginImpl(TEST_MODEL);

        /* When */
        Collection<String> nearestWords = model.wordsNearestThreshold("hot", 0.94);

        /* Then */
        assertThat(nearestWords)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .containsExactly("dry", "wet");
    }

    private static Word2Vec testModel() {
        try {
            URI uri = ClassLoader
                    .getSystemClassLoader()
                    .getResource("models/word2vec.c.output.model.bin")
                    .toURI();
            Path path = PathUtils.get(uri);
            try (InputStream fileInputStream = Files.newInputStream(path)) {
                return WordVectorSerializerPluginImpl.readAsBinaryNoLineBreaks(fileInputStream);
            }
        } catch (Exception modelLoadingException) {
            fail(modelLoadingException.getMessage());
            return null;
        }
    }
}

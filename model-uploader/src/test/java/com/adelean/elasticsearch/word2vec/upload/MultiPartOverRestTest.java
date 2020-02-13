package com.adelean.elasticsearch.word2vec.upload;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiPartOverRestTest {

    @Test
    @DisplayName("Test model name from file")
    public void testModelNameFromFile () {

        /* Given */
        Path modelFile = Paths.get("/path/to/models/my-model.bin");

        /* When */
        String model = MultiPartOverRest.modelNameFromFile(modelFile);

        /* Then */
        assertThat(model)
                .isEqualTo("my-model");
    }
}

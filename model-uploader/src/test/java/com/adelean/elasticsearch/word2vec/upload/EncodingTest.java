package com.adelean.elasticsearch.word2vec.upload;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class EncodingTest {
    private static final Path modelFile = Paths.get("/home/hosuaby/projects/doc2vec/model/model-tot.bin");

    @Test
    public void testBase64Encoding () {
        ByteBuffer buffer = ByteBuffer.allocate(3072);
        int parts = 0;

        try (FileChannel fileChannel = FileChannel.open(modelFile)) {
            while (fileChannel.read(buffer) > 0) {
                byte[] rawData = buffer.array();
                String encoded = Base64.getEncoder().encodeToString(rawData);
                byte[] decoded = Base64.getDecoder().decode(encoded);

                assertThat(decoded)
                        .isEqualTo(rawData);
                parts++;

                buffer.clear();
            }
        } catch (IOException fileReadException) {
            fail(fileReadException.getMessage());
        }

        System.out.println("Parts " + parts);
    }
}

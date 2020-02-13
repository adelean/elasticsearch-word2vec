package com.adelean.elasticsearch.word2vec.upload;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class ByteSizeUtilsTest {

    @DisplayName("Test formatSize")
    @ParameterizedTest(name = "{0} bytes must be formatted as {1}")
    @CsvSource({ "505, 505 B", "1024, 1.0 KiB", "2864968, 2.7 MiB", "3644258522, 3.4 GiB" })
    public void testFormatSize (long sizeInBytes, String expected) {
        String format = ByteSizeUtils.humanReadableByteCountBin(sizeInBytes);
        assertThat(format)
                .isEqualTo(expected);
    }
}

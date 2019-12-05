package com.adelean.elasticsearch.word2vec.upload;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Client;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public final class MultiPartOverRest {
//    private static final int HTTP_MAX_CONTENT_LENGTH = 100 * 1000000;     // 100 Mb
    private static final int HTTP_MAX_CONTENT_LENGTH = 3072;
    private static final String JSON_TEMPLATE = "{\"d\":\"%s\"}";
    private static final int MAX_PAYLOAD_LENGTH = HTTP_MAX_CONTENT_LENGTH - JSON_TEMPLATE
            .substring(2)
            .getBytes(StandardCharsets.UTF_8)
            .length;

    public static void main(String[] args) {
        String fileName = args[0];
        Path path = Paths.get(fileName);

        ByteBuffer buffer = ByteBuffer.allocate(HTTP_MAX_CONTENT_LENGTH);

        Client client = ResteasyClientBuilder.newClient();
        ResteasyWebTarget target = (ResteasyWebTarget) client.target("http://localhost:9200");
        UploadApi uploadApi = target.proxy(UploadApi.class);

        UploadApi.StartUploadResponse startUploadResponse = uploadApi.startUpload("model-tot");
        UUID uploadId = startUploadResponse.getUploadId();
        long partNumber = 0;

        try (FileChannel fileChannel = FileChannel.open(path)) {
            while (fileChannel.read(buffer) > 0) {
                byte[] bytes = buffer.array();
                UploadApi.Part part = new UploadApi.Part(bytes);
                uploadApi.storePart(uploadId, partNumber++, part);
                buffer.clear();
            }
        } catch (IOException fileReadException) {
            fileReadException.printStackTrace();
        }

        uploadApi.finishUpload(uploadId);
    }
}

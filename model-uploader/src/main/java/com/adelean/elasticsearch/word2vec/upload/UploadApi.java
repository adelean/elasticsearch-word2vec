package com.adelean.elasticsearch.word2vec.upload;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Base64;
import java.util.UUID;

@Path("/word2vec-models/upload")
public interface UploadApi {

    @POST
    @Path("/start/{model}")
    @Produces(MediaType.APPLICATION_JSON)
    StartUploadResponse startUpload(@PathParam("model") String model);

    @POST
    @Path("/part/{uploadId}/{partNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    StorePartResponse storePart(
            @PathParam("uploadId") UUID uploadId,
            @PathParam("partNumber") long partNumber,
            Part part);

    @POST
    @Path("finish/{uploadId}")
    @Produces(MediaType.APPLICATION_JSON)
    Response finishUpload(@PathParam("uploadId") UUID uploadId);

    @XmlRootElement
    class Part {
        private final byte[] data;

        public Part(byte[] data) {
            this.data = data;
        }

        @XmlElement
        public String d() {
            return Base64.getEncoder().encodeToString(data);
        }
    }

    @XmlRootElement
    class StartUploadResponse {
        @XmlElement
        private UUID uploadId;

        public StartUploadResponse() {
        }

        public StartUploadResponse(UUID uploadId) {
            this.uploadId = uploadId;
        }

        public UUID getUploadId() {
            return uploadId;
        }
    }

    @XmlRootElement
    class StorePartResponse {
        @XmlElement
        private String model;

        public StorePartResponse() {
        }

        public StorePartResponse(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }
    }
}

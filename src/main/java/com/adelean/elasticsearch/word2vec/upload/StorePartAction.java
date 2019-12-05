package com.adelean.elasticsearch.word2vec.upload;

import org.elasticsearch.action.Action;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.common.xcontent.ToXContentObject;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;
import java.util.UUID;

import static org.elasticsearch.action.ValidateActions.addValidationError;

public final class StorePartAction extends Action<
        StorePartAction.StorePartActionRequest,
        StorePartAction.StorePartActionResponse,
        StorePartAction.StorePartActionRequest.Builder> {
    public static final String NAME = "cluster:admin/word2vec/upload/part";
    public static final StorePartAction INSTANCE = new StorePartAction();

    protected StorePartAction() {
        super(NAME);
    }

    @Override
    public StorePartActionRequest.Builder newRequestBuilder(ElasticsearchClient client) {
        return new StorePartActionRequest.Builder(client);
    }

    @Override
    public StorePartActionResponse newResponse() {
        return new StorePartActionResponse();
    }

    public static final class StorePartActionRequest extends ActionRequest {
        private UUID uploadId;
        private Long partNumber;
        private String data;

        public StorePartActionRequest() {
        }

        public StorePartActionRequest(UUID uploadId, Long partNumber, String data) {
            this.uploadId = uploadId;
            this.partNumber = partNumber;
            this.data = data;
        }

        public UUID getUploadId() {
            return uploadId;
        }

        public void setUploadId(UUID uploadId) {
            this.uploadId = uploadId;
        }

        public Long getPartNumber() {
            return partNumber;
        }

        public void setPartNumber(Long partNumber) {
            this.partNumber = partNumber;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        @Override
        public ActionRequestValidationException validate() {
            ActionRequestValidationException arve = null;
            if (uploadId == null) {
                arve = addValidationError("'uploadId' must be set", null);
            }
            if (partNumber == null) {
                arve = addValidationError("'partNumber' must be set", null);
            }
            if (data == null) {
                arve = addValidationError("'data' must be set", null);
            }
            return arve;
        }

        static final class Builder extends ActionRequestBuilder<StorePartActionRequest, StorePartActionResponse, Builder> {
            protected Builder(ElasticsearchClient client) {
                super(client, INSTANCE, new StorePartActionRequest());
            }
        }
    }

    public static final class StorePartActionResponse extends ActionResponse implements ToXContentObject {
        String model;

        public StorePartActionResponse() {
        }

        public StorePartActionResponse(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        @Override
        public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
            return builder
                    .startObject()
                    .field("model", model)
                    .endObject();
        }
    }
}

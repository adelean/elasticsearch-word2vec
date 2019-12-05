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

public final class FinishUploadAction extends Action<
        FinishUploadAction.FinishUploadActionRequest,
        FinishUploadAction.FinishUploadActionResponse,
        FinishUploadAction.FinishUploadActionRequest.Builder> {
    public static final String NAME = "cluster:admin/word2vec/upload/finish";
    public static final FinishUploadAction INSTANCE = new FinishUploadAction();

    protected FinishUploadAction() {
        super(NAME);
    }

    @Override
    public FinishUploadActionRequest.Builder newRequestBuilder(ElasticsearchClient client) {
        return new FinishUploadActionRequest.Builder(client);
    }

    @Override
    public FinishUploadActionResponse newResponse() {
        return new FinishUploadActionResponse();
    }

    public static final class FinishUploadActionRequest extends ActionRequest {
        private UUID uploadId;

        public FinishUploadActionRequest() {
        }

        public FinishUploadActionRequest(UUID uploadId) {
            this.uploadId = uploadId;
        }

        public UUID getUploadId() {
            return uploadId;
        }

        public void setUploadId(UUID uploadId) {
            this.uploadId = uploadId;
        }

        @Override
        public ActionRequestValidationException validate() {
            ActionRequestValidationException arve = null;
            if (uploadId == null) {
                arve = addValidationError("'uploadId' must be set", null);
            }
            return arve;
        }

        static final class Builder extends ActionRequestBuilder<FinishUploadActionRequest, FinishUploadActionResponse, Builder> {
            Builder(ElasticsearchClient client) {
                super(client, INSTANCE, new FinishUploadActionRequest());
            }
        }
    }

    public static final class FinishUploadActionResponse extends ActionResponse implements ToXContentObject {
        String model;

        public FinishUploadActionResponse() {
        }

        public FinishUploadActionResponse(String model) {
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

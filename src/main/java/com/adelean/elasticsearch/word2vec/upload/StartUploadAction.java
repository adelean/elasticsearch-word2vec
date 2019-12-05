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

public final class StartUploadAction extends Action<
        StartUploadAction.StartUploadActionRequest,
        StartUploadAction.StartUploadActionResponse,
        StartUploadAction.StartUploadActionRequest.Builder> {
    public static final String NAME = "cluster:admin/word2vec/upload/start";
    public static final StartUploadAction INSTANCE = new StartUploadAction();

    protected StartUploadAction() {
        super(NAME);
    }

    @Override
    public StartUploadActionRequest.Builder newRequestBuilder(ElasticsearchClient client) {
        return new StartUploadActionRequest.Builder(client);
    }

    @Override
    public StartUploadActionResponse newResponse() {
        return new StartUploadActionResponse();
    }

    public static final class StartUploadActionRequest extends ActionRequest {
        private String model;

        public StartUploadActionRequest() {
        }

        public StartUploadActionRequest(String model) {
            this.model = model;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        @Override
        public ActionRequestValidationException validate() {
            ActionRequestValidationException arve = null;
            if (model == null) {
                arve = addValidationError("'model' must be set", null);
            }
            return arve;
        }

        static final class Builder extends ActionRequestBuilder<StartUploadActionRequest, StartUploadActionResponse, Builder> {
            Builder(ElasticsearchClient client) {
                super(client, INSTANCE, new StartUploadActionRequest());
            }
        }
    }

    public static final class StartUploadActionResponse extends ActionResponse implements ToXContentObject {
        private UUID uploadId;

        public StartUploadActionResponse() {
        }

        public StartUploadActionResponse(UUID uploadId) {
            this.uploadId = uploadId;
        }

        @Override
        public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
            return builder
                    .startObject()
                    .field("uploadId", uploadId.toString())
                    .endObject();
        }
    }
}

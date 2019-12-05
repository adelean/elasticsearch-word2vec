package com.adelean.elasticsearch.word2vec.model;

import org.elasticsearch.action.Action;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.ActionRequestValidationException;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.client.ElasticsearchClient;
import org.elasticsearch.common.Table;

public final class ModelListAction extends Action<
        ModelListAction.ModelListActionRequest,
        ModelListAction.ModelListActionResponse,
        ModelListAction.ModelListActionRequest.Builder> {
    public static final String NAME = "cluster:admin/word2vec/models/list";
    public static final ModelListAction INSTANCE = new ModelListAction();

    protected ModelListAction() {
        super(NAME);
    }

    @Override
    public ModelListActionRequest.Builder newRequestBuilder(ElasticsearchClient client) {
        return new ModelListActionRequest.Builder(client);
    }

    @Override
    public ModelListActionResponse newResponse() {
        return new ModelListActionResponse();
    }

    public static final class ModelListActionRequest extends ActionRequest {
        @Override
        public ActionRequestValidationException validate() {
            return null;
        }

        static final class Builder extends ActionRequestBuilder<ModelListActionRequest, ModelListActionResponse, Builder> {
            Builder(ElasticsearchClient client) {
                super(client, INSTANCE, new ModelListActionRequest());
            }
        }
    }

    public static final class ModelListActionResponse extends ActionResponse {
        private Table modelsTable;

        public ModelListActionResponse() {
        }

        public ModelListActionResponse(Table modelsTable) {
            this.modelsTable = modelsTable;
        }

        public Table getModelsTable() {
            return modelsTable;
        }

        public void setModelsTable(Table modelsTable) {
            this.modelsTable = modelsTable;
        }
    }
}

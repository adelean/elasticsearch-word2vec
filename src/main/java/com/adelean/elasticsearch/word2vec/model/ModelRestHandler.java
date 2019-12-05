package com.adelean.elasticsearch.word2vec.model;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.Table;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestResponse;
import org.elasticsearch.rest.action.RestResponseListener;
import org.elasticsearch.rest.action.cat.RestTable;

public final class ModelRestHandler extends BaseRestHandler {
    private static final String PREFIX = "word2vec-models";

    public ModelRestHandler(RestController restController, Settings settings) {
        super(settings);
        restController.registerHandler(RestRequest.Method.GET, PREFIX + "/list", this);
    }

    @Override
    public String getName() {
        return "Manage word2vec models";
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) {
        String path = request.path();
        String command = command(path);

        switch (command) {
            // TODO code "delete"
            case "list":
            default:
                return listModels(client);
        }
    }

    private RestChannelConsumer listModels(NodeClient client) {
        return channel -> client.execute(
                ModelListAction.INSTANCE,
                new ModelListAction.ModelListActionRequest(),
                new RestResponseListener<ModelListAction.ModelListActionResponse>(channel) {
                    @Override
                    public RestResponse buildResponse(ModelListAction.ModelListActionResponse modelListActionResponse) throws Exception {
                        Table table = modelListActionResponse.getModelsTable();
                        return RestTable.buildResponse(table, channel);
                    }
                });
    }

    private String command(String path) {
        return path.split("/")[2];
    }
}

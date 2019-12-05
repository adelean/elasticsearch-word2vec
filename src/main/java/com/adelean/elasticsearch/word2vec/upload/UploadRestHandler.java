package com.adelean.elasticsearch.word2vec.upload;

import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.ParseField;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.ObjectParser;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.rest.action.RestToXContentListener;

import java.io.IOException;
import java.util.UUID;

public final class UploadRestHandler extends BaseRestHandler {
    private static final String[] PREFIX = { "word2vec-models", "upload" };

    public static final ObjectParser<StorePartAction.StorePartActionRequest, Void> PARSER = new ObjectParser<>(StorePartAction.NAME);
    static {
        PARSER.declareString(StorePartAction.StorePartActionRequest::setData, new ParseField("d"));
    }

    public UploadRestHandler(RestController restController, Settings settings) {
        super(settings);
        String prefix = '/' + String.join("/", PREFIX) + '/';
        restController.registerHandler(RestRequest.Method.POST, prefix + "start/{model}", this);
        restController.registerHandler(RestRequest.Method.POST, prefix + "finish/{uploadId}", this);
        restController.registerHandler(RestRequest.Method.POST, prefix + "part/{uploadId}/{partNumber}", this);
    }

    @Override
    public String getName() {
        return "Upload word2vec model";
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
        String path = request.path();
        String command = command(path);

        switch (command) {
            case "start":
                return startUpload(request, client);
            case "finish":
                return finishUpload(request, client);
            case "part":
            default:
               return storePart(request, client);
        }
    }

    private RestChannelConsumer startUpload(RestRequest request, NodeClient client) {
        String model = request.param("model");
        StartUploadAction.StartUploadActionRequest actionRequest =
                new StartUploadAction.StartUploadActionRequest(model);

        return channel -> client.execute(
                StartUploadAction.INSTANCE,
                actionRequest,
                new RestToXContentListener<StartUploadAction.StartUploadActionResponse>(channel) {
                    @Override
                    protected RestStatus getStatus(StartUploadAction.StartUploadActionResponse startUploadActionResponse) {
                        return RestStatus.CREATED;
                    }
                });
    }

    private RestChannelConsumer storePart(RestRequest request, NodeClient client) throws IOException {
        UUID uploadId = UUID.fromString(request.param("uploadId"));
        long partNumber = request.paramAsLong("partNumber", 0L);

        StorePartAction.StorePartActionRequest storePartRequest = new StorePartAction.StorePartActionRequest();
        storePartRequest.setUploadId(uploadId);
        storePartRequest.setPartNumber(partNumber);

        PARSER.parse(request.contentParser(), storePartRequest, null);

        return channel -> client.execute(
                StorePartAction.INSTANCE,
                storePartRequest,
                new RestToXContentListener<StorePartAction.StorePartActionResponse>(channel) {
                    @Override
                    protected RestStatus getStatus(StorePartAction.StorePartActionResponse storePartActionResponse) {
                        return RestStatus.ACCEPTED;
                    }
                });
    }

    private RestChannelConsumer finishUpload(RestRequest request, NodeClient client) {
        UUID uploadId = UUID.fromString(request.param("uploadId"));

        FinishUploadAction.FinishUploadActionRequest finishUploadRequest =
                new FinishUploadAction.FinishUploadActionRequest(uploadId);

        return channel -> client.execute(
                FinishUploadAction.INSTANCE,
                finishUploadRequest,
                new RestToXContentListener<FinishUploadAction.FinishUploadActionResponse>(channel) {
                    @Override
                    protected RestStatus getStatus(FinishUploadAction.FinishUploadActionResponse storePartActionResponse) {
                        return RestStatus.OK;
                    }
                });
    }

    private String command(String path) {
        return path.split("/")[3];
    }
}

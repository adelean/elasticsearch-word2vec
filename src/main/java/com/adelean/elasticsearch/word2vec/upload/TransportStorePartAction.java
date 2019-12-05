package com.adelean.elasticsearch.word2vec.upload;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.HandledTransportAction;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import java.util.UUID;

import static com.adelean.elasticsearch.word2vec.utils.ActionPromise.promise;

public final class TransportStorePartAction extends HandledTransportAction<
        StorePartAction.StorePartActionRequest, StorePartAction.StorePartActionResponse> {
    private final UploadService uploadService;

    @Inject
    public TransportStorePartAction(
            Settings settings,
            ThreadPool threadPool,
            TransportService transportService,
            ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver,
            UploadService uploadService) {
        super(
                settings,
                StorePartAction.NAME,
                threadPool,
                transportService,
                actionFilters,
                indexNameExpressionResolver,
                StorePartAction.StorePartActionRequest::new);
        this.uploadService = uploadService;
    }

    @Override
    protected void doExecute(
            StorePartAction.StorePartActionRequest request,
            ActionListener<StorePartAction.StorePartActionResponse> listener) {
        UUID uploadId = request.getUploadId();
        Long partNumber = request.getPartNumber();
        String data = request.getData();

        promise(uploadService.storePart(uploadId, partNumber, data))
                .mapResponse(StorePartAction.StorePartActionResponse::new)
                .then(listener);
    }
}

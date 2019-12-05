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

public final class TransportFinishUploadAction extends HandledTransportAction<
        FinishUploadAction.FinishUploadActionRequest, FinishUploadAction.FinishUploadActionResponse> {
    private final UploadService uploadService;

    @Inject
    public TransportFinishUploadAction(
            Settings settings,
            ThreadPool threadPool,
            TransportService transportService,
            ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver,
            UploadService uploadService) {
        super(
                settings,
                FinishUploadAction.NAME,
                threadPool,
                transportService,
                actionFilters,
                indexNameExpressionResolver,
                FinishUploadAction.FinishUploadActionRequest::new);
        this.uploadService = uploadService;
    }

    @Override
    protected void doExecute(
            FinishUploadAction.FinishUploadActionRequest request,
            ActionListener<FinishUploadAction.FinishUploadActionResponse> listener) {
        UUID uploadId = request.getUploadId();

        promise(uploadService.finishUpload(uploadId))
                .mapResponse(FinishUploadAction.FinishUploadActionResponse::new)
                .then(listener);
    }
}

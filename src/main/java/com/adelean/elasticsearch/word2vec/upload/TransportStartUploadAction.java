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

public final class TransportStartUploadAction extends HandledTransportAction<
        StartUploadAction.StartUploadActionRequest, StartUploadAction.StartUploadActionResponse> {
    private final UploadService uploadService;

    @Inject
    public TransportStartUploadAction(
            Settings settings,
            ThreadPool threadPool,
            TransportService transportService,
            ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver,
            UploadService uploadService) {
        super(
                settings,
                StartUploadAction.NAME,
                threadPool,
                transportService,
                actionFilters,
                indexNameExpressionResolver,
                StartUploadAction.StartUploadActionRequest::new);
        this.uploadService = uploadService;
    }

    @Override
    protected void doExecute(
            StartUploadAction.StartUploadActionRequest request,
            ActionListener<StartUploadAction.StartUploadActionResponse> listener) {
        String model = request.getModel();
        UUID uploadId = uploadService.startUpload(model);
        StartUploadAction.StartUploadActionResponse response = new StartUploadAction.StartUploadActionResponse(uploadId);
        listener.onResponse(response);
    }
}

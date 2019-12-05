package com.adelean.elasticsearch.word2vec.model;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.HandledTransportAction;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

import static com.adelean.elasticsearch.word2vec.utils.ActionPromise.promise;

public final class TransportModelListAction extends HandledTransportAction<
        ModelListAction.ModelListActionRequest, ModelListAction.ModelListActionResponse> {
    private final ModelService modelService;

    @Inject
    public TransportModelListAction(
            Settings settings,
            ThreadPool threadPool,
            TransportService transportService,
            ActionFilters actionFilters,
            IndexNameExpressionResolver indexNameExpressionResolver,
            ModelService modelService) {
        super(
                settings,
                ModelListAction.NAME,
                threadPool,
                transportService,
                actionFilters,
                indexNameExpressionResolver,
                ModelListAction.ModelListActionRequest::new);
        this.modelService = modelService;
    }

    @Override
    protected void doExecute(
            ModelListAction.ModelListActionRequest request,
            ActionListener<ModelListAction.ModelListActionResponse> listener) {
        promise(modelService.modelTable())
                .mapResponse(ModelListAction.ModelListActionResponse::new)
                .then(listener);
    }
}

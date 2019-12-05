package com.adelean.elasticsearch.word2vec.utils;

import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.support.PlainListenableActionFuture;

public final class ActionFutures {
    private ActionFutures() {
    }

    public static <T> ListenableActionFuture<T> failed(Exception exception) {
        PlainListenableActionFuture<T> future = PlainListenableActionFuture.newListenableFuture();
        future.onFailure(exception);
        return future;
    }
}

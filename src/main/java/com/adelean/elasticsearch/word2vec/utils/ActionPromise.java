package com.adelean.elasticsearch.word2vec.utils;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.ListenableActionFuture;
import org.elasticsearch.action.support.PlainListenableActionFuture;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

// TODO: try more lightweight future implementation
public class ActionPromise<T> {
    private final ListenableActionFuture<T> listenableActionFuture;

    private ActionPromise(ListenableActionFuture<T> listenableActionFuture) {
        this.listenableActionFuture = listenableActionFuture;
    }

    public static <R> ActionPromise<R> promise(ListenableActionFuture<R> asyncActionCall) {
        return new ActionPromise<>(asyncActionCall);
    }

    public static <R extends ActionResponse> ActionPromise<R> doAction(ActionRequestBuilder<?, R, ?> actionRequestBuilder) {
        PlainListenableActionFuture<R> listenableActionFuture = PlainListenableActionFuture.newListenableFuture();
        actionRequestBuilder.execute(listenableActionFuture);
        return new ActionPromise<>(listenableActionFuture);
    }

    public <O> ActionPromise<O> mapResponse(Function<T, O> mapper) {
        PlainListenableActionFuture<O> listenableActionFuture = PlainListenableActionFuture.newListenableFuture();

        this.listenableActionFuture.addListener(new ActionListener<T>() {
            @Override
            public void onResponse(T response) {
                O result = mapper.apply(response);
                listenableActionFuture.onResponse(result);
            }

            @Override
            public void onFailure(Exception failure) {
                listenableActionFuture.onFailure(failure);
            }
        });

        return new ActionPromise<>(listenableActionFuture);
    }

    public <R extends ActionResponse> ActionPromise<R> then(ActionRequestBuilder<?, R, ?> actionRequestBuilder) {
        PlainListenableActionFuture<R> listenableActionFuture = PlainListenableActionFuture.newListenableFuture();

        this.listenableActionFuture.addListener(new ActionListener<T>() {
            @Override
            public void onResponse(T response) {
                actionRequestBuilder.execute(listenableActionFuture);
            }

            @Override
            public void onFailure(Exception failure) {
                listenableActionFuture.onFailure(failure);
            }
        });

        return new ActionPromise<>(listenableActionFuture);
    }

    public <O> ActionPromise<O> then(BiConsumer<T, ActionListener<O>> nextAsyncActionCall) {
        PlainListenableActionFuture<O> listenableActionFuture = PlainListenableActionFuture.newListenableFuture();

        this.listenableActionFuture.addListener(new ActionListener<T>() {
            @Override
            public void onResponse(T response) {
                nextAsyncActionCall.accept(response, listenableActionFuture);
            }

            @Override
            public void onFailure(Exception failure) {
                listenableActionFuture.onFailure(failure);
            }
        });

        return new ActionPromise<>(listenableActionFuture);
    }

    public ActionPromise<T> then(Runnable nextAsyncActionCall) {
        PlainListenableActionFuture<T> listenableActionFuture = PlainListenableActionFuture.newListenableFuture();

        this.listenableActionFuture.addListener(new ActionListener<T>() {
            @Override
            public void onResponse(T response) {
                nextAsyncActionCall.run();
                listenableActionFuture.onResponse(response);
            }

            @Override
            public void onFailure(Exception failure) {
                listenableActionFuture.onFailure(failure);
            }
        });

        return new ActionPromise<>(listenableActionFuture);
    }

    public void then(ActionListener<T> actionListener) {
        listenableActionFuture.addListener(actionListener);
    }

    public void unless(Consumer<Exception> failHandler) {
        listenableActionFuture.addListener(ActionListener.wrap((ignored) -> {}, failHandler::accept));
    }

    public ListenableActionFuture<T> toFuture() {
        return this.listenableActionFuture;
    }
}

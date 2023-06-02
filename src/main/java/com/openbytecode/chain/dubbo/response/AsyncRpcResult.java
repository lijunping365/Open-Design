package com.openbytecode.chain.dubbo.response;

import com.openbytecode.chain.dubbo.Invocation;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * @author lijunping
 */
public class AsyncRpcResult implements Result {

    private volatile boolean async;

    private Invocation invocation;

    private CompletableFuture<Response> responseFuture;

    public AsyncRpcResult(CompletableFuture<Response> future, Invocation invocation) {
        this.responseFuture = future;
        this.invocation = invocation;
        async = !future.isDone();
    }

    @Override
    public Object getValue() {
        try {
            if (responseFuture.isDone()) {
                return responseFuture.get();
            }
        } catch (Exception e) {
            // This should not happen in normal request process;
            System.out.println("Got exception when trying to fetch the underlying result from AsyncRpcResult.");
            throw new RuntimeException(e.getMessage());
        }

        return createDefaultValue(invocation);
    }

    @Override
    public void setValue(Object value) {
        try {
            if (responseFuture.isDone()) {
                responseFuture.get().setValue(value);
            } else {
                Response appResponse = new Response();
                appResponse.setValue(value);
                responseFuture.complete(appResponse);
            }
        } catch (Exception e) {
            // This should not happen in normal request process;
            System.out.println("Got exception when trying to fetch the underlying result from AsyncRpcResult.");
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Result whenCompleteWithContext(BiConsumer<Result, Throwable> fn) {
        this.responseFuture = this.responseFuture.whenComplete((v, t) -> {
            if (async) {
                //RpcContext.restoreContext(storedContext);
            }
            fn.accept(v, t);
        });

        return this;
    }

    private static Result createDefaultValue(Invocation invocation) {
        return new Response();
    }

    public static AsyncRpcResult newDefaultAsyncResult(Object value, Throwable t, Invocation invocation) {
        CompletableFuture<Response> future = new CompletableFuture<>();
        Response result = new Response();
        if (t != null) {
            result.setException(t);
        } else {
            result.setValue(value);
        }
        future.complete(result);
        return new AsyncRpcResult(future, invocation);
    }
}

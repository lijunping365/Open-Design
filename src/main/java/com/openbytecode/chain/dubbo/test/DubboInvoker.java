package com.openbytecode.chain.dubbo.test;

import com.openbytecode.chain.dubbo.*;
import com.openbytecode.chain.dubbo.response.AsyncRpcResult;
import com.openbytecode.chain.dubbo.response.Response;
import com.openbytecode.chain.dubbo.response.Result;

import java.util.concurrent.CompletableFuture;

/**
 * @author lijunping
 */
public class DubboInvoker<T> extends AbstractInvoker<T> {

    public DubboInvoker(Class<T> serviceType) {
        super(serviceType);
    }

    @Override
    protected Result doInvoke(Invocation invocation) throws Throwable {
        RpcInvocation inv = (RpcInvocation) invocation;
        final String methodName = inv.getMethodName();
        String serviceName = inv.getServiceName();
        System.out.println("执行 rpc 调用" + serviceName + ":" + methodName);
        CompletableFuture<Response> completableFuture = CompletableFuture.supplyAsync(() -> {
            Response response = new Response();
            response.setValue("yyyyyyyyyyyyyyy");
            return response;
        });
        return new AsyncRpcResult(completableFuture, inv);
    }
}

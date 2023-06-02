package com.openbytecode.chain.dubbo;

import com.openbytecode.chain.dubbo.response.AsyncRpcResult;
import com.openbytecode.chain.dubbo.response.Result;

import java.lang.reflect.InvocationTargetException;

/**
 * @author lijunping
 */
public abstract class AbstractInvoker<T> implements Invoker<T> {

    /**
     * Service interface type
     */
    private final Class<T> type;

    public AbstractInvoker(Class<T> type) {
        this.type = type;
    }

    @Override
    public Result invoke(Invocation inv) throws Exception {
        RpcInvocation invocation = (RpcInvocation) inv;

        // prepare rpc invocation
        prepareInvocation(invocation);

        // do invoke rpc invocation and return async result
        return doInvokeAndReturn(invocation);
    }

    @Override
    public Class<T> getInterface() {
        return type;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public void destroy() {

    }

    private void prepareInvocation(RpcInvocation inv) {
        inv.setInvoker(this);
    }

    private AsyncRpcResult doInvokeAndReturn(RpcInvocation invocation) {
        AsyncRpcResult asyncResult = null;
        try {
            asyncResult = (AsyncRpcResult) doInvoke(invocation);
        } catch (InvocationTargetException e) {
            System.out.println(e.getMessage());
        } catch (Throwable e) {
            asyncResult = AsyncRpcResult.newDefaultAsyncResult(null, e, invocation);
        }

        return asyncResult;
    }

    protected abstract Result doInvoke(Invocation invocation) throws Throwable;
}

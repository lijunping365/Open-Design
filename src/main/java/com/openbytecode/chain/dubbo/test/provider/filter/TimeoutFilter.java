package com.openbytecode.chain.dubbo.test.provider.filter;

import com.openbytecode.chain.dubbo.*;
import com.openbytecode.chain.dubbo.response.Result;
import com.openbytecode.chain.dubbo.spi.Activate;

/**
 * @author lijunping
 */
@Activate(group = "PROVIDER", order = -11000)
public class TimeoutFilter implements Filter, Filter.Listener{

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws Exception {
        System.out.println("Timeout........Before.......");
        Result invoke = invoker.invoke(invocation);
        System.out.println("Timeout........After.......");
        return invoke;
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        System.out.println("tttttttttttttttttttt");
    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {

    }
}

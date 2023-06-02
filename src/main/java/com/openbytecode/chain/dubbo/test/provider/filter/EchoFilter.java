package com.openbytecode.chain.dubbo.test.provider.filter;

import com.openbytecode.chain.dubbo.*;
import com.openbytecode.chain.dubbo.response.Result;
import com.openbytecode.chain.dubbo.spi.Activate;

/**
 * @author lijunping
 */
@Activate(group = "PROVIDER", order = -110000)
public class EchoFilter implements Filter, Filter.Listener {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation inv) throws Exception {
        System.out.println("Echo......Before.........");
        Result invoke = invoker.invoke(inv);
        System.out.println("Echo......After.........");
        return invoke;
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        System.out.println("sssssssssssssssssssss");
    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {

    }
}

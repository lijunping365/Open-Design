package com.openbytecode.chain.dubbo.test.provider.filter;

import com.openbytecode.chain.dubbo.*;
import com.openbytecode.chain.dubbo.response.Result;
import com.openbytecode.chain.dubbo.spi.Activate;

/**
 * @author lijunping
 */
@Activate(group = "PROVIDER", order = -1000)
public class TokenFilter implements Filter, Filter.Listener{

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws Exception {
        System.out.println("Token.......Before........");
        Result result = invoker.invoke(invocation);
        System.out.println("Token.......After........");
        return result;
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        System.out.println("ooooooooooooooooooooo");
    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {

    }
}

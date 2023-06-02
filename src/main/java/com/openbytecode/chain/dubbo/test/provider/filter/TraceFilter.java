package com.openbytecode.chain.dubbo.test.provider.filter;

import com.openbytecode.chain.dubbo.*;
import com.openbytecode.chain.dubbo.response.Result;
import com.openbytecode.chain.dubbo.spi.Activate;

/**
 * @author lijunping
 */
@Activate(group = "PROVIDER", order = -110)
public class TraceFilter implements Filter, Filter.Listener {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws Exception {
        System.out.println("Trace.......Before........");
        Result invoke = invoker.invoke(invocation);
        System.out.println("Trace.......After........");
        return invoke;
    }

    @Override
    public void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation) {
        System.out.println("pppppppppppppppppppppp");
    }

    @Override
    public void onError(Throwable t, Invoker<?> invoker, Invocation invocation) {

    }
}

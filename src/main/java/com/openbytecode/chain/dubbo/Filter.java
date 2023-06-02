package com.openbytecode.chain.dubbo;


import com.openbytecode.chain.dubbo.response.Result;
import com.openbytecode.chain.dubbo.spi.SPI;

/**
 * @author lijunping
 */
@SPI
public interface Filter {

    Result invoke(Invoker<?> invoker, Invocation invocation) throws Exception;

    interface Listener {

        /**
         * This method will only be called on successful remote rpc execution, that means, the service in on remote received
         * the request and the result (normal or exceptional) returned successfully.
         *
         * @param appResponse, the rpc call result, it can represent both normal result and exceptional result
         * @param invoker,     context
         * @param invocation,  context
         */
        void onResponse(Result appResponse, Invoker<?> invoker, Invocation invocation);

        /**
         * This method will be called on detection of framework exceptions, for example, TimeoutException, NetworkException
         * Exception raised in Filters, etc.
         *
         * @param t,          framework exception
         * @param invoker,    context
         * @param invocation, context
         */
        void onError(Throwable t, Invoker<?> invoker, Invocation invocation);
    }
}

package com.openbytecode.chain.dubbo;

import com.openbytecode.chain.dubbo.response.Result;

/**
 * @author lijunping
 */
public interface Invoker<T> extends Node {

    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();

    /**
     * invoke.
     *
     * @param invocation
     * @return result
     * @throws Exception
     */
    Result invoke(Invocation invocation) throws Exception;

    /**
     * destroy all
     */
    default void destroyAll() {
        destroy();
    }

}

package com.openbytecode.chain.dubbo;

/**
 * @author lijunping
 */
public interface Node {

    /**
     * is available.
     *
     * @return available.
     */
    boolean isAvailable();

    /**
     * destroy.
     */
    void destroy();

}

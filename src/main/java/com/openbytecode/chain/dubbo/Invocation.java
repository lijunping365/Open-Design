package com.openbytecode.chain.dubbo;

/**
 * @author lijunping
 */
public interface Invocation {

    /**
     * get the invoker in current context.
     *
     * @return invoker.
     * @transient
     */
    Invoker<?> getInvoker();

    /**
     * get method name.
     *
     * @return method name.
     * @serial
     */
    String getMethodName();

    /**
     * get the interface name
     *
     * @return
     */
    String getServiceName();

    /**
     * get arguments.
     *
     * @return arguments.
     * @serial
     */
    Object[] getArguments();
}

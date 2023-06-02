package com.openbytecode.chain.spring;


/**
 * @author lijunping
 */
@FunctionalInterface
public interface MethodInterceptor {

    /**
     * Implement this method to perform extra treatments before and
     * after the invocation. Polite implementations would certainly
     * like to invoke {@link Joinpoint#proceed()}.
     * @param invocation the method invocation joinpoint
     * @return the result of the call to {@link Joinpoint#proceed()};
     * might be intercepted by the interceptor
     * @throws Throwable if the interceptors or the target object
     * throws an exception
     */
    Object invoke(MethodInvocation invocation) throws Throwable;

}

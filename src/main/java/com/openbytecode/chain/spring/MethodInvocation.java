package com.openbytecode.chain.spring;

import java.lang.reflect.Method;

/**
 * @author lijunping
 */
public interface MethodInvocation {

    /**
     * Proceed to the next interceptor in the chain.
     * <p>The implementation and the semantics of this method depends
     * on the actual joinpoint type (see the children interfaces).
     * @return see the children interfaces' proceed definition
     * @throws Throwable if the joinpoint throws an exception
     */
    Object proceed() throws Throwable;

    /**
     * Get the method being called.
     * <p>This method is a friendly implementation of the
     * {@link Joinpoint#getStaticPart()} method (same result).
     * @return the method being called
     */
    Method getMethod();

    /**
     * Get the arguments as an array object.
     * It is possible to change element values within this
     * array to change the arguments.
     * @return the argument of the invocation
     */
    Object[] getArguments();

    /**
     * Return the object that holds the current joinpoint's static part.
     * <p>For instance, the target object for an invocation.
     * @return the object (can be null if the accessible object is static)
     */
    Object getThis();
}

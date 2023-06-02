package com.openbytecode.chain.spring;

import org.springframework.aop.support.AopUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author lijunping
 */
public class BaseMethodInvocation implements MethodInvocation{

    protected Object[] arguments;

    protected final Object proxy;

    protected final Object target;

    protected final Method method;

    protected final Class<?> targetClass;

    protected final List<?> interceptors;

    /**
     * Index from 0 of the current interceptor we're invoking.
     * -1 until we invoke: then the current interceptor.
     */
    private int currentInterceptorIndex = -1;

    protected BaseMethodInvocation(Object proxy,
                                   Object target,
                                   Method method,
                                   Object[] arguments,
                                   Class<?> targetClass,
                                   List<?> interceptors) {
        this.proxy = proxy;
        this.target = target;
        this.method = BridgeMethodResolver.findBridgedMethod(method);
        this.arguments = adaptArgumentsIfNecessary(method, arguments);
        this.targetClass = targetClass;
        this.interceptors = interceptors;
    }

    @Override
    public Object proceed() throws Throwable {
        // We start with an index of -1 and increment early.
        if (this.currentInterceptorIndex == this.interceptors.size() - 1) {
            return invoke();
        }
        Object interceptor = this.interceptors.get(++this.currentInterceptorIndex);
        return ((MethodInterceptor) interceptor).invoke(this);
    }

    @Override
    public Method getMethod() {
        return this.method;
    }

    @Override
    public Object[] getArguments() {
        return this.arguments;
    }

    @Override
    public Object getThis() {
        return this.target;
    }
    /**
     * Invoke the joinpoint using reflection.
     * Subclasses can override this to use custom invocation.
     * @return the return value of the joinpoint
     * @throws Throwable if invoking the joinpoint resulted in an exception
     */
    @Nullable
    protected Object invoke() throws Throwable {
        return AopUtils.invokeJoinpointUsingReflection(this.target, this.method, this.arguments);
    }

    /**
     * Adapt the given arguments to the target signature in the given method,
     * if necessary: in particular, if a given vararg argument array does not
     * match the array type of the declared vararg parameter in the method.
     * @param method the target method
     * @param arguments the given arguments
     * @return a cloned argument array, or the original if no adaptation is needed
     * @since 4.2.3
     */
    static Object[] adaptArgumentsIfNecessary(Method method, @Nullable Object[] arguments) {
        if (ObjectUtils.isEmpty(arguments)) {
            return new Object[0];
        }
        if (method.isVarArgs()) {
            if (method.getParameterCount() == arguments.length) {
                Class<?>[] paramTypes = method.getParameterTypes();
                int varargIndex = paramTypes.length - 1;
                Class<?> varargType = paramTypes[varargIndex];
                if (varargType.isArray()) {
                    Object varargArray = arguments[varargIndex];
                    if (varargArray instanceof Object[] && !varargType.isInstance(varargArray)) {
                        Object[] newArguments = new Object[arguments.length];
                        System.arraycopy(arguments, 0, newArguments, 0, varargIndex);
                        Class<?> targetElementType = varargType.getComponentType();
                        int varargLength = Array.getLength(varargArray);
                        Object newVarargArray = Array.newInstance(targetElementType, varargLength);
                        System.arraycopy(varargArray, 0, newVarargArray, 0, varargLength);
                        newArguments[varargIndex] = newVarargArray;
                        return newArguments;
                    }
                }
            }
        }
        return arguments;
    }
}

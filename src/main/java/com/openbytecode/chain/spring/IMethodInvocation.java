package com.openbytecode.chain.spring;

import net.sf.cglib.proxy.MethodProxy;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * @author lijunping
 */
public class IMethodInvocation extends BaseMethodInvocation{

    private final MethodProxy methodProxy;

    public IMethodInvocation(Object proxy,
                             Object target,
                             Method method,
                             Object[] arguments,
                             Class<?> targetClass,
                             List<?> interceptors,
                             MethodProxy methodProxy) {

        super(proxy, target, method, arguments, targetClass, interceptors);

        // Only use method proxy for public methods not derived from java.lang.Object
        this.methodProxy = (Modifier.isPublic(method.getModifiers()) &&
                method.getDeclaringClass() != Object.class && !AopUtils.isEqualsMethod(method) &&
                !AopUtils.isHashCodeMethod(method) && !AopUtils.isToStringMethod(method) ?
                methodProxy : null);
    }

    @Override
    public Object proceed() throws Throwable {
        return super.proceed();
    }

    @Override
    protected Object invoke() throws Throwable {
        if (this.methodProxy != null) {
            return this.methodProxy.invoke(this.target, this.arguments);
        }
        else {
            return super.invoke();
        }
    }
}

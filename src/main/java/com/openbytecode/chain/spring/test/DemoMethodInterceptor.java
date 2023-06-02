package com.openbytecode.chain.spring.test;

import com.openbytecode.chain.spring.IMethodInvocation;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 注意这里的 MethodInterceptor 是 net.sf.cglib.proxy.MethodInterceptor
 *
 * @author lijunping
 */
public class DemoMethodInterceptor implements MethodInterceptor {

    private final Object target;
    private final List<?> chain;

    public DemoMethodInterceptor(Object target, List<Object> chain) {
        this.target = target;
        this.chain = chain;
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        // 构建调用链
        IMethodInvocation invocation = new IMethodInvocation(proxy, target, method, args, target.getClass(), chain, methodProxy);
        // 执行调用链
        return invocation.proceed();
    }
}

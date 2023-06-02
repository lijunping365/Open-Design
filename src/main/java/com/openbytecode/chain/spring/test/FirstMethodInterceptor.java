
package com.openbytecode.chain.spring.test;


import com.openbytecode.chain.spring.MethodInterceptor;
import com.openbytecode.chain.spring.MethodInvocation;

/**
 * 注意这里是实现我们自己的 MethodInterceptor
 *
 * @author lijunping
 */
public class FirstMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("FirstMethodInterceptor .......Before");
        Object proceed = invocation.proceed();// 调用 IMethodInvocation 的 proceed 方法，形成递归调用
        System.out.println("FirstMethodInterceptor .......After");
        return proceed;
    }
}

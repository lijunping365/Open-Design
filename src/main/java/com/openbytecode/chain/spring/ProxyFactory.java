package com.openbytecode.chain.spring;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * @author lijunping
 */
public class ProxyFactory {

    /**
     * 实例化动态代理对象
     * @param tClass 代理目标对象类型
     * @param <T> 代理目标对象类型泛型
     * @return 动态代理对象
     */
    public static <T> T getProxyInstance(Class<T> tClass, MethodInterceptor interceptor) {
        return getProxyInstance(tClass, tClass, interceptor);
    }

    /**
     * 实例化动态代理对象，并转换为对应接口类型
     * @param tClass 代理目标对象类型
     * @param interfaceClass 代理目标对象接口类型
     * @param <T> 代理目标对象接口类型泛型
     * @return 动态代理对象
     */
    public static <T> T getProxyInstance(Class<? extends T> tClass, Class<T> interfaceClass, MethodInterceptor interceptor) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tClass);
        enhancer.setClassLoader(tClass.getClassLoader());
        enhancer.setCallback(interceptor); // 实现了 MethodInterceptor 接口的类
        return interfaceClass.cast(enhancer.create());
    }
}

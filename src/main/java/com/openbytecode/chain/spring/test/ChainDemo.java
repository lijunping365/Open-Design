package com.openbytecode.chain.spring.test;

import com.openbytecode.chain.spring.ProxyFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijunping
 */
public class ChainDemo {
    public static void main(String[] args) {
        // 创建目标对象
        DemoService demoService = new DemoService();
        // 构建拦截器链
        List<Object> chain = new ArrayList<>();
        chain.add(new FirstMethodInterceptor());
        chain.add(new SecondMethodInterceptor());
        // 创建 cglib 动态代理的 Interceptor
        DemoMethodInterceptor interceptor = new DemoMethodInterceptor(demoService, chain);
        // 获取代理对象
        DemoService proxyInstance = ProxyFactory.getProxyInstance(DemoService.class, interceptor);
        // 执行代理对象
        proxyInstance.test();
    }
}

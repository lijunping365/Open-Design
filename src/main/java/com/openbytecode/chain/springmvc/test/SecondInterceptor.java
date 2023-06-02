package com.openbytecode.chain.springmvc.test;


import com.openbytecode.chain.springmvc.HandlerInterceptor;
import com.openbytecode.chain.springmvc.HttpRequest;
import com.openbytecode.chain.springmvc.HttpResponse;

/**
 * @author lijunping
 */
public class SecondInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpRequest request, HttpResponse response, Object handler) throws Exception {
        System.out.println("SecondInterceptor 前置");
        return true;
    }

    @Override
    public void postHandle(HttpRequest request, HttpResponse response, Object handler, Object obj) throws Exception {
        System.out.println("SecondInterceptor 处理中");
    }

    @Override
    public void afterCompletion(HttpRequest request, HttpResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("SecondInterceptor 后置");
    }
}

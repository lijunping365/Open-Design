package com.openbytecode.chain.springmvc;

/**
 * @author lijunping
 */
public interface HandlerInterceptor {

    default boolean preHandle(HttpRequest request, HttpResponse response, Object handler) throws Exception {
        return true;
    }

    default void postHandle(HttpRequest request, HttpResponse response, Object handler, Object obj) throws Exception {
    }

    default void afterCompletion(HttpRequest request, HttpResponse response, Object handler, Exception ex) throws Exception {
    }
}

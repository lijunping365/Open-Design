package com.openbytecode.chain.tomcat;

/**
 * @author lijunping
 */
public interface FilterChain {

    void addFilter(Filter filter);

    /**
     * 回调入口
     * @param request
     * @param response
     */
    void doFilter(HttpRequest request, HttpResponse response);
}

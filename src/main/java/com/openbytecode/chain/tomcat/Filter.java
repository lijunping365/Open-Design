package com.openbytecode.chain.tomcat;


/**
 * @author lijunping
 */
public interface Filter {

    void doFilter(HttpRequest request, HttpResponse response, FilterChain chain);
}

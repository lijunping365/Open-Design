package com.openbytecode.chain.tomcat.test;

import com.openbytecode.chain.tomcat.Filter;
import com.openbytecode.chain.tomcat.FilterChain;
import com.openbytecode.chain.tomcat.HttpRequest;
import com.openbytecode.chain.tomcat.HttpResponse;

/**
 * @author lijunping
 */
public class FirstFilter implements Filter {


    @Override
    public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) {
        System.out.println("FirstFilter ................Before");
        chain.doFilter(request, response);
        System.out.println("FirstFilter ................After");
    }
}

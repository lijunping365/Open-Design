package com.openbytecode.chain.tomcat.test;


import com.openbytecode.chain.tomcat.Filter;
import com.openbytecode.chain.tomcat.FilterChain;
import com.openbytecode.chain.tomcat.HttpRequest;
import com.openbytecode.chain.tomcat.HttpResponse;

/**
 * @author lijunping
 */
public class SecondFilter implements Filter {


    @Override
    public void doFilter(HttpRequest request, HttpResponse response, FilterChain chain) {
        System.out.println("SecondFilter ................Before");
        chain.doFilter(request, response);
        System.out.println("SecondFilter ................After");
    }
}

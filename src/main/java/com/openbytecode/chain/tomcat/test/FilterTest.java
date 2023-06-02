package com.openbytecode.chain.tomcat.test;


import com.openbytecode.chain.tomcat.DefaultFilterChain;
import com.openbytecode.chain.tomcat.HttpRequest;
import com.openbytecode.chain.tomcat.HttpResponse;

/**
 * @author lijunping
 */
public class FilterTest {

    public static void main(String[] args) {
        DefaultFilterChain filterChain = new DefaultFilterChain();

        filterChain.addFilter(new FirstFilter());
        filterChain.addFilter(new SecondFilter());
        filterChain.addFilter(new ThirdFilter());
        filterChain.doFilter(new HttpRequest(), new HttpResponse());

    }
}

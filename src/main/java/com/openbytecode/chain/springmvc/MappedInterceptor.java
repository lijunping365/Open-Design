package com.openbytecode.chain.springmvc;


import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author lijunping
 */
public class MappedInterceptor implements HandlerInterceptor{

    private final List<String> includeConditions;

    private final List<String> excludeConditions;

    private final HandlerInterceptor interceptor;

    public MappedInterceptor(List<String> includeConditions, List<String> excludeConditions, HandlerInterceptor interceptor) {
        this.includeConditions = includeConditions;
        this.excludeConditions = excludeConditions;
        this.interceptor = interceptor;
    }

    public boolean matches(String parameter) {
        if (StringUtils.isBlank(parameter)){
            return false;
        }
        return includeConditions.contains(parameter) && !excludeConditions.contains(parameter);
    }

    public HandlerInterceptor getInterceptor() {
        return interceptor;
    }
}

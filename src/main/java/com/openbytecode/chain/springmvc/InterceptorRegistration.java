package com.openbytecode.chain.springmvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lijunping
 */
public class InterceptorRegistration {

    private final HandlerInterceptor interceptor;

    private List<String> includeCondition;

    private List<String> excludeCondition;

    private int order = 0;

    public InterceptorRegistration(HandlerInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public InterceptorRegistration order(int order) {
        this.order = order;
        return this;
    }

    protected int getOrder() {
        return this.order;
    }

    protected Object getInterceptor() {
        if (this.includeCondition == null && this.excludeCondition == null) {
            return this.interceptor;
        } else {
            return new MappedInterceptor(includeCondition, excludeCondition, this.interceptor);
        }
    }

    public InterceptorRegistration addIncludeConditions(String... conditions) {
        return this.addIncludeConditions(Arrays.asList(conditions));
    }

    public InterceptorRegistration addIncludeConditions(List<String> conditions) {
        this.includeCondition = this.includeCondition != null ? this.includeCondition : new ArrayList(conditions.size());
        this.includeCondition.addAll(conditions);
        return this;
    }

    public InterceptorRegistration addExcludeConditions(String... patterns) {
        return this.addExcludeConditions(Arrays.asList(patterns));
    }

    public InterceptorRegistration addExcludeConditions(List<String> patterns) {
        this.excludeCondition = this.excludeCondition != null ? this.excludeCondition : new ArrayList(patterns.size());
        this.excludeCondition.addAll(patterns);
        return this;
    }
}

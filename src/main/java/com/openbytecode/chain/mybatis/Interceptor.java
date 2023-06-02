package com.openbytecode.chain.mybatis;

/**
 * @author lijunping
 */
public interface Interceptor {

    Object intercept(Invocation invocation) throws Throwable;

    default Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}

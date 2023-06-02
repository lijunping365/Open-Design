package com.openbytecode.chain.mybatis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author lijunping
 */
public class InterceptorChain {
    /**
     * 拦截器集合
     */
    private final List<Interceptor> interceptors = new ArrayList<>();

    /**
     * 装载拦截器
     */
    public Object pluginAll(Object target) {
        for (Interceptor interceptor : interceptors) {
            target = interceptor.plugin(target);
        }
        return target;
    }

    /**
     * 添加拦截器
     *
     * @param interceptor 拦截器
     */
    public void addInterceptor(Interceptor interceptor) {
        interceptors.add(interceptor);
    }


    /**
     * 获取所有拦截器
     *
     * @return 拦截器集合
     */
    public List<Interceptor> getInterceptors() {
        return Collections.unmodifiableList(interceptors);
    }
}

package com.openbytecode.chain.springmvc;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijunping
 */
public class HandlerExecutionChain {

    private final Object handler;

    private List<HandlerInterceptor> interceptorList = new ArrayList<>();

    private int interceptorIndex = -1;

    public HandlerExecutionChain(Object handler) {
        this.handler = handler;
    }

    /**
     * Add the given interceptor to the end of this chain.
     */
    public void addInterceptor(HandlerInterceptor interceptor) {
        interceptorList.add(interceptor);
    }

    /**
     * Add the given interceptor at the specified index of this chain.
     * @since 5.2
     */
    public void addInterceptor(int index, HandlerInterceptor interceptor) {
        interceptorList.add(index, interceptor);
    }

    public List<HandlerInterceptor> getInterceptors() {
        return this.interceptorList;
    }


    /**
     * Apply preHandle methods of registered interceptors.
     * @return {@code true} if the execution chain should proceed with the
     * next interceptor or the handler itself. Else, DispatcherServlet assumes
     * that this interceptor has already dealt with the response itself.
     */
    public boolean applyPreHandle(HttpRequest request, HttpResponse response) throws Exception {
        List<HandlerInterceptor> interceptors = getInterceptors();
        if (!CollectionUtils.isEmpty(interceptors)) {
            for (int i = 0; i < interceptors.size(); i++) {
                HandlerInterceptor interceptor = interceptors.get(i);
                if (!interceptor.preHandle(request, response, this.handler)) {
                    triggerAfterCompletion(request, response, null);
                    return false;
                }
                this.interceptorIndex = i;
            }
        }
        return true;
    }

    /**
     * Apply postHandle methods of registered interceptors.
     */
    public void applyPostHandle(HttpRequest request, HttpResponse response, Object obj) throws Exception {
        List<HandlerInterceptor> interceptors = getInterceptors();
        if (!CollectionUtils.isEmpty(interceptors)) {
            for (int i = interceptors.size() - 1; i >= 0; i--) {
                HandlerInterceptor interceptor = interceptors.get(i);
                interceptor.postHandle(request, response, this.handler, obj);
            }
        }
    }

    /**
     * Trigger afterCompletion callbacks on the mapped HandlerInterceptors.
     * Will just invoke afterCompletion for all interceptors whose preHandle invocation
     * has successfully completed and returned true.
     */
    public void triggerAfterCompletion(HttpRequest request, HttpResponse response, Exception ex) throws Exception {
        List<HandlerInterceptor> interceptors = getInterceptors();
        if (!CollectionUtils.isEmpty(interceptors)) {
            for (int i = this.interceptorIndex; i >= 0; i--) {
                HandlerInterceptor interceptor = interceptors.get(i);
                try {
                    interceptor.afterCompletion(request, response, this.handler, ex);
                }
                catch (Throwable ex2) {
                    System.out.println("HandlerInterceptor.afterCompletion threw exception" + ex2.getMessage());
                }
            }
        }
    }
}

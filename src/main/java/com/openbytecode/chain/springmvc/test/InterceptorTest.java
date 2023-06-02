package com.openbytecode.chain.springmvc.test;

import com.openbytecode.chain.springmvc.*;

import java.util.List;

/**
 * @author lijunping
 */
public class InterceptorTest {

    /**
     * 输出结果如下：
     *
     * FirstInterceptor 前置
     * SecondInterceptor 前置
     * rrrrrrrrrrrttttttttttttttttttt
     * SecondInterceptor 处理中
     * FirstInterceptor 处理中
     * SecondInterceptor 后置
     * FirstInterceptor 后置
     *
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        InterceptorRegistry registry = new InterceptorRegistry();

        registry.addInterceptor(new FirstInterceptor())
                .addIncludeConditions("aaa")
                .addExcludeConditions("bbb")
                .order(1);

        registry.addInterceptor(new SecondInterceptor())
                .addIncludeConditions("aaa")
                .addExcludeConditions("ddd")
                .order(2);

        List<Object> interceptors = registry.getInterceptors();

        dispatcher((List<HandlerInterceptor>)(List)interceptors);
    }

    private static void dispatcher(List<HandlerInterceptor> interceptors) throws Exception {

        HandlerMethod handlerMethod = new HandlerMethod(new DemoService(), DemoService.class, DemoService.class.getMethod("test", null), null);

        HandlerExecutionChain chain = new HandlerExecutionChain(handlerMethod);

        for (HandlerInterceptor interceptor : interceptors) {
            if (interceptor instanceof MappedInterceptor){
                MappedInterceptor mappedInterceptor = (MappedInterceptor) interceptor;
                if (mappedInterceptor.matches("aaa")) {
                    chain.addInterceptor(mappedInterceptor.getInterceptor());
                }
            } else{
                chain.addInterceptor(interceptor);
            }
        }

        HttpRequest request = new HttpRequest();
        HttpResponse response = new HttpResponse();

        Exception exception = null;
        try {
            final boolean b = chain.applyPreHandle(request, response);
            if (!b){
                return;
            }

            // invoke 原方法
            Object o = handlerMethod.doInvoke(null);
            System.out.println(o);

            chain.applyPostHandle(request, response, o);
        }catch (Exception e){
            exception = e;
            System.out.println(e.getMessage());
        }
        chain.triggerAfterCompletion(request, response, exception);
    }


}

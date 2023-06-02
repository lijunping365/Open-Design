package com.openbytecode.chain.springmvc;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author lijunping
 */
public class HandlerMethod {

    private final Object bean;

    private final Class<?> beanType;

    private final Method method;

    private final Object[] arguments;

    public HandlerMethod(Object bean, Class<?> beanType, Method method, Object[] arguments) {
        this.bean = bean;
        this.beanType = beanType;
        this.method = method;
        this.arguments = arguments;
    }

    public Object doInvoke(Object... args) throws Exception {
        ReflectionUtils.makeAccessible(method);
        try {
            return method.invoke(bean, args);
        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}

package com.openbytecode.chain.dubbo.spi;

import java.lang.annotation.*;

/**
 * @author lijunping
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Activate {

    /**
     * 所属组，String[],例如消费端、服务端。
     *
     * @return
     */
    String[] group() default {};

    /**
     * 如果指定该值，只有当消费者或服务提供者URL中包含属性名为value的键值对，该过滤器才处于激活状态。
     */
    String[] value() default {};

    /**
     * 用户指定顺序，值越小，越先执行。
     */
    int order() default 0;
}

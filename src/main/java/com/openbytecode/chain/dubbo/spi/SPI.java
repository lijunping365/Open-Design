package com.openbytecode.chain.dubbo.spi;

import java.lang.annotation.*;

/**
 * @author lijunping
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface SPI {

    /**
     * default extension name
     */
    String value() default "";
}

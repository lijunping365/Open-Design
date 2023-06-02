package com.openbytecode.chain.mybatis;

import java.lang.annotation.*;

/**
 * @author lijunping
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {
    /**
     * Returns method signatures to intercept.
     *
     * @return method signatures
     */
    Signature[] value();
}

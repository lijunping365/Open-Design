package com.openbytecode.chain.dubbo.response;

import java.io.Serializable;
import java.util.function.BiConsumer;

/**
 * @author lijunping
 */
public interface Result extends Serializable {

    /**
     * Get invoke result.
     *
     * @return result. if no result return null.
     */
    Object getValue();

    void setValue(Object value);

    Result whenCompleteWithContext(BiConsumer<Result, Throwable> fn);
}

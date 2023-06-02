package com.openbytecode.chain.dubbo.response;

import com.openbytecode.chain.dubbo.response.Result;

import java.util.function.BiConsumer;

/**
 * @author lijunping
 */
public class Response implements Result {

    private static final long serialVersionUID = -6925924956850004727L;

    private Object result;

    private Throwable exception;

    @Override
    public Object getValue() {
        return result;
    }

    @Override
    public void setValue(Object value) {
        this.result = value;
    }

    public Throwable getException() {
        return exception;
    }

    public void setException(Throwable exception) {
        this.exception = exception;
    }

    @Override
    public Result whenCompleteWithContext(BiConsumer<Result, Throwable> fn) {
        throw new UnsupportedOperationException("AppResponse represents an concrete business response, there will be no status changes, you should get internal values directly.");
    }
}

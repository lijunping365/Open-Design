package com.openbytecode.chain.dubbo.spi;

/**
 * @author lijunping
 */
public class Holder<T> {

    private volatile T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}

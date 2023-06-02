package com.openbytecode.chain.netty;


/**
 * @author lijunping
 */
public class DefaultChannelHandlerContext implements ChannelHandlerContext{

    volatile DefaultChannelHandlerContext next;
    volatile DefaultChannelHandlerContext prev;
    private ChannelHandler handler;
    private final String name;

    public DefaultChannelHandlerContext(String name) {
        this.name = name;
    }

    public DefaultChannelHandlerContext(String name, ChannelHandler handler) {
        this.name = name;
        this.handler = handler;
    }

    @Override
    public ChannelHandler handler() {
        return this.handler;
    }

    @Override
    public void fireChannelRead(Object msg) {
        invokeChannelRead(next, msg);
    }

    @Override
    public void fireChannelReadComplete() {
        invokeChannelReadComplete(next);
    }

    @Override
    public void fireExceptionCaught(Throwable cause) {
        invokeExceptionCaught(next, cause);
    }

    public static void invokeChannelRead(final DefaultChannelHandlerContext next, Object msg) {
        if (null != next){
            next.invokeChannelRead(msg);
        }
    }

    public static void invokeChannelReadComplete(DefaultChannelHandlerContext next) {
        if (null != next){
            next.invokeChannelReadComplete();
        }
    }

    public static void invokeExceptionCaught(DefaultChannelHandlerContext next, Throwable cause) {
        if (null != next){
            next.invokeExceptionCaught(cause);
        }
    }

    private void invokeChannelRead(Object msg) {
        if (null != handler){
            try {
                handler.channelRead(this, msg);
            } catch (Exception e){
                invokeExceptionCaught(e);
            }
        } else {
            fireChannelRead(msg);
        }
    }

    private void invokeChannelReadComplete() {
        if (null != handler){
            try {
                handler.channelReadComplete(this);
            } catch (Exception e){
                invokeExceptionCaught(e);
            }
        } else {
            fireChannelReadComplete();
        }
    }

    private void invokeExceptionCaught(final Throwable cause) {
        if (null != handler){
            try {
                handler.exceptionCaught(this, cause);
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        } else {
            fireExceptionCaught(cause);
        }
    }

}

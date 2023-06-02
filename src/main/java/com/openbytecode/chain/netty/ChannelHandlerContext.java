package com.openbytecode.chain.netty;


/**
 * @author lijunping
 */
public interface ChannelHandlerContext {

    ChannelHandler handler();

    /**
     * A {@link io.netty.channel.Channel} received a message.
     *
     * This will result in having the {@link ChannelInboundHandler#channelRead(io.netty.channel.ChannelHandlerContext, Object)}
     * method  called of the next {@link ChannelInboundHandler} contained in the  {@link io.netty.channel.ChannelPipeline} of the
     * {@link io.netty.channel.Channel}.
     */
    void fireChannelRead(Object msg);

    /**
     * Triggers an {@link ChannelInboundHandler#channelReadComplete(io.netty.channel.ChannelHandlerContext)}
     * event to the next {@link ChannelInboundHandler} in the {@link io.netty.channel.ChannelPipeline}.
     */
    void fireChannelReadComplete();

    /**
     * A {@link io.netty.channel.Channel} received an {@link Throwable} in one of its inbound operations.
     *
     * This will result in having the  {@link ChannelInboundHandler#exceptionCaught(io.netty.channel.ChannelHandlerContext, Throwable)}
     * method  called of the next  {@link ChannelInboundHandler} contained in the  {@link io.netty.channel.ChannelPipeline} of the
     * {@link io.netty.channel.Channel}.
     */
    void fireExceptionCaught(Throwable cause);
}

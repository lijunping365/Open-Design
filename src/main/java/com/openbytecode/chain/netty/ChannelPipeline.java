package com.openbytecode.chain.netty;

/**
 * @author lijunping
 */
public interface ChannelPipeline {

    /**
     * Inserts a {@link io.netty.channel.ChannelHandler} at the first position of this pipeline.
     *
     * @param name     the name of the handler to insert first
     * @param handler  the handler to insert first
     *
     * @throws IllegalArgumentException
     *         if there's an entry with the same name already in the pipeline
     * @throws NullPointerException
     *         if the specified handler is {@code null}
     */
    ChannelPipeline addFirst(String name, ChannelHandler handler);

    /**
     * Appends a {@link ChannelHandler} at the last position of this pipeline.
     *
     * @param name     the name of the handler to append
     * @param handler  the handler to append
     *
     * @throws IllegalArgumentException
     *         if there's an entry with the same name already in the pipeline
     * @throws NullPointerException
     *         if the specified handler is {@code null}
     */
    ChannelPipeline addLast(String name, ChannelHandler handler);

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

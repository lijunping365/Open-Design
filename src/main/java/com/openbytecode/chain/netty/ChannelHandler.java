package com.openbytecode.chain.netty;

import java.nio.channels.Channel;

/**
 * @author lijunping
 */
public interface ChannelHandler {

    /**
     * Invoked when the current {@link io.netty.channel.Channel} has read a message from the peer.
     */
    void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception;

    /**
     * Invoked when the last message read by the current read operation has been consumed by
     * {@link #channelRead(io.netty.channel.ChannelHandlerContext, Object)}.  If {@link ChannelOption#AUTO_READ} is off, no further
     * attempt to read an inbound data from the current {@link Channel} will be made until
     * {@link io.netty.channel.ChannelHandlerContext#read()} is called.
     */
    void channelReadComplete(ChannelHandlerContext ctx) throws Exception;

    /**
     * Gets called if a {@link Throwable} was thrown.
     */
    void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception;
}

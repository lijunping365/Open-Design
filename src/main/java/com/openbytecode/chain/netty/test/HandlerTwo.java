package com.openbytecode.chain.netty.test;

import com.openbytecode.chain.netty.ChannelHandler;
import com.openbytecode.chain.netty.ChannelHandlerContext;

/**
 * @author lijunping
 */
public class HandlerTwo implements ChannelHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HandlerTwo................. Before");
        ctx.fireChannelRead(msg);
        System.out.println("HandlerTwo................. After");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HandlerTwo=======ReadComplete");
        ctx.fireChannelReadComplete();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("HandlerTwo=======exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}

package com.openbytecode.chain.netty.test;

import com.openbytecode.chain.netty.ChannelHandler;
import com.openbytecode.chain.netty.ChannelHandlerContext;

/**
 * @author lijunping
 */
public class HandlerOne11 implements ChannelHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HandlerOne111................. Before");
        ctx.fireChannelRead(msg);
        //throw new RuntimeException("test exception");
        System.out.println("HandlerOne111................. After");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HandlerOne111=======ReadComplete");
        ctx.fireChannelReadComplete();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("HandlerOne111=======exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}

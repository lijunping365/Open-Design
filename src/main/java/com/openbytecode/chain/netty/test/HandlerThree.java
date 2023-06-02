package com.openbytecode.chain.netty.test;

import com.openbytecode.chain.netty.ChannelHandler;
import com.openbytecode.chain.netty.ChannelHandlerContext;

/**
 * @author lijunping
 */
public class HandlerThree implements ChannelHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("HandlerThree................. Before");
        ctx.fireChannelRead(msg);
        System.out.println("HandlerThree................. After");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("HandlerThree=======ReadComplete");
        ctx.fireChannelReadComplete();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("HandlerThree=======exceptionCaught");
        ctx.fireExceptionCaught(cause);
    }
}

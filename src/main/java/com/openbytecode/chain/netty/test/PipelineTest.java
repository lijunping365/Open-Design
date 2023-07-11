package com.openbytecode.chain.netty.test;

import com.openbytecode.chain.netty.DefaultChannelPipeline;
import com.openbytecode.chain.netty.DefaultDispatcher;

/**
 * @author lijunping
 */
public class PipelineTest {

    public static void main(String[] args) {

        DefaultChannelPipeline pipeline = new DefaultChannelPipeline();
        pipeline.addLast("handlerOne", new HandlerOne());
        pipeline.addLast("handlerTwo", new HandlerTwo());
        pipeline.addLast("handlerThree", new HandlerThree());

        pipeline.addBefore("handlerTwo", "handlerOne11", new HandlerOne11());

        DefaultDispatcher dispatcher = new DefaultDispatcher(pipeline);

        dispatcher.doDispatcher("aaaaaaaaaaaaaaaaaa");

    }
}

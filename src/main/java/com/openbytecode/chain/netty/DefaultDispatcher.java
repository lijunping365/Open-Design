package com.openbytecode.chain.netty;

/**
 * @author lijunping
 */
public class DefaultDispatcher implements Dispatcher{

    private final ChannelPipeline channelPipeline;

    public DefaultDispatcher(ChannelPipeline channelPipeline) {
        this.channelPipeline = channelPipeline;
    }

    @Override
    public void doDispatcher(String msg) {
        try {
            channelPipeline.fireChannelRead(msg);
        }catch (Exception e){
            channelPipeline.fireExceptionCaught(e);
        }
        channelPipeline.fireChannelReadComplete();
    }
}

package com.openbytecode.chain.netty;


/**
 * @author lijunping
 */
public class DefaultChannelPipeline implements ChannelPipeline{

    private final DefaultChannelHandlerContext head;
    private final DefaultChannelHandlerContext tail;

    public DefaultChannelPipeline() {
        head = new DefaultChannelHandlerContext("HEAD");
        tail = new DefaultChannelHandlerContext("TAIL");
        head.next = tail;
        tail.prev = head;
    }

    @Override
    public ChannelPipeline addFirst(String name, ChannelHandler handler) {
        final DefaultChannelHandlerContext newNode;
        synchronized (this) {
            newNode = newHandlerNode(name, handler);
            addFirst0(newNode);
        }
        return this;
    }

    @Override
    public ChannelPipeline addLast(String name, ChannelHandler handler) {
        final DefaultChannelHandlerContext newNode;
        synchronized (this) {
            newNode = newHandlerNode(name, handler);
            addLast0(newNode);
        }
        return this;
    }

    @Override
    public void fireChannelRead(Object msg) {
        DefaultChannelHandlerContext.invokeChannelRead(head, msg);
    }

    @Override
    public void fireChannelReadComplete() {
        DefaultChannelHandlerContext.invokeChannelReadComplete(head);
    }

    @Override
    public void fireExceptionCaught(Throwable cause) {
        DefaultChannelHandlerContext.invokeExceptionCaught(head, cause);
    }

    private DefaultChannelHandlerContext newHandlerNode(String name, ChannelHandler handler) {
        return new DefaultChannelHandlerContext(name, handler);
    }

    private void addFirst0(DefaultChannelHandlerContext newNode) {
        DefaultChannelHandlerContext next = head.next;
        newNode.prev = head;
        newNode.next = next;
        head.next = newNode;
        next.prev = newNode;
    }

    private void addLast0(DefaultChannelHandlerContext newNode) {
        DefaultChannelHandlerContext prev = tail.prev;
        newNode.prev = prev;
        newNode.next = tail;
        prev.next = newNode;
        tail.prev = newNode;
    }
}

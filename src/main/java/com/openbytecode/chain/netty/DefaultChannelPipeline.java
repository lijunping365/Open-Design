package com.openbytecode.chain.netty;


import java.util.NoSuchElementException;

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
    public ChannelPipeline addBefore(String baseName, String name, ChannelHandler handler) {
        final DefaultChannelHandlerContext newNode;
        final DefaultChannelHandlerContext node;
        synchronized (this) {
            newNode = newHandlerNode(name, handler);
            node = getContextOrDie(baseName);
            addBefore0(node, newNode);
        }
        return this;
    }

    @Override
    public ChannelPipeline addAfter(String baseName, String name, ChannelHandler handler) {
        final DefaultChannelHandlerContext newNode;
        final DefaultChannelHandlerContext node;
        synchronized (this) {
            newNode = newHandlerNode(name, handler);
            node = getContextOrDie(baseName);
            addAfter0(node, newNode);
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

    private DefaultChannelHandlerContext getContextOrDie(String name) {
        DefaultChannelHandlerContext ctx = context(name);
        if (ctx == null) {
            throw new NoSuchElementException(name);
        } else {
            return ctx;
        }
    }

    private DefaultChannelHandlerContext context(String name) {
        DefaultChannelHandlerContext context = head.next;
        while (context != tail) {
            if (context.name().equals(name)) {
                return context;
            }
            context = context.next;
        }
        return null;
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

    private void addBefore0(DefaultChannelHandlerContext ctx, DefaultChannelHandlerContext newCtx) {
        newCtx.prev = ctx.prev;
        newCtx.next = ctx;
        ctx.prev.next = newCtx;
        ctx.prev = newCtx;
    }

    private static void addAfter0(DefaultChannelHandlerContext ctx, DefaultChannelHandlerContext newCtx) {
        newCtx.prev = ctx;
        newCtx.next = ctx.next;
        ctx.next.prev = newCtx;
        ctx.next = newCtx;
    }
}

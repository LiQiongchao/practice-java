package com.oio.practice.learn.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeClientHandler extends SimpleChannelInboundHandler {

    private final ByteBuf firstMessage;

    /**
     * Creates a client-side handler.
     */
    public TimeClientHandler() {
        byte[] req = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }
    //客户端和服务端TCP链路建立成功后调用此方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //通过此方法将消息发送给服务端
        ctx.writeAndFlush(firstMessage);
    }
    //服务端返回应答消息调用此方法
    @Override
    public void channelRead0(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("Now is : " + body);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 释放资源
        log.info("Unexpected exception from downstream : "
                + cause.getMessage());
        ctx.close();
    }
}

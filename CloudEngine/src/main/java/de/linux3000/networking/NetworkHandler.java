package de.linux3000.networking;

import de.linux3000.networking.packets.Packet;

import io.netty.channel.*;

import java.util.HashMap;


public class NetworkHandler extends SimpleChannelInboundHandler<Packet>{

    public static Channel proxyChannel;
    public static HashMap<String, Channel> serverChannel;


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        System.out.println("read");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        cause.printStackTrace();
        ctx.close();
    }


}

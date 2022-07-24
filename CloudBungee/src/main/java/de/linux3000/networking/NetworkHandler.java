package de.linux3000.networking;

import de.linux3000.Main;
import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.networking.packets.pkts.ProxyEnabledPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.EventExecutorGroup;

public class NetworkHandler  extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ca:" + ctx.channel());
        Main.getINSTANCE().getClient().setChannel(ctx.channel());
        //ctx.channel().writeAndFlush(new ProxyEnabledPacket().write(), ctx.channel().voidPromise());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        int id = buf.readInt();
        Class<? extends Packet> packetc = PacketManager.in_packets.get(id);
        if(packetc != null) {
            try {
                Packet packet = packetc.newInstance();
                packet.read(buf);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}

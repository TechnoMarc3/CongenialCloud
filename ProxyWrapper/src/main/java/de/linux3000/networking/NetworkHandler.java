package de.linux3000.networking;

import de.linux3000.ProxyWrapper;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

public class NetworkHandler  extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ProxyWrapper.getINSTANCE());
        System.out.println(ProxyWrapper.getINSTANCE().getNettyClient());
        ProxyWrapper.getINSTANCE().getNettyClient().setChannel(ctx.channel());
        //ctx.channel().writeAndFlush(new ProxyEnabledPacket().write(), ctx.channel().voidPromise());
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

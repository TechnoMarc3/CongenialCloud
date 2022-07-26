package de.linux3000.networking;


import de.linux3000.ProxyWrapper;
import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.networking.packets.pkts.HelloPacket;
import de.linux3000.utils.ArrayUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.List;

public class NettyClient {

    Channel channel;

    public NettyClient() throws InterruptedException {
        PacketManager.registerPackets();
        EventLoopGroup group = new NioEventLoopGroup();

           new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel ch) throws Exception {
                            ch.pipeline()
                                    .addLast("encoder", new MessageToByteEncoder<Packet>() {
                                        @Override
                                        protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {

                                            int id = ArrayUtils.find(PacketManager.out, packet.getClass());
                                            if(id == -1) throw new IOException("Invalid Packet id!");
                                            packet.write(byteBuf);
                                        }
                                    })
                                    .addLast("decoder", new ByteToMessageDecoder() {
                                        @Override
                                        protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

                                            int id = byteBuf.readInt();
                                            if(id == -1) throw new IOException("Invalid packet id!");
                                            Packet packet = (Packet) PacketManager.in[id].newInstance();
                                            packet.read(byteBuf);
                                            list.add(packet);
                                        }
                                    })

                                    .addLast(new NetworkHandler());
                        }
                    }).connect("127.0.0.1", 25847).sync().channel();

    }

    public void setChannel(Channel channel) {
        ProxyWrapper.getINSTANCE().setConnectionToManager(channel);
        this.channel = channel;
        this.sendPacket(new HelloPacket());
    }

    public Channel getChannel() {
        return channel;
    }

    public void sendPacket(Packet packet) {

        getChannel().writeAndFlush(packet, getChannel().voidPromise());

    }
}

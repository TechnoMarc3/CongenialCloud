package de.linux3000.networking;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.networking.packets.pkts.HelloPacket;
import de.linux3000.networking.packets.pkts.ProxyEnabledPacket;
import de.linux3000.utils.ArrayUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

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
                                            System.out.println("encode");
                                            int id = ArrayUtils.find(PacketManager.out, packet.getClass());
                                            if(id < 0 || id >= PacketManager.in.length) System.out.println("Invalid Packet id!");

                                            packet.write(byteBuf);
                                        }
                                    })
                                    .addLast("decoder", new ByteToMessageDecoder() {
                                        @Override
                                        protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
                                            System.out.println("decode");

                                            int id = byteBuf.readInt();
                                            System.out.println(id);
                                            if(id < 0 || id >= PacketManager.in.length){
                                                System.out.println("Invalid packet id!");
                                                return;}
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
        this.channel = channel;
        this.sendPacket(new HelloPacket());
    }

    public Channel getChannel() {
        return channel;
    }

    public void sendPacket(Packet packet) {
        System.out.println("sending packet: " + packet);
        getChannel().writeAndFlush(packet);

    }
}

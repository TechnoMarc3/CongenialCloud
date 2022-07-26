package de.linux3000.networking;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.networking.packets.pkts.HelloPacket;
import de.linux3000.utils.ArrayUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;

import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.IOException;
import java.util.List;

public class NettyServer {



    public NettyServer() throws InterruptedException {
        PacketManager.registerPackets();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

            new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        protected void initChannel(Channel channel) {
                            channel.pipeline()
                                    .addLast("encoder", new MessageToByteEncoder<Packet>() {
                                        @Override
                                        protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {

                                            int id = ArrayUtils.find(PacketManager.out, packet.getClass());


                                            packet.write(byteBuf);
                                        }
                                    })
                                    .addLast("decoder", new ByteToMessageDecoder() {
                                        @Override
                                        protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {


                                            int id = byteBuf.readInt();


                                            Packet packet = (Packet) PacketManager.in[id].newInstance();
                                            if(packet instanceof HelloPacket) {
                                                ((HelloPacket) packet).read(byteBuf, ctx.channel());
                                            }else {
                                                packet.read(byteBuf);
                                            }
                                            list.add(packet);
                                        }
                                    })

                                    .addLast(new NetworkHandler());


                        }
                    }).bind(25847).sync().channel().closeFuture().syncUninterruptibly();

        }




}

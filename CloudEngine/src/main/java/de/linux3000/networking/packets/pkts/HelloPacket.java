package de.linux3000.networking.packets.pkts;

import de.linux3000.Cloud;
import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

public class HelloPacket implements Packet {
    @Override
    public void read(ByteBuf buf) {
        System.out.println("hello");
    }


    public void read(ByteBuf buf, Channel channel) {
        int num = buf.readInt();
        switch (num) {
            case 0:
            case 1:

             //   System.out.println("Proxy Hello");
                break;
            case 2:
                    Cloud.getINSTANCE().addServerConnection(channel);
                break;
            case 3:
                Cloud.getINSTANCE().setConnectionToProxyWrapper(channel);
                break;
        }
    }

    @Override
    public void write(ByteBuf buf) {
        System.out.println("writing");
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));

        System.out.println("buf: " + buf);
    }


}

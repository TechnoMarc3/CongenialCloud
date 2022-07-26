package de.linux3000.networking.packets.pkts;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import io.netty.buffer.ByteBuf;

public class HelloPacket implements Packet {
    @Override
    public void read(ByteBuf buf) {
        System.out.println("hello");
    }

    @Override
    public void write(ByteBuf buf) {

        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        buf.writeInt(3);

    }


}

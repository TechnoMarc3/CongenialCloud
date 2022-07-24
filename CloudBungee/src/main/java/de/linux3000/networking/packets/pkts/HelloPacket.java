package de.linux3000.networking.packets.pkts;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class HelloPacket implements Packet {
    @Override
    public void read(ByteBuf buf) {
        System.out.println("hello");
    }

    @Override
    public void write(ByteBuf buf) {
        System.out.println("writing");
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
       buf.writeInt(1);
        System.out.println("buf: " + buf);
    }


}

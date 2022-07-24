package de.linux3000.networking.packets.pkts;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import io.netty.buffer.ByteBuf;

public class ProxyEnabledPacket implements Packet {



    @Override
    public void read(ByteBuf buf) {

        System.out.println("yes sir");

    }

    @Override
    public void write(ByteBuf buf) {
        System.out.println("writing");
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
    }


}

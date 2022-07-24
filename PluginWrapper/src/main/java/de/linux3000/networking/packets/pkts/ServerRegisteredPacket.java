package de.linux3000.networking.packets.pkts;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import io.netty.buffer.ByteBuf;

public class ServerRegisteredPacket implements Packet {

    int port;


    public ServerRegisteredPacket(int port) {
        this.port = port;

    }

    @Override
    public void read(ByteBuf buf) {
        this.port = buf.readInt();

    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        buf.writeInt(port);

    }



    public int getPort() {
        return port;
    }


}

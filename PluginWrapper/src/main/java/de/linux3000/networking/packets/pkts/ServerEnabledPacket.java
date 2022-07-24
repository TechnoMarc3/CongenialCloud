package de.linux3000.networking.packets.pkts;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class ServerEnabledPacket implements Packet {

    int serverCount;
    int port;
    String address;

    public ServerEnabledPacket(int port, String address) {
        this.port = port;
        this.address = address;
    }

    @Override
    public void read(ByteBuf buf) {
        this.port = buf.readInt();
        this.address = buf.toString(StandardCharsets.UTF_8);
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        buf.writeInt(port);
        buf.writeCharSequence(address, StandardCharsets.UTF_8);
    }

    public int getServerCount() {
        return serverCount;
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return address;
    }
}

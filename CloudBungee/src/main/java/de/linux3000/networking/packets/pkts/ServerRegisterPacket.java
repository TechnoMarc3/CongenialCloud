package de.linux3000.networking.packets.pkts;

import de.linux3000.Main;
import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;

public class ServerRegisterPacket implements Packet {

    String name;
    int port;
    String address;

    public ServerRegisterPacket(String name, int port, String address) {
        this.name = name;
        this.port = port;
        this.address = address;
    }

    @Override
    public void read(ByteBuf buf) {
        this.name = buf.toString(StandardCharsets.UTF_8);
        this.port = buf.readInt();
        this.address = buf.toString(StandardCharsets.UTF_8);

        Main.getINSTANCE().addServiceToProxy(this.name, this.port, this.address);

    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        buf.writeCharSequence(name, StandardCharsets.UTF_8);
        buf.writeInt(port);
        buf.writeCharSequence(address, StandardCharsets.UTF_8);

    }


    public int getPort() {
        return port;
    }

}


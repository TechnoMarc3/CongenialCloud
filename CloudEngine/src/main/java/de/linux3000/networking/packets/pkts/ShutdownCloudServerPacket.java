package de.linux3000.networking.packets.pkts;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.UUID;

public class ShutdownCloudServerPacket implements Packet {

    UUID uuid;

    public ShutdownCloudServerPacket(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public void read(ByteBuf buf) {

    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        ByteBufUtil.writeUtf8(buf, uuid.toString());
    }
}

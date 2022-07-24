package de.linux3000.networking.packets.pkts;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.util.UUID;

public class SendPlayerToServerPacket implements Packet {

    UUID uuid;
    UUID serverUUID;

    public SendPlayerToServerPacket(UUID uuid, UUID serverUUID) {
        this.uuid = uuid;
        this.serverUUID = serverUUID;
    }

    @Override
    public void read(ByteBuf buf) {

    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        ByteBufUtil.writeUtf8(buf, uuid.toString());
        ByteBufUtil.writeUtf8(buf, serverUUID.toString());
    }
}

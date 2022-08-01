package de.linux3000.networking.packets.pkts;

import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import de.linux3000.utils.StringUtils;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CloudRegisterPlayerPacket implements Packet {

    private String name;
    private UUID uuid;

    public CloudRegisterPlayerPacket(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public CloudRegisterPlayerPacket() {
    }

    @Override
    public void read(ByteBuf buf) {
        int nameLength = buf.readInt();
        name = (String) buf.readCharSequence(nameLength, StandardCharsets.UTF_8);
        int uuidLength = buf.readInt();
        uuid = UUID.fromString((String) buf.readCharSequence(uuidLength, StandardCharsets.UTF_8));
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        buf.writeInt(StringUtils.length(name));
        buf.writeCharSequence(name, StandardCharsets.UTF_8);
        buf.writeInt(StringUtils.length(uuid.toString()));
        buf.writeCharSequence(uuid.toString(), StandardCharsets.UTF_8);
    }
}

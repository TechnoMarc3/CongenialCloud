package de.linux3000.networking.packets.pkts;

import de.linux300.api.CloudApi;
import de.linux300.api.player.CloudPlayer;
import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import de.linux3000.utils.StringUtils;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CloudUpdatePlayerPacket implements Packet {

    private UUID uuid;
    private String connectedServer;
    private String connectedProxy;

    public CloudUpdatePlayerPacket(UUID uuid, String connectedServer, String connectedProxy) {
        this.uuid = uuid;

        this.connectedServer = connectedServer;
        this.connectedProxy = connectedProxy;
    }

    public CloudUpdatePlayerPacket() {
    }

    @Override
    public void read(ByteBuf buf) {
        int uuidLength = buf.readInt();
        int conSLength = buf.readInt();
        int conPLength = buf.readInt();
        uuid = UUID.fromString((String) buf.readCharSequence(uuidLength, StandardCharsets.UTF_8));
        CloudPlayer player = (CloudPlayer) CloudApi.getINSTANCE().getPlayerManager().getPlayerByUUID(uuid);
        String conS = (String) buf.readCharSequence(conSLength, StandardCharsets.UTF_8);
        String conP = (String) buf.readCharSequence(conPLength, StandardCharsets.UTF_8);
        if(!conS.equalsIgnoreCase("null")) {
            connectedServer = conS;
            player.setServer(CloudApi.getINSTANCE().getServerManager().getServerByUUID(UUID.fromString(connectedServer)));
        }
        if(!conP.equalsIgnoreCase("null")) {
            connectedProxy = conP;
            player.setProxy(CloudApi.getINSTANCE().getServerManager().getServerByUUID(UUID.fromString(connectedProxy)));
        }
        CloudApi.getINSTANCE().getPlayerManager().update(player);
    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        buf.writeInt(StringUtils.length(uuid.toString()));
            buf.writeInt(StringUtils.length(connectedServer));
        buf.writeInt(StringUtils.length(connectedProxy));
        buf.writeCharSequence(uuid.toString(), StandardCharsets.UTF_8);
            buf.writeCharSequence(connectedServer, StandardCharsets.UTF_8);
            buf.writeCharSequence(connectedProxy, StandardCharsets.UTF_8);

    }
}

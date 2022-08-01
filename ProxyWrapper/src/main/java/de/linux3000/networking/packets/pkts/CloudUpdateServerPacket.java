package de.linux3000.networking.packets.pkts;

import de.linux300.api.CloudApi;
import de.linux300.api.event.events.CloudServerUpdateEvent;
import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.CloudServer;
import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import de.linux3000.utils.StringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CloudUpdateServerPacket implements Packet {


    UUID uuid;
    int playeramount;
    String host;
    int port;
    boolean isFull;
    boolean isOnline;
    List<UUID> player;

    private int updateCount;

    public CloudUpdateServerPacket(UUID uuid, int playeramount, String host, int port, boolean isFull, boolean isOnline, List<UUID> player) {
        this.uuid = uuid;
        this.playeramount = playeramount;
        this.host = host;
        this.port = port;
        this.isFull = isFull;
        this.isOnline = isOnline;
        this.player = player;

        System.out.println("instance: " + this);
    }

    public CloudUpdateServerPacket() {
    }

    @Override
    public void read(ByteBuf buf) {

    }

    public void read(ByteBuf buf, Channel channel) {
        int uuiidLength = buf.readInt();
        this.uuid = UUID.fromString((String) buf.readCharSequence(uuiidLength, StandardCharsets.UTF_8));
        this.playeramount = buf.readInt();
        int hostLength = buf.readInt();
        this.host = (String) buf.readCharSequence(hostLength, StandardCharsets.UTF_8);
        this.port = buf.readInt();
        this.isFull = buf.readBoolean();
        this.isOnline = buf.readBoolean();
        this.player = new ArrayList<>();
        while(buf.readableBytes() > 4) {
            int length = buf.readInt();
            this.player.add(UUID.fromString((String) buf.readCharSequence(length, StandardCharsets.UTF_8)));
        }
        CloudServer server = (CloudServer) CloudApi.getINSTANCE().getServerManager().getServerByUUID(uuid);

        server.setHost(host);
        server.setPort(port);
        server.setFull(isFull);
        server.setOnline(isOnline);
        List<ICloudPlayer> players = new ArrayList<>();
        for(UUID uuid : player) {
            players.add(CloudApi.getINSTANCE().getPlayerManager().getPlayerByUUID(uuid));
        }
        server.setPlayers(players);
        CloudApi.getINSTANCE().getEventManager().callEvent(new CloudServerUpdateEvent(server));


    }

    @Override
    public void write(ByteBuf buf) {
        System.out.println("writing");
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        buf.writeInt(StringUtils.length(uuid.toString()));
        buf.writeCharSequence(uuid.toString(), StandardCharsets.UTF_8);
        buf.writeInt(playeramount);
        buf.writeInt(StringUtils.length(host));
        buf.writeCharSequence(host, StandardCharsets.UTF_8);
        buf.writeInt(port);
        buf.writeBoolean(isFull);
        buf.writeBoolean(isOnline);
        for(UUID uuidP : player) {
            buf.writeInt(StringUtils.length(uuidP.toString()));
            buf.writeCharSequence(uuidP.toString(), StandardCharsets.UTF_8);
        }
    }
}

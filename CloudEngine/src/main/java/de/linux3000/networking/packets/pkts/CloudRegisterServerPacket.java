package de.linux3000.networking.packets.pkts;

import de.linux300.api.CloudApi;
import de.linux300.api.event.IEvent;
import de.linux300.api.event.events.CloudServerRegisterEvent;
import de.linux300.api.server.CloudServer;
import de.linux300.api.server.ServerTypes;
import de.linux3000.Cloud;
import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.PacketManager;
import de.linux3000.utils.ArrayUtils;
import de.linux3000.utils.StringUtils;
import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class CloudRegisterServerPacket implements Packet {


    private UUID uuid;
    private String serverType;
    private String host;
    private int port;
    private String name;

    public CloudRegisterServerPacket(UUID uuid, String serverType, String host, int port, String name) {
        this.uuid = uuid;
        this.serverType = serverType;
        this.host = host;
        this.port = port;
        this.name = name;
    }

    public CloudRegisterServerPacket() {
    }

    @Override
    public void read(ByteBuf buf) {
        int uuidLength = buf.readInt();
        int serverTypeLength = buf.readInt();;
        String preUUID = (String) buf.readCharSequence(uuidLength, StandardCharsets.UTF_8);
        String preType =  (String) buf.readCharSequence(serverTypeLength, StandardCharsets.UTF_8);
        this.uuid = UUID.fromString(preUUID);

        this.serverType = preType;
        this.port = buf.readInt();
        int length = buf.readInt();
        this.host = (String) buf.readCharSequence(length, StandardCharsets.UTF_8);
        int nameLength = buf.readInt();
        this.name = (String) buf.readCharSequence(nameLength, StandardCharsets.UTF_8);

        CloudApi.getINSTANCE().getServerManager().registerServer(new CloudServer(name, uuid, host, port, ServerTypes.valueOf(serverType)));
        if(ServerTypes.valueOf(serverType).equals(ServerTypes.SPIGOT)) {
        Cloud.getINSTANCE().getConnectionToProxyWrapper().writeAndFlush(this);
            System.out.println("send server to proxy");
        }


    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        buf.writeInt(StringUtils.length(uuid.toString()));
        buf.writeInt(StringUtils.length(serverType));
        buf.writeCharSequence(uuid.toString(), StandardCharsets.UTF_8);
        buf.writeCharSequence(serverType, StandardCharsets.UTF_8);
        buf.writeInt(port);
        buf.writeInt(StringUtils.length(host));
        buf.writeCharSequence(host, StandardCharsets.UTF_8);
        buf.writeInt(StringUtils.length(name));
        buf.writeCharSequence(name, StandardCharsets.UTF_8);

    }
}

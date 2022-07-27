package de.linux3000.networking.packets.pkts;

import de.linux300.api.CloudApi;
import de.linux300.api.event.IEvent;
import de.linux300.api.event.events.CloudServerRegisterEvent;
import de.linux300.api.server.CloudServer;
import de.linux300.api.server.ServerTypes;
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

    public CloudRegisterServerPacket(UUID uuid, String serverType) {
        this.uuid = uuid;
        this.serverType = serverType;
    }

    public CloudRegisterServerPacket() {
    }

    @Override
    public void read(ByteBuf buf) {
        int uuidLength = buf.readInt();
        int serverTypeLength = buf.readInt();;
        String preUUID = (String) buf.readCharSequence(uuidLength, StandardCharsets.UTF_8);
        String preType =  (String) buf.readCharSequence(serverTypeLength, StandardCharsets.UTF_8);
        System.out.println(preUUID);
        System.out.println(preType);
     this.uuid = UUID.fromString(preUUID);

      this.serverType = preType;

        CloudApi.getINSTANCE().getServerManager().registerServer(new CloudServer(uuid,this.serverType));

    }

    @Override
    public void write(ByteBuf buf) {
        buf.writeInt(ArrayUtils.find(PacketManager.out, this.getClass()));
        buf.writeInt(StringUtils.length(uuid.toString()));
        buf.writeInt(StringUtils.length(serverType));
        buf.writeCharSequence(uuid.toString(), StandardCharsets.UTF_8);
        buf.writeCharSequence(serverType, StandardCharsets.UTF_8);

    }
}

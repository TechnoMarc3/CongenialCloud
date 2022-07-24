package de.linux3000.impl;

import de.linux300.api.manager.impl.AbstractCloudPlayerManager;
import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;
import de.linux3000.ServerWrapper;
import de.linux3000.networking.packets.pkts.BanPlayerPacket;
import de.linux3000.networking.packets.pkts.SendPlayerToServerPacket;
import de.linux3000.networking.packets.pkts.WarnPlayerPacket;

public class CloudPlayerManagerImpl extends AbstractCloudPlayerManager {
    @Override
    public boolean sendPlayerToCloudServer(ICloudServer iCloudServer, ICloudPlayer iCloudPlayer) {
        ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new SendPlayerToServerPacket(iCloudPlayer.uuid(), iCloudServer.uniqueId()));
        return false;
    }

    @Override
    public boolean banPlayer(ICloudPlayer iCloudPlayer, String s) {
        ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new BanPlayerPacket(iCloudPlayer.uuid(), s));
        return false;
    }

    @Override
    public boolean warnPlayer(ICloudPlayer iCloudPlayer, String s) {
        ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new WarnPlayerPacket(iCloudPlayer.uuid(), s));
        return false;
    }


}

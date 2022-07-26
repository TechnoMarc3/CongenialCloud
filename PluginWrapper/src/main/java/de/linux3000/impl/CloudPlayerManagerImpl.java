package de.linux3000.impl;

import de.linux300.api.manager.impl.AbstractCloudPlayerManager;
import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;
import de.linux3000.ServerWrapper;

import java.util.List;

public class CloudPlayerManagerImpl extends AbstractCloudPlayerManager {
    @Override
    public boolean sendPlayerToCloudServer(ICloudServer iCloudServer, ICloudPlayer iCloudPlayer) {
       // ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new SendPlayerToServerPacket(iCloudPlayer.uuid(), iCloudServer.uniqueId()));
        return false;
    }

    @Override
    public boolean banPlayer(ICloudPlayer iCloudPlayer, String s) {
    //     ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new BanPlayerPacket(iCloudPlayer.uuid(), s));
        return false;
    }

    @Override
    public boolean kickPlayer(ICloudPlayer iCloudPlayer, String s) {
      //  ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new WarnPlayerPacket(iCloudPlayer.uuid(), s));
        return false;
    }

    @Override
    public void sendMessageToPlayer(ICloudPlayer iCloudPlayer, String s) {

    }

    @Override
    public void sendActionBar(ICloudPlayer iCloudPlayer, String s) {

    }

    @Override
    public void sendTablist(ICloudPlayer iCloudPlayer, List<String> list, List<String> list1) {

    }

    @Override
    public boolean hasPermission(ICloudPlayer iCloudPlayer, String s) {
        return false;
    }

    @Override
    public void sendPlayerToLobby(ICloudPlayer iCloudPlayer) {

    }


}

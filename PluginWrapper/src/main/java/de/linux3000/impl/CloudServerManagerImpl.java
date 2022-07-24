package de.linux3000.impl;

import de.linux300.api.manager.impl.AbstractCloudServerManager;

import de.linux300.api.server.ICloudServer;
import de.linux3000.ServerWrapper;
import de.linux3000.networking.packets.pkts.ShutdownCloudServerPacket;


public class CloudServerManagerImpl extends AbstractCloudServerManager {


    @Override
    public void shutdownCloudServer(ICloudServer iCloudServer) {
        ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new ShutdownCloudServerPacket(iCloudServer.uniqueId()));
    }
}

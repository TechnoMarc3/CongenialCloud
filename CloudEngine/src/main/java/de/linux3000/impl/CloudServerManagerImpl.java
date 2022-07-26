package de.linux3000.impl;

import de.linux300.api.manager.impl.AbstractCloudServerManager;

import de.linux300.api.server.ICloudServer;



public class CloudServerManagerImpl extends AbstractCloudServerManager {


    @Override
    public void shutdownCloudServer(ICloudServer iCloudServer) {
      //  ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new ShutdownCloudServerPacket(iCloudServer.uniqueId()));
    }

    @Override
    public void startProxyServer(ICloudServer iCloudServer) {
        //TODO
    }
}

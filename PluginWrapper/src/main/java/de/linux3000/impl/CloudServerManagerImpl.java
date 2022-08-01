package de.linux3000.impl;

import de.linux300.api.manager.impl.AbstractCloudServerManager;
import de.linux300.api.server.ICloudServer;
import de.linux3000.ServerWrapper;
import org.bukkit.Bukkit;



public class CloudServerManagerImpl extends AbstractCloudServerManager {

    @Override
    public void shutdownCloudServer(ICloudServer iCloudServer) {


     //   ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new ShutdownCloudServerPacket(iCloudServer.uniqueId()));

        //TODO -> check if it is this CloudServer

        if (ServerWrapper.getINSTANCE().isCloudPluginLoaded()) {
            Bukkit.shutdown();
        } else {
            ServerWrapper.getINSTANCE().stop();
        }

    }

    @Override
    public void startProxyServer(ICloudServer iCloudServer) {
        //TODO
    }
}

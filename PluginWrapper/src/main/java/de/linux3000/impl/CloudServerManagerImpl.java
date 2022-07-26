package de.linux3000.impl;

import de.linux300.api.manager.impl.AbstractCloudServerManager;
import de.linux300.api.server.ICloudServer;
import de.linux3000.ServerWrapper;

import java.lang.reflect.InvocationTargetException;


public class CloudServerManagerImpl extends AbstractCloudServerManager {

    @Override
    public void shutdownCloudServer(ICloudServer iCloudServer) {


     //   ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new ShutdownCloudServerPacket(iCloudServer.uniqueId()));

        //TODO -> check if it is this CloudServer

        if (ServerWrapper.getINSTANCE().isCloudPluginLoaded()) {
            try {
                Class.forName("Bukkit").getMethod("shutdown").invoke(null);
            } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            ServerWrapper.getINSTANCE().stop();
        }

    }

    @Override
    public void startProxyServer(ICloudServer iCloudServer) {
        //TODO
    }
}

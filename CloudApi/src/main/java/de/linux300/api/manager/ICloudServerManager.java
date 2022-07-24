package de.linux300.api.manager;

import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;

import java.util.UUID;

public interface ICloudServerManager {

    ICloudServer getServerByName(String name);
    ICloudServer getServerByUUID(UUID uuid);


    void addPlayerToServer(ICloudServer server, ICloudPlayer player) ;

    void shutdownCloudServer(ICloudServer server);

    void registerServer(ICloudServer server);
    void unregisterServer(ICloudServer server);


    void removePlayerFromServer(ICloudServer server, ICloudPlayer player);



}

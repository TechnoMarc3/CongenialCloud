package de.linux300.api.manager;

import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;

import java.util.List;
import java.util.UUID;

public interface ICloudServerManager {

    ICloudServer getServerByName(String name);
    ICloudServer getServerByUUID(UUID uuid);

    List<ICloudServer> getAllRegisteredServer();


    void addPlayerToServer(ICloudServer server, ICloudPlayer player) ;

    void shutdownCloudServer(ICloudServer server);
    void startProxyServer(ICloudServer server);

    void registerServer(ICloudServer server);
    void unregisterServer(ICloudServer server);


    void removePlayerFromServer(ICloudServer server, ICloudPlayer player);

    void update(ICloudServer server);



}

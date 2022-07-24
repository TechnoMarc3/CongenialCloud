package de.linux300.api.manager.impl;

import de.linux300.api.manager.ICloudServerManager;
import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCloudServerManager implements ICloudServerManager {

    List<ICloudServer> servers = new ArrayList<>();


    @Override
    public ICloudServer getServerByName(String name) {
        for(ICloudServer server : servers) {
            if (server.name().equalsIgnoreCase(name)) {
                return server;
            }
        }
        return null;
    }

    @Override
    public ICloudServer getServerByUUID(UUID uuid) {
        for(ICloudServer server : servers) {
            if (server.uniqueId().equals(uuid)) {
                return server;
            }
        }
        return null;
    }

    @Override
    public void addPlayerToServer(ICloudServer server, ICloudPlayer player) {
        server.player().add(player);
    }



    @Override
    public void registerServer(ICloudServer server) {
        servers.add(server);
    }

    @Override
    public void unregisterServer(ICloudServer server) {
        servers.remove(server);
    }

    @Override
    public void removePlayerFromServer(ICloudServer server, ICloudPlayer player) {
        server.player().remove(player);
    }
}

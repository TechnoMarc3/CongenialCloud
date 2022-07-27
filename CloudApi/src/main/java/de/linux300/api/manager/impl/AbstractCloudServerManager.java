package de.linux300.api.manager.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.event.events.CloudServerRegisterEvent;
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
    public List<ICloudServer> getAllRegisteredServer() {
        return servers;
    }

    @Override
    public void registerServer(ICloudServer server) {
        System.out.println("server: " + server);
        servers.add(server);

        System.out.println(servers);
        System.out.println("registered");

        CloudApi.getINSTANCE().getEventManager().callEvent(new CloudServerRegisterEvent(server));
    }

    @Override
    public void unregisterServer(ICloudServer server) {
        servers.remove(server);
    }

    @Override
    public void removePlayerFromServer(ICloudServer server, ICloudPlayer player) {
        server.player().remove(player);
    }

    @Override
    public void update(ICloudServer server) {
        this.servers.remove(server);
        this.servers.add(server);
    }
}

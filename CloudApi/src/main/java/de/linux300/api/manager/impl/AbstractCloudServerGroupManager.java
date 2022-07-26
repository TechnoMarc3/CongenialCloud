package de.linux300.api.manager.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.ICloudServerGroupManager;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCloudServerGroupManager implements ICloudServerGroupManager {

    List<ICloudServerGroup> serverGroups = new ArrayList<>();

    @Override
    public ICloudServerGroup getServerGroupByName(String name) {
        for(ICloudServerGroup server : serverGroups) {
            if (server.name().equalsIgnoreCase(name)) {
                return server;
            }
        }
        return null;
    }

    @Override
    public ICloudServerGroup getServerGroupByUUID(UUID uuid) {
        for(ICloudServerGroup server : serverGroups) {
            if (server.uniqueID().equals(uuid)) {
                return server;
            }
        }
        return null;
    }

    @Override
    public void registerCloudServerGroup(ICloudServerGroup group) {
        serverGroups.add(group);
    }

    @Override
    public void unregisterCloudServerGroup(ICloudServerGroup group) {
        serverGroups.remove(group);
    }

    public List<ICloudServerGroup> getServerGroups() {
        return serverGroups;
    }

    @Override
    public void addServerToServerGroup(ICloudServer server, ICloudServerGroup group) {
        group.allServer().add(server);
    }

    @Override
    public void removeServerFromServerGroup(ICloudServer server, ICloudServerGroup group) {
        group.allServer().remove(server);
    }



    @Override
    public void shutdownAllServer(ICloudServerGroup group) {
        group.allServer().forEach(server -> {
            CloudApi.getINSTANCE().getServerManager().shutdownCloudServer(server);
        });

    }
}

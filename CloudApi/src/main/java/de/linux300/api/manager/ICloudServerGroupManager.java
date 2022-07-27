package de.linux300.api.manager;

import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;

import java.util.UUID;

public interface ICloudServerGroupManager {

    ICloudServerGroup getServerGroupByName(String name);
    ICloudServerGroup getServerGroupByUUID(UUID uuid);

    void registerCloudServerGroup(ICloudServerGroup group);
    void unregisterCloudServerGroup(ICloudServerGroup group);

    void addServerToServerGroup(ICloudServer server, ICloudServerGroup group);
    void removeServerFromServerGroup(ICloudServer server, ICloudServerGroup group);
    void startNewService(ICloudServerGroup group);
    void shutdownAllServer(ICloudServerGroup group);

    void update(ICloudServerGroup group);

}

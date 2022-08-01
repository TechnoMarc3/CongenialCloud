package de.linux300.api.player;

import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;

import java.util.UUID;

public interface ICloudPlayer {

    String name();
    UUID uuid();


    //TODO -> Server connect state
    ICloudServer getConnectedServer();
    ICloudServer getConnectedProxy();
    String getConnectedServerName();
    String getConnectedProxyName();

}

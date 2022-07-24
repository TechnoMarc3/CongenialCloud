package de.linux300.api.serverGroup;

import de.linux300.api.server.ICloudServer;
import de.linux300.api.versions.Versions;


import java.util.List;
import java.util.UUID;

public interface ICloudServerGroup {

    String name();
    int maxMemory();
    int playerAmount();

    UUID uniqueID();


    List<ICloudServer> allServer();
    int registeredServerCount();
    int onlineServerCount();

    int percentageForNewServer();
    int playerForNewServer();
    Versions version();
}

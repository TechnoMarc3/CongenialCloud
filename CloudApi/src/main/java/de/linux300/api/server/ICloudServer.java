package de.linux300.api.server;

import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.serverGroup.ICloudServerGroup;

import java.io.File;
import java.util.List;
import java.util.UUID;

public interface ICloudServer {


    //TODO -> Server state
    String name();
    int server();
    UUID uniqueId();
    ICloudServerGroup serverGroup();
    int maxMemory();
    int minMemory();
    int playerAmount();
    String host();
    int port();
    File version();
    boolean isFull();
    boolean isOnline();
    List<ICloudPlayer> player();
    ServerTypes serverType();

}

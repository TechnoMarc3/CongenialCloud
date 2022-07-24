package de.linux3000.base;

import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;
import de.linux3000.Cloud;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.UUID;

public class CloudServer implements ICloudServer {

    int server;
    int ram;
    String name;
    List<ICloudPlayer> players;
    ICloudServerGroup group;

    public CloudServer(int server, int ram, String name, ICloudServerGroup group) {
        this.server = server;
        this.ram = ram;
        this.name = name;
        this.group = group;
    }



    @Override
    public int server() {
        return server;
    }

    @Override
    public UUID uniqueId() {
        return null;
    }

    @Override
    public int maxMemory() {
        return ram;
    }

    @Override
    public int minMemory() {
        return 0;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public int playerAmount() {
        return this.players.size();
    }

    @Override
    public String host() {
        return null;
    }

    @Override
    public int port() {
        return 0;
    }

    @Override
    public String version() {
        return null;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public boolean isOnline() {
        return false;
    }

    @Override
    public List<ICloudPlayer> player() {
        return this.players;
    }


    @Override
    public ICloudServerGroup serverGroup() {
        return group;
    }

}

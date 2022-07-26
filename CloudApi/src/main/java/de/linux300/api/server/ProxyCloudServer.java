package de.linux300.api.server;

import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.serverGroup.ICloudServerGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProxyCloudServer implements ICloudServer{

    private UUID uuid;
    private File version;
    boolean full;
    boolean online;

    private List<ICloudPlayer> players;



    public ProxyCloudServer(File version) {
        uuid = UUID.randomUUID();
        this.version = version;
        players = new ArrayList<>();
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    @Override
    public String name() {
        return "Proxy";
    }

    @Override
    public int server() {
        return 0;
    }

    @Override
    public UUID uniqueId() {
        return uuid;
    }

    @Override
    public ICloudServerGroup serverGroup() {
        return null;
    }

    @Override
    public int maxMemory() {
        return 0;
    }

    @Override
    public int minMemory() {
        return 0;
    }

    @Override
    public int playerAmount() {
        return 0;
    }

    @Override
    public String host() {
        return "0.0.0.0";
    }

    @Override
    public int port() {
        return 25577;
    }

    @Override
    public File version() {
        return version;
    }

    @Override
    public boolean isFull() {
        return full;
    }

    @Override
    public boolean isOnline() {
        return online;
    }

    @Override
    public List<ICloudPlayer> player() {
        return players;
    }

    @Override
    public ServerTypes serverType() {
        return ServerTypes.PROXY;
    }
}

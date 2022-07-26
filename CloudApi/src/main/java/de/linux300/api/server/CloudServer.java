package de.linux300.api.server;

import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;

import java.io.File;
import java.util.List;
import java.util.UUID;

public class CloudServer implements ICloudServer {

    int server;
    int ram;
    String name;
    List<ICloudPlayer> players;
    ICloudServerGroup group;
    UUID uuid;
    String host;
    int port;
    String version;
    boolean isFull;
    boolean isOnline;
    ServerTypes serverTypes;

    public CloudServer(int server, int ram, String name, ICloudServerGroup group, ServerTypes types) {
        this.server = server;
        this.ram = ram;
        this.name = name;
        this.group = group;
        this.uuid = UUID.randomUUID();
        this.serverTypes = types;


    }

    public void setServer(int server) {
        this.server = server;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayers(List<ICloudPlayer> players) {
        this.players = players;
    }

    public void setGroup(ICloudServerGroup group) {
        this.group = group;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    @Override
    public int server() {
        return server;
    }

    @Override
    public UUID uniqueId() {
        return uuid;
    }

    @Override
    public int maxMemory() {
        return ram;
    }

    @Override
    public int minMemory() {
        return ram;
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
    public File version() {
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
    public ServerTypes serverType() {
        return this.serverTypes;
    }


    @Override
    public ICloudServerGroup serverGroup() {
        return group;
    }
}
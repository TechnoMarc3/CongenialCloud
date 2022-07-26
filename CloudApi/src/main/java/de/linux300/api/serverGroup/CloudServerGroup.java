package de.linux300.api.serverGroup;

import de.linux300.api.server.ICloudServer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CloudServerGroup implements ICloudServerGroup {

    private String name;
    private List<ICloudServer> server = new ArrayList<>();
    private File version;
    private int percentageForNewServer;
    private int playerForNewServer;
    private int ram;
    private int registeredServer;

    private UUID uuid;

    public CloudServerGroup(String name, File version, int percentageForNewServer, int playerForNewServer, int ram) {
        this.name = name;
        this.version = version;
        this.percentageForNewServer = percentageForNewServer;
        this.playerForNewServer = playerForNewServer;
        this.ram = ram;

        this.uuid = UUID.randomUUID();
    }

    public CloudServerGroup(String name, int ram, int server,File version) {
        this.name = name;
        this.ram = ram;
        this.version = version;
        this.server = new ArrayList<>();
        this.registeredServer = server;



        this.uuid = UUID.randomUUID();
    }

    public void setServer(List<ICloudServer> server) {
        this.server = server;
    }

    public void setVersion(File version) {
        this.version = version;
    }

    public void setPercentageForNewServer(int percentageForNewServer) {
        this.percentageForNewServer = percentageForNewServer;
    }

    public void setPlayerForNewServer(int playerForNewServer) {
        this.playerForNewServer = playerForNewServer;
    }

    public void setRegisteredServer(int registeredServer) {
        this.registeredServer = registeredServer;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public int maxMemory() {
        return ram;
    }

    @Override
    public List<ICloudServer> allServer() {
        return this.server;
    }

    @Override
    public int registeredServerCount() {
        return registeredServer;
    }

    @Override
    public int onlineServerCount() {
        return 0;
    }

    @Override
    public int percentageForNewServer() {
        return this.percentageForNewServer;
    }

    @Override
    public int playerForNewServer() {
        return this.playerForNewServer;
    }


    @Override
    public File version() {
        return this.version;
    }

    @Override
    public int playerAmount() {
        int i = 0;
        for(ICloudServer server : this.server) {
            i+= server.playerAmount();
        }
        return i;
    }

    @Override
    public UUID uniqueID() {
        return uuid;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setName(String name) {
        this.name = name;
    }
}

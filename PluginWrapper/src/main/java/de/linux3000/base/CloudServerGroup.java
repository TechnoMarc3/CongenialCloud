package de.linux3000.base;

import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;
import de.linux300.api.versions.Versions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CloudServerGroup implements ICloudServerGroup {

    private String name;
    private List<ICloudServer> server = new ArrayList<>();
    private Versions version;
    private int percentageForNewServer;
    private int playerForNewServer;
    private int ram;

    private UUID uuid;

    public CloudServerGroup(String name, Versions version, int percentageForNewServer, int playerForNewServer, int ram) {
        this.name = name;
        this.version = version;
        this.percentageForNewServer = percentageForNewServer;
        this.playerForNewServer = playerForNewServer;
        this.ram = ram;

        this.uuid = UUID.randomUUID();
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
        return 0;
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
    public Versions version() {
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
        return null;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setName(String name) {
        this.name = name;
    }
}

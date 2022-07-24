package de.linux3000.cache.caches;

import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;
import de.linux300.api.versions.Versions;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CachedCloudServerGroup implements ICloudServerGroup {




    String name;
    int percentageForNew;
    int playerForNew;
    int ram;
    int maxPlayer;
    Versions versions;

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(Versions versions) {
        this.versions = versions;
    }

    public void setPercentageForNew(int percentageForNew) {
        this.percentageForNew = percentageForNew;
    }

    public void setPlayerForNew(int playerForNew) {
        this.playerForNew = playerForNew;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public void setMaxPlayer(int maxPlayer) {
        this.maxPlayer = maxPlayer;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public List<ICloudServer> allServer() {
        return new ArrayList<>();
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
        return percentageForNew;
    }

    @Override
    public int playerForNewServer() {
        return playerForNew;
    }

    @Override
    public int maxMemory() {
        return ram;
    }


    @Override
    public int playerAmount() {
        return 0;
    }

    @Override
    public UUID uniqueID() {
        return null;
    }

    @Override
    public Versions version() {
        return versions;
    }
}

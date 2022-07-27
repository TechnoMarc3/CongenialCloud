package de.linux300.api.manager.impl;

import de.linux300.api.manager.ICloudPlayerManager;
import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractCloudPlayerManager implements ICloudPlayerManager {


    List<ICloudPlayer> players = new ArrayList<>();

    @Override
    public void addPlayerToCloud(ICloudPlayer player) {
        players.add(player);
    }

    @Override
    public void removePlayerFromCloud(ICloudPlayer player) {
        players.remove(player);
    }

    @Override
    public ICloudPlayer getPlayerByName(String name) {
        for(ICloudPlayer player : players) {
            if (player.name().equalsIgnoreCase(name)) {
                return player;
            }
        }

        return null;
    }

    @Override
    public ICloudPlayer getPlayerByUUID(UUID uuid) {
        for(ICloudPlayer player : players) {
            if (player.uuid().equals(uuid)) {
                return player;
            }
        }

        return null;
    }

    @Override
    public void update(ICloudPlayer player) {
        this.players.remove(player);
        this.players.add(player);
    }
}

package de.linux300.api.manager.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.event.events.CloudPlayerRegisterEvent;
import de.linux300.api.event.events.CloudPlayerUpdateEvent;
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
        if(!this.players.contains(player)) {
            System.out.println("player: " + player);
            players.add(player);
            System.out.println(players);
            System.out.println("registered");
            CloudApi.getINSTANCE().getEventManager().callEvent(new CloudPlayerRegisterEvent(player));
        }

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
    public List<ICloudPlayer> getAllRegisteredPlayers() {
        return players;
    }

    @Override
    public void update(ICloudPlayer player) {

        this.players.remove(player);
        this.players.add(player);

        CloudApi.getINSTANCE().getEventManager().callEvent(new CloudPlayerUpdateEvent(player));
    }
}

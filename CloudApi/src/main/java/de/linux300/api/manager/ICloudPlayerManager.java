package de.linux300.api.manager;

import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;

import java.util.UUID;

public interface ICloudPlayerManager {

    //true - successful
    //false - error occurred
    boolean sendPlayerToCloudServer(ICloudServer server, ICloudPlayer player);

    ICloudPlayer getPlayerByName(String name);

    void addPlayerToCloud(ICloudPlayer player);
    void removePlayerFromCloud(ICloudPlayer player);

    ICloudPlayer getPlayerByUUID(UUID uuid);

    boolean banPlayer(ICloudPlayer player, String reason);
    boolean warnPlayer(ICloudPlayer player, String reason); //3* warn = ban

}

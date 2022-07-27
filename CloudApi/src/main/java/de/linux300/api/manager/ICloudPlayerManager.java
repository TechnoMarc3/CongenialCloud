package de.linux300.api.manager;

import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.ICloudServer;

import java.util.List;
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
    boolean kickPlayer(ICloudPlayer player, String reason); //3* warn = ban

    void sendMessageToPlayer(ICloudPlayer player, String message);
    void sendActionBar(ICloudPlayer player, String actionbar);
    void sendTablist(ICloudPlayer player, List<String> headers, List<String> footers);
    boolean hasPermission(ICloudPlayer player, String permission);
    void sendPlayerToLobby(ICloudPlayer player);

    void update(ICloudPlayer player);

}

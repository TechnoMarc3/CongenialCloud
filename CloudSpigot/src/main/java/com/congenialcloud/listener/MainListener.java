package com.congenialcloud.listener;

import com.congenialcloud.Main;
import de.linux300.api.CloudApi;
import de.linux300.api.player.ICloudPlayer;
import de.linux300.api.server.CloudServer;
import de.linux3000.ServerWrapper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.List;

public class MainListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        System.out.println(event.getPlayer().getUniqueId());
        CloudApi.getINSTANCE().getPlayerManager().getAllRegisteredPlayers().forEach(player -> System.out.println(player.uuid()));

        ICloudPlayer player = CloudApi.getINSTANCE().getPlayerManager().getPlayerByUUID(event.getPlayer().getUniqueId());
        System.out.println(ServerWrapper.getINSTANCE().getThisServer());
        CloudApi.getINSTANCE().getServerManager().addPlayerToServer(ServerWrapper.getINSTANCE().getThisServer(), player);
        System.out.println("added player to server in api");

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
      ICloudPlayer player = CloudApi.getINSTANCE().getPlayerManager().getPlayerByUUID(event.getPlayer().getUniqueId());
      CloudApi.getINSTANCE().getServerManager().removePlayerFromServer(ServerWrapper.getINSTANCE().getThisServer(), player);

    }

}

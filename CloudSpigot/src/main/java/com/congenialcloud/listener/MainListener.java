package com.congenialcloud.listener;

import com.congenialcloud.Main;
import de.linux300.api.CloudApi;
import de.linux300.api.player.ICloudPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MainListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        ICloudPlayer player = CloudApi.getINSTANCE().getPlayerManager().getPlayerByUUID(event.getPlayer().getUniqueId());
        CloudApi.getINSTANCE().getServerManager().addPlayerToServer(CloudApi.getINSTANCE().getServerManager().getServerByName(Main.getINSTANCE().getServerName()), player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        ICloudPlayer player = CloudApi.getINSTANCE().getPlayerManager().getPlayerByUUID(event.getPlayer().getUniqueId());
        CloudApi.getINSTANCE().getServerManager().removePlayerFromServer(CloudApi.getINSTANCE().getServerManager().getServerByName(Main.getINSTANCE().getServerName()), player);
    }

}

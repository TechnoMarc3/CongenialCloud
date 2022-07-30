package de.linux3000;


import de.linux300.api.CloudApi;
import de.linux300.api.player.CloudPlayer;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;


public class MainListener implements Listener {

    @EventHandler
    public void postLogin(PostLoginEvent event) {
        //adding registered servers to listeners, so the player will connect to the correct server and no errors will be thrown
        ListenerInfo info = event.getPlayer().getPendingConnection().getListener();
        try {
            Field f = info.getClass().getDeclaredField("serverPriority");
            f.setAccessible(true);
            List<String> priorities = info.getServerPriority();
            priorities.clear();
            priorities.addAll(Main.getINSTANCE().getServer());
            f.set(info, priorities);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @EventHandler
    public void onLogin(LoginEvent event) {
        PendingConnection connection = event.getConnection();
        UUID uuid = connection.getUniqueId();
        String name = connection.getName();
        CloudPlayer player = new CloudPlayer(name, uuid);
        CloudApi.getINSTANCE().getPlayerManager().addPlayerToCloud(player);
    }



}

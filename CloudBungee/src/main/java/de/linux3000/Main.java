package de.linux3000;

import de.linux300.api.CloudApi;
import de.linux300.api.server.ICloudServer;
import io.netty.channel.unix.DomainSocketAddress;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.YamlConfiguration;


import java.io.*;
import java.lang.reflect.Field;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main extends Plugin {


    private static Main INSTANCE;
    private List<String> server = new ArrayList<>();

    @Override
    public void onLoad() {
        INSTANCE = this;
        ProxyWrapper.getINSTANCE().registerListener(new CloudMainListener());

    }

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new MainListener());

        for(ListenerInfo info : getProxy().getConfigurationAdapter().getListeners()) {
            try {
                Field f = info.getClass().getDeclaredField("serverPriority");
                f.setAccessible(true);
                List<String> priorities = info.getServerPriority();
                priorities.clear();
                f.set(info, priorities);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            System.out.println(info.getServerPriority());

        }
    }

    @Override
    public void onDisable() {

    }



    public void addServerToProxy(ICloudServer server) {
        if(ProxyServer.getInstance().getServers().containsKey(server.name())) {
            System.out.println("server already registered");
            return;
        }
        System.out.println("adding server to proxy : " +server);
        InetSocketAddress address = new InetSocketAddress(server.host(), server.port());
        registerServer(server.name(), server.uniqueId(), address);
    }

    public void removeServerFromProxy(ICloudServer server) {
        unregisterServer(server.name());
    }

    private void registerServer(String name, UUID uuid, InetSocketAddress address) {
        System.out.println(name);
        System.out.println(uuid);
        System.out.println(address);
        ServerInfo serverInfo = ProxyServer.getInstance().constructServerInfo(name, address, uuid.toString(), false);
        ProxyServer.getInstance().getServers().put(name, serverInfo);
        System.out.println("registered server successfully");
        server.add(name);



        for(ListenerInfo info : getProxy().getConfigurationAdapter().getListeners()) {
            try {
                Field f = info.getClass().getDeclaredField("serverPriority");
                f.setAccessible(true);
                List<String> priorities = info.getServerPriority();
                priorities.clear();
                priorities.addAll(server);
                f.set(info, priorities);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }




    }

    public List<String> getServer() {
        return server;
    }

    private void unregisterServer(String name) {
        ProxyServer.getInstance().getServers().remove(name);
    }

    public static Main getINSTANCE() {
        return INSTANCE;
    }
}

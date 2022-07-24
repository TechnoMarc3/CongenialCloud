package de.linux3000;

import de.linux3000.networking.NettyClient;
import de.linux3000.networking.packets.pkts.ProxyEnabledPacket;
import de.linux3000.networking.packets.pkts.ServerEnabledPacket;
import org.bukkit.plugin.java.JavaPlugin;

import java.net.InetSocketAddress;
import java.util.UUID;

public final class Main extends JavaPlugin {

    NettyClient client;
    static Main INSTANCE;

    @Override
    public void onLoad() {
        INSTANCE = this;

        try {
            client =new NettyClient();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onEnable() {
        // Plugin startup logic



        int port = this.getServer().getPort();
        String address = this.getServer().getIp();

        client.sendPacket(new ServerEnabledPacket(port, address));



    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public NettyClient getClient() {
        return client;
    }

    public static Main getINSTANCE() {
        return INSTANCE;
    }
}

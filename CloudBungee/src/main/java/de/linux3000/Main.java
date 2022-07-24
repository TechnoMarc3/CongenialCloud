package de.linux3000;

import de.linux3000.networking.NettyClient;
import de.linux3000.networking.NetworkHandler;
import de.linux3000.networking.packets.pkts.HelloPacket;
import de.linux3000.networking.packets.pkts.ProxyEnabledPacket;

import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Plugin;

import java.net.InetSocketAddress;
import java.util.Timer;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;

public final class Main extends Plugin {

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


                client.sendPacket(new ProxyEnabledPacket());




    }

    public void addServiceToProxy(String name, int port, String address) {
        registerService(name, UUID.randomUUID(), new InetSocketAddress(address, port));
    }

    private void registerService(String name, UUID uuid, InetSocketAddress address) {

        ServerInfo serverInfo = ProxyServer.getInstance().constructServerInfo(name, address,uuid.toString(), false);
        ProxyServer.getInstance().getServers().put(name, serverInfo);

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

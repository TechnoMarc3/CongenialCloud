package de.linux3000;

import de.linux3000.impl.CloudApiImpl;
import de.linux3000.networking.NettyClient;
import io.netty.channel.Channel;

public class ServerWrapper {


    static ServerWrapper INSTANCE;
    private NettyClient nettyClient;

    private Channel connectionToManager;

    public static void main(String[] args) throws InterruptedException {
        new ServerWrapper();
    }

    public ServerWrapper() throws InterruptedException {
        INSTANCE = this;
        nettyClient = new NettyClient();
    }

    static {

        new CloudApiImpl();

    }

    public static ServerWrapper getINSTANCE() {
        return INSTANCE;
    }

    public NettyClient getNettyClient() {
        return nettyClient;
    }

    public Channel getConnectionToManager() {
        return connectionToManager;
    }

    public void setConnectionToManager(Channel connectionToManager) {
        this.connectionToManager = connectionToManager;
    }
}

package de.linux3000;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.ICloudPlayerManager;
import de.linux300.api.manager.ICloudProcessManager;
import de.linux300.api.manager.ICloudServerGroupManager;
import de.linux300.api.manager.ICloudServerManager;
import de.linux300.api.manager.impl.AbstractCloudProcessManager;
import de.linux300.api.server.ProxyCloudServer;
import de.linux300.api.serverGroup.CloudServerGroup;
import de.linux3000.impl.CloudPlayerManagerImpl;
import de.linux3000.impl.CloudServerGroupManagerImpl;
import de.linux3000.impl.CloudServerManagerImpl;
import de.linux3000.networking.NettyClient;
import io.netty.channel.Channel;

import java.io.File;

public class ProxyWrapper extends CloudApi{

    private boolean isCloudPluginLoaded = false;
    static ProxyWrapper INSTANCE;
    private NettyClient nettyClient;

    private CloudPlayerManagerImpl cloudPlayerManager;
    private CloudServerManagerImpl cloudServerManager;
    private CloudServerGroupManagerImpl cloudServerGroupManager;
    private AbstractCloudProcessManager cloudProcessManager;

    private Channel connectionToManager;

    public static ProxyCloudServer server;



    public static void main(String[] args) throws InterruptedException {
        String proxyFile = args[0];
        System.out.println(proxyFile);
        File proxy = new File(proxyFile);
        System.out.println(proxy);

        server = new ProxyCloudServer(proxy);

        new ProxyWrapper();



        CloudApi.getINSTANCE().getServerManager().startProxyServer(server);


    }



    public ProxyWrapper() throws InterruptedException {
        INSTANCE = this;
        nettyClient = new NettyClient();

        cloudPlayerManager = new CloudPlayerManagerImpl();
        cloudServerGroupManager = new CloudServerGroupManagerImpl();
        cloudServerManager = new CloudServerManagerImpl();
        cloudProcessManager = new AbstractCloudProcessManager();

        CloudApi.setInstance(this);


    }


    public void stop() {
        //TODO -> Stop Services
        System.exit(0);
    }

    public void setCloudPluginLoaded(boolean cloudPluginLoaded) {
        isCloudPluginLoaded = cloudPluginLoaded;
    }

    public static ProxyWrapper getINSTANCE() {
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

    @Override
    public ICloudServerManager getServerManager() {
        return this.cloudServerManager;
    }

    @Override
    public ICloudPlayerManager getPlayerManager() {
        return this.cloudPlayerManager;
    }

    @Override
    public ICloudServerGroupManager getServerGroupManager() {
        return this.cloudServerGroupManager;
    }

    @Override
    public ICloudProcessManager getProcessManager() {
        return this.cloudProcessManager;
    }

    @Override
    public boolean isPlugin() {

        return true;
    }

    @Override
    public boolean isManager() {
        return false;
    }


    public boolean isCloudPluginLoaded() {
        return isCloudPluginLoaded;
    }
}

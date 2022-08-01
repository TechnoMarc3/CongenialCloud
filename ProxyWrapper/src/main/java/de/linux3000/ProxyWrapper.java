package de.linux3000;

import de.linux300.api.CloudApi;
import de.linux300.api.event.EventListener;
import de.linux300.api.manager.*;
import de.linux300.api.manager.impl.AbstractCloudProcessManager;
import de.linux300.api.server.ProxyCloudServer;
import de.linux300.api.serverGroup.CloudServerGroup;
import de.linux3000.impl.*;
import de.linux3000.networking.NettyClient;
import io.netty.channel.Channel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProxyWrapper extends CloudApi{

    private boolean isCloudPluginLoaded = false;
    static ProxyWrapper INSTANCE;
    private NettyClient nettyClient;

    private CloudPlayerManagerImpl cloudPlayerManager;
    private CloudServerManagerImpl cloudServerManager;
    private CloudServerGroupManagerImpl cloudServerGroupManager;
    private AbstractCloudProcessManager cloudProcessManager;
    private CloudEventManagerImpl cloudEventManager;

    private Channel connectionToManager;

    public static ProxyCloudServer server;

    private List<EventListener> listeners = new ArrayList<>();



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
        nettyClient = new NettyClient();
        INSTANCE = this;


        cloudPlayerManager = new CloudPlayerManagerImpl();
        cloudServerGroupManager = new CloudServerGroupManagerImpl();
        cloudServerManager = new CloudServerManagerImpl();
        cloudProcessManager = new AbstractCloudProcessManager();
        cloudEventManager = new CloudEventManagerImpl();

        CloudApi.setInstance(this);

    }


    public void registerListener(EventListener listener) {
        listeners.add(listener);
    }

    public void unregisterListener(EventListener listener) {
        listeners.remove(listener);
    }

    public List<EventListener> getListeners() {
        return listeners;
    }

    public void stop() {
        //TODO -> Stop Services
        System.exit(0);
    }

    public static ProxyCloudServer getServer() {
        return server;
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
    public ICloudEventManager getEventManager() {
        return this.cloudEventManager;
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

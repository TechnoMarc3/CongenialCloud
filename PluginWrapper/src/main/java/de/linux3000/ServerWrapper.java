package de.linux3000;

import de.linux300.api.CloudApi;
import de.linux300.api.event.EventListener;
import de.linux300.api.manager.*;
import de.linux300.api.manager.impl.AbstractCloudProcessManager;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.CloudServerGroup;
import de.linux3000.impl.CloudEventManagerImpl;
import de.linux3000.impl.CloudPlayerManagerImpl;
import de.linux3000.impl.CloudServerGroupManagerImpl;
import de.linux3000.impl.CloudServerManagerImpl;
import de.linux3000.networking.NettyClient;
import io.netty.channel.Channel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ServerWrapper extends CloudApi{

    private boolean isCloudPluginLoaded = false;
    static ServerWrapper INSTANCE;
    private NettyClient nettyClient;

    private CloudPlayerManagerImpl cloudPlayerManager;
    private CloudServerManagerImpl cloudServerManager;
    private CloudServerGroupManagerImpl cloudServerGroupManager;
    private AbstractCloudProcessManager cloudProcessManager;
    private CloudEventManagerImpl cloudEventManager;

    private ICloudServer thisServer;
    private List<EventListener> listeners = new ArrayList<>();

    private ICloudServer thisSideServer;

   private static String name;
      private static int maxMemory;
    private static    int server;
 private static    File version;


    private Channel connectionToManager;



    public static void main(String[] args) throws InterruptedException {
        name = args[0];
        maxMemory = Integer.parseInt(args[1]);
        server = Integer.parseInt(args[2]);
        String versionString = args[3];
        version = new File(versionString);




        CloudServerGroup group = new CloudServerGroup(name, maxMemory, server, version);


        new ServerWrapper();


        CloudApi.getINSTANCE().getServerGroupManager().startNewService(group);

    }






    public ServerWrapper() throws InterruptedException {
        nettyClient = new NettyClient();

        INSTANCE = this;

        cloudPlayerManager = new CloudPlayerManagerImpl();
        cloudServerGroupManager = new CloudServerGroupManagerImpl();
        cloudServerManager = new CloudServerManagerImpl();
        cloudProcessManager = new AbstractCloudProcessManager();
        cloudEventManager = new CloudEventManagerImpl();

        CloudApi.setInstance(this);


    }


    public void setThisServer(ICloudServer thisServer) {
        this.thisServer = thisServer;
        System.out.println("set this server to : " + thisServer);
    }

    public ICloudServer getThisServer() {
        return thisServer;
    }

    public static void setName(String name) {
        ServerWrapper.name = name;
    }

    public static String getName() {
        return name;
    }

    public static int getMaxMemory() {
        return maxMemory;
    }

    public static int getServer() {
        return server;
    }

    public static File getVersion() {
        return version;
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

    public void setCloudPluginLoaded(boolean cloudPluginLoaded) {
        isCloudPluginLoaded = cloudPluginLoaded;
    }

    public void stop() {
        //TODO -> Stop Services
        System.exit(0);
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

    @Override
    public ICloudServerManager getServerManager() {
        return this.cloudServerManager;
    }

    @Override
    public ICloudEventManager getEventManager() {
        return cloudEventManager;
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

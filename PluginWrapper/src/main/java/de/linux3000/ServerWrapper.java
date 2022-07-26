package de.linux3000;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.ICloudPlayerManager;
import de.linux300.api.manager.ICloudProcessManager;
import de.linux300.api.manager.ICloudServerGroupManager;
import de.linux300.api.manager.ICloudServerManager;
import de.linux300.api.manager.impl.AbstractCloudProcessManager;
import de.linux300.api.serverGroup.CloudServerGroup;
import de.linux3000.impl.CloudPlayerManagerImpl;
import de.linux3000.impl.CloudServerGroupManagerImpl;
import de.linux3000.impl.CloudServerManagerImpl;
import de.linux3000.networking.NettyClient;
import io.netty.channel.Channel;

import java.io.File;

public class ServerWrapper extends CloudApi{

    private boolean isCloudPluginLoaded = false;
    static ServerWrapper INSTANCE;
    private NettyClient nettyClient;

    private CloudPlayerManagerImpl cloudPlayerManager;
    private CloudServerManagerImpl cloudServerManager;
    private CloudServerGroupManagerImpl cloudServerGroupManager;
    private AbstractCloudProcessManager cloudProcessManager;

    private Channel connectionToManager;



    public static void main(String[] args) throws InterruptedException {
        String name = args[0];
        int maxMemory = Integer.parseInt(args[1]);
        int server = Integer.parseInt(args[2]);
        String versionString = args[3];
        File version = new File(versionString);




        CloudServerGroup group = new CloudServerGroup(name, maxMemory, server, version);


        new ServerWrapper();


        CloudApi.getINSTANCE().getServerGroupManager().startNewService(group);




    }



    public ServerWrapper() throws InterruptedException {
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

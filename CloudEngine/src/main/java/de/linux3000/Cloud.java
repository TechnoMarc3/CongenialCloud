package de.linux3000;

import de.linux300.api.CloudApi;
import de.linux300.api.event.EventListener;
import de.linux300.api.versions.Versions;
import de.linux3000.cache.manager.CloudServerGroupCacheManager;
import de.linux3000.commands.CommandManager;
import de.linux3000.commands.CommandRegistry;
import de.linux3000.impl.CloudApiImpl;
import de.linux3000.listener.CloudListener;
import de.linux3000.manager.CloudServerGroupManager;
import de.linux3000.networking.NettyServer;
import de.linux3000.startup.StartupManager;
import de.linux3000.startup.StartupOrganizer;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.List;


public class Cloud {

    private static Cloud INSTANCE;
    private StartupOrganizer organizer;
    private StartupManager manager;
    private CommandRegistry commandRegistry;
    private CloudServerGroupManager cloudServerManagerGroup ;
    private CommandManager commandManager;
    private CloudServerGroupCacheManager cloudServerGroupCacheManager;
    private List<EventListener> listeners = new ArrayList<>();

    private List<Channel> connectionsToServer = new ArrayList<>();

    private Channel connectionToProxyWrapper;


    public static void main(String[] args) {
        new Thread(() -> {
            try {
                new NettyServer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Cloud().initialize();

        CloudApi.setInstance(new CloudApiImpl());




        getINSTANCE().organizer.registerQuestions();
        getINSTANCE().organizer.registerServerQuestions();
        getINSTANCE().commandRegistry.registerCommands();



        getINSTANCE().manager.doStartup();




        new Thread(() -> {
            getINSTANCE().commandManager.listenForCommands();
        }).start();



    }

    private  void initialize() {
        INSTANCE = this;
        organizer = new StartupOrganizer();
        manager = new StartupManager();

        commandRegistry =  new CommandRegistry();
        cloudServerManagerGroup =  new CloudServerGroupManager();
        commandManager = new CommandManager();
        cloudServerGroupCacheManager = new CloudServerGroupCacheManager();

        registerListener(new CloudListener());


    }

    public void addServerConnection(Channel channel) {
        this.connectionsToServer.add(channel);
    }

    public List<Channel> getConnectionsToServer() {
        return connectionsToServer;
    }

    public void setConnectionToProxyWrapper(Channel connectionToProxyWrapper) {
        this.connectionToProxyWrapper = connectionToProxyWrapper;
        System.out.println("set proxy connection to: " + connectionToProxyWrapper);
    }

    public Channel getConnectionToProxyWrapper() {
        return connectionToProxyWrapper;
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

    public static Cloud getINSTANCE() {
        return INSTANCE;
    }

    public StartupOrganizer getOrganizer() {
        return organizer;
    }

    public StartupManager getStartupManager() {
        return manager;
    }

    public CommandRegistry getCommandRegistry() {
        return commandRegistry;
    }

    public CloudServerGroupManager getCloudServerGroupManager() {
        return cloudServerManagerGroup;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public CloudServerGroupCacheManager getCloudServerGroupCacheManager() {
        return cloudServerGroupCacheManager;
    }
}

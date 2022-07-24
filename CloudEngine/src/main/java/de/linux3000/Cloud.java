package de.linux3000;

import de.linux3000.cache.manager.CloudServerGroupCacheManager;
import de.linux3000.commands.CommandManager;
import de.linux3000.commands.CommandRegistry;
import de.linux3000.manager.CloudServerGroupManager;
import de.linux3000.networking.NettyServer;
import de.linux3000.startup.StartupManager;
import de.linux3000.startup.StartupOrganizer;


public class Cloud {

    private static Cloud INSTANCE;
    private StartupOrganizer organizer;
    private StartupManager manager;
    private CommandRegistry commandRegistry;
    private CloudServerGroupManager cloudServerManagerGroup ;
    private CommandManager commandManager;
    private CloudServerGroupCacheManager cloudServerGroupCacheManager;
    public static void main(String[] args) throws InterruptedException {

        new Cloud().initialize();

        getINSTANCE().organizer.registerQuestions();
        getINSTANCE().organizer.registerServerQuestions();
     //  getINSTANCE().manager.doStartup();
        getINSTANCE().commandRegistry.registerCommands();
        new NettyServer();
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

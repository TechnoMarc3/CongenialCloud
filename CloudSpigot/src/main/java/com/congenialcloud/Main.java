package com.congenialcloud;


import com.congenialcloud.listener.MainListener;
import de.linux300.api.CloudApi;
import de.linux300.api.manager.ICloudServerManager;
import org.bukkit.plugin.java.JavaPlugin;



public final class Main extends JavaPlugin {


    static Main INSTANCE;
    String serverName;

    @Override
    public void onLoad() {


        INSTANCE = this;



    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        CloudApi api = CloudApi.getINSTANCE();
        ICloudServerManager manager = api.getServerManager();
        System.out.println(api);
        System.out.println(manager);
        System.out.println(manager.getAllRegisteredServer());
        getServer().getPluginManager().registerEvents(new MainListener(), this);




    }

    public String getServerName() {
        return serverName;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    public static Main getINSTANCE() {
        return INSTANCE;
    }
}

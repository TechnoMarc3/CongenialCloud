package com.congenialcloud;


import com.congenialcloud.listener.MainListener;
import de.linux300.api.CloudApi;
import de.linux300.api.manager.ICloudServerManager;
import de.linux300.api.server.CloudServer;
import de.linux3000.ServerWrapper;
import org.bukkit.plugin.java.JavaPlugin;



public final class Main extends JavaPlugin {


    static Main INSTANCE;


    @Override
    public void onLoad() {


        INSTANCE = this;



    }

    @Override
    public void onEnable() {
        // Plugin startup logic



        getServer().getPluginManager().registerEvents(new MainListener(), this);

        System.out.println(getServer().getIp());
        System.out.println(getServer().getPort());

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    public static Main getINSTANCE() {
        return INSTANCE;
    }
}

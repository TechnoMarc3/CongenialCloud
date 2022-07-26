package de.linux3000.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.impl.AbstractCloudServerGroupManager;
import de.linux300.api.server.CloudServer;
import de.linux300.api.server.ServerTypes;
import de.linux300.api.serverGroup.ICloudServerGroup;
import de.linux3000.Agent;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.jar.JarFile;

public class CloudServerGroupManagerImpl extends AbstractCloudServerGroupManager {


    private void createServer() {

        File g = new File("./plugins");
        System.out.println(g.mkdirs());

        File spigot = new File("C:\\Users\\marco\\Documents\\Development\\Cloud\\CloudSpigot-1.0-SNAPSHOT.jar");
        File j = new File("./plugins/" + "CloudPlugin.jar");


        try {
            Files.copy(spigot.toPath(), j.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied Plugin");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }




    @Override
    public void startNewService(ICloudServerGroup group) {


        createServer();


        File g = new File("./"+ group.version().getName());

        try {
            File h = group.version();
            System.out.println(h);
            Files.copy(h.toPath(),g.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copy successful");
        } catch (IOException e) {
            e.printStackTrace();
        }


            try {
                JarFile jar = new JarFile(g);
                Agent.appendJarFile(jar);
                String main = jar.getManifest().getMainAttributes().getValue("Main-Class");
                Class mainClass = Class.forName(main);
                System.setProperty("com.mojang.eula.agree", "true");
                mainClass.getMethod("main", String[].class).invoke(null, (Object) new String[] {});

                CloudServer server = new CloudServer(group.registeredServerCount(), group.maxMemory() / group.registeredServerCount(), group.name() + " - " + group.registeredServerCount(),group , ServerTypes.SPIGOT);
                CloudApi.getINSTANCE().getServerManager().registerServer(server);
                CloudApi.getINSTANCE().getServerGroupManager().addServerToServerGroup(server, group);

            } catch (IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
                ex.printStackTrace();
            }

    }


}

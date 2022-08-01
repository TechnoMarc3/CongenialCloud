package de.linux3000.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.impl.AbstractCloudServerGroupManager;
import de.linux300.api.server.CloudServer;
import de.linux300.api.server.ServerTypes;
import de.linux300.api.serverGroup.ICloudServerGroup;
import de.linux3000.Agent;
import de.linux3000.ServerWrapper;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
            FileWriter fileWriter = new FileWriter(new File("./server.properties"));
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println("server-port=25567");
            printWriter.println("online-mode=false");
            printWriter.close();
            System.out.println("Copy successful");
        } catch (IOException e) {
            e.printStackTrace();
        }


        File jsonFile = new File("./CloudConfig.json");
        JSONObject object = new JSONObject();
        object.put("name", ServerWrapper.getName());
        object.put("maxMemory", ServerWrapper.getMaxMemory());
        object.put("server", ServerWrapper.getServer());
        object.put("version", ServerWrapper.getVersion().getName());

        try (FileWriter file = new FileWriter(jsonFile)) {
            System.out.println("writing json file");
            file.write(object.toJSONString());
            file.flush();
            System.out.println("writing completed");
        } catch (IOException e) {
            e.printStackTrace();
        }



                new Thread(() -> {
                    try {
                        JarFile jar = new JarFile(g);
                        Agent.appendJarFile(jar);
                        String main = jar.getManifest().getMainAttributes().getValue("Main-Class");
                        Class mainClass = Class.forName(main);
                        System.setProperty("com.mojang.eula.agree", "true");
                        mainClass.getMethod("main", String[].class).invoke(null, (Object) new String[] {});
                    } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | IOException e) {
                        e.printStackTrace();
                    }
                }).start();

                ServerWrapper.setName(group.name() + " - " + group.registeredServerCount());
                CloudServer server = new CloudServer(group.registeredServerCount(), group.maxMemory() / group.registeredServerCount(), group.name() + " - " + group.registeredServerCount(),group, ServerTypes.SPIGOT );
                server.setHost("127.0.0.1");
                server.setPort(25567);
                //TODO -> Port Management with API
                ServerWrapper.getINSTANCE().setCloudPluginLoaded(true);
                ServerWrapper.getINSTANCE().setThisServer(server);
                CloudApi.getINSTANCE().getServerManager().registerServer(server);
                CloudApi.getINSTANCE().getServerGroupManager().addServerToServerGroup(server, group);




    }


}

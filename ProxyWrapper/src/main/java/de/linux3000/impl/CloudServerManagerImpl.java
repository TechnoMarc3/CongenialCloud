package de.linux3000.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.impl.AbstractCloudServerManager;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.server.ServerTypes;
import de.linux3000.Agent;
import de.linux3000.ProxyWrapper;
import net.md_5.bungee.api.ProxyServer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.jar.JarFile;


public class CloudServerManagerImpl extends AbstractCloudServerManager {

    @Override
    public void shutdownCloudServer(ICloudServer iCloudServer) {


       // ProxyWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new ShutdownCloudServerPacket(iCloudServer.uniqueId()));
        if(!iCloudServer.serverType().equals(ServerTypes.PROXY)) return;
        if(ProxyWrapper.getINSTANCE().isCloudPluginLoaded()) {

                ProxyServer.getInstance().stop();

        }

    }


    private void createServer() {

        File g = new File("./plugins");
        System.out.println(g.mkdirs());

        File plugin = new File("C:\\Users\\marco\\Documents\\Development\\Cloud\\CloudBungee-1.0-SNAPSHOT.jar");

        File j = new File("./plugins/" + "CloudPlugin.jar");


        try {
            Files.copy(plugin.toPath(), j.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied Plugin");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }




    @Override
    public void startProxyServer(ICloudServer iCloudServer) {

        createServer();
        File g = new File("./"+ iCloudServer.version().getName());

        try {
            File h = iCloudServer.version();


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
            new Thread(()-> {
                try {
                    mainClass.getMethod("main", String[].class).invoke(null, (Object) new String[] {});
                } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }).start();
            ProxyWrapper.getINSTANCE().setCloudPluginLoaded(true);
            CloudApi.getINSTANCE().getServerManager().registerServer(ProxyWrapper.server);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }


    }
}

package de.linux3000.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.impl.AbstractCloudServerManager;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.server.ServerTypes;
import de.linux3000.Agent;
import de.linux3000.ProxyWrapper;

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
            //TODO -> stop service
        }

    }



    @Override
    public void startProxyServer(ICloudServer iCloudServer) {
        System.out.println(0);


        System.out.println(1);

        File g = new File("./"+ iCloudServer.version().getName());
        System.out.println(2);

        try {
            File h = iCloudServer.version();
            System.out.println(3);
            System.out.println(h);
            Files.copy(h.toPath(),g.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println(4);
            System.out.println("Copy successful");
        } catch (IOException e) {

            e.printStackTrace();
        }

        try {
            JarFile jar = new JarFile(g);
            Agent.appendJarFile(jar);
            String main = jar.getManifest().getMainAttributes().getValue("Main-Class");
            Class mainClass = Class.forName(main);
            mainClass.getMethod("main", String[].class).invoke(null, (Object) new String[] {});
            ProxyWrapper.getINSTANCE().setCloudPluginLoaded(true);
            CloudApi.getINSTANCE().getServerManager().registerServer(ProxyWrapper.server);
        } catch (IOException | ClassNotFoundException | InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
            ex.printStackTrace();
        }



    }
}

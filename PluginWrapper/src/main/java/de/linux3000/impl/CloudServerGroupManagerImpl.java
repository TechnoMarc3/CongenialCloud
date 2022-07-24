package de.linux3000.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.impl.AbstractCloudServerGroupManager;
import de.linux300.api.serverGroup.ICloudServerGroup;
import de.linux3000.base.CloudServer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CloudServerGroupManagerImpl extends AbstractCloudServerGroupManager {


    private String path = "./Cloud/Server";



    private void createServer(ICloudServerGroup group) {
        File f = new File(path + "/" + group.name() + "/CloudServer - " + group.allServer().size()+1);
        System.out.println(f.mkdirs());
    }

    public void createServerFiles(ICloudServerGroup group) {

        File f = new File(path + "/" + group.name());

        System.out.println(f.mkdirs());




    }

    @Override
    public void startNewService(ICloudServerGroup group) {

        createServerFiles(group);
        createServer(group);

        File f = new File(path + "/" + group.name() + "/CloudServer - " + group.allServer().size()+1);
        File g = new File(path + "/" + group.name() + "/CloudServer - " + group.allServer().size()+1+ "/" + group.version().name() + ".jar");

        try {
            Files.copy(group.version().getFile().toPath(),g.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copy successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(f);
        builder.command("java" , "-Xmx" + group.maxMemory() / (group.allServer().size()+1) + "M","-Xms" + (group.maxMemory() / (group.allServer().size()+1))/2 + "M","-Dcom.mojang.eula.agree=true","-DIReallyKnowWhatIAmDoingISwear", "-jar", g.getAbsolutePath(), "--nogui", "-o", "true");
        try {
            Process start = builder.start();
            System.out.println("Started server " + group.name() + " - " + group.allServer().size()+1);
            CloudServer server = new CloudServer((group.allServer().size()+1), (group.maxMemory() / (group.allServer().size()+1)), group.name() + " - " + group.allServer().size()+1, group);
            CloudApi.getINSTANCE().getProcessManager().registerProcess(start, server);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

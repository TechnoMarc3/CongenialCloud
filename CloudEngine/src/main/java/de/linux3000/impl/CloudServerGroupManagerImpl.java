package de.linux3000.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.impl.AbstractCloudServerGroupManager;
import de.linux300.api.serverGroup.ICloudServerGroup;
import de.linux3000.base.CloudServer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CloudServerGroupManagerImpl extends AbstractCloudServerGroupManager {


    private String path = "./Cloud/Server";

    File wrapper;


    private void createServer(ICloudServerGroup group) {
        File f = new File(path + "/" + group.name() + "/CloudServer - " + group.allServer().size()+1);
        System.out.println(f.mkdirs());
        File o = new File("C:\\Users\\marco\\Documents\\Development\\Cloud\\PluginWrapper\\out\\artifacts\\PluginWrapper_jar\\PluginWrapper.jar");
        wrapper= new File(path + "/" + group.name() + "/CloudServer - " + group.allServer().size()+1 +"/PluginWrapper.jar");

        try {
            Files.copy(o.toPath(), wrapper.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Copied Wrapper");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }


    private void startWrapper(ICloudServerGroup group) {
        if(wrapper == null) {
            return;
        }

        ProcessBuilder builder = new ProcessBuilder();

        builder.directory(new File(wrapper.getAbsolutePath().replace("\\PluginWrapper.jar", "")));
        builder.command("java","-Xmx" + group.maxMemory() / (group.registeredServerCount() +1)+ "M", "-Xms" +  group.maxMemory() / (group.registeredServerCount() +1)+ "M","-javaagent:" + wrapper.getAbsolutePath(), "-jar", wrapper.getAbsolutePath(), group.name(), String.valueOf(group.maxMemory()), String.valueOf(group.allServer().size()+1), group.version().getAbsolutePath());
        try {
           Process p =  builder.start();
            System.out.println("started wrapper: " +p);

            new Thread(() -> {
                InputStreamReader reader = new InputStreamReader(p.getInputStream());
                InputStreamReader er = new InputStreamReader(p.getErrorStream());
                BufferedReader r = new BufferedReader(reader);
                BufferedReader err = new BufferedReader(er);
                String s;
                String error;
                while (true) {
                    try {
                        if((error = err.readLine()) != null) {
                            System.out.println(error);
                        }else {
                            break;
                        }
                        if ((s = r.readLine()) != null)  {
                            System.out.println(s);
                        }else {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void startNewService(ICloudServerGroup group) {


        createServer(group);
        startWrapper(group);



    }


}

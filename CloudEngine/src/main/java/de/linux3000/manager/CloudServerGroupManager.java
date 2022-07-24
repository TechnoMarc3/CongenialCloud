package de.linux3000.manager;

import de.linux300.api.CloudApi;
import de.linux300.api.serverGroup.ICloudServerGroup;
import de.linux3000.Cloud;
import de.linux3000.base.CloudServer;
import de.linux3000.base.CloudServerGroup;
import de.linux3000.cache.caches.CachedCloudServerGroup;

import java.io.File;


public class CloudServerGroupManager {



    private String path = "./Cloud/Server";
    public void handleAnswers() {
        CachedCloudServerGroup cachedCloudServerGroup = Cloud.getINSTANCE().getCloudServerGroupCacheManager().get();
        Cloud.getINSTANCE().getCloudServerGroupCacheManager().recycle();

        CloudServerGroup newGroup = new CloudServerGroup(cachedCloudServerGroup.name(), cachedCloudServerGroup.version(), cachedCloudServerGroup.percentageForNewServer(), cachedCloudServerGroup.playerForNewServer(), cachedCloudServerGroup.maxMemory());


        ProcessBuilder builder = new ProcessBuilder();

        File f = new File(path + "/" + newGroup.name() + "/CloudServer - 1");
        builder.directory(f);
        builder.command("java", "-jar", "C:\\Users\\marco\\Documents\\Development\\Cloud\\PluginWrapper\\out\\artifacts\\PluginWrapper_jar\\PluginWrapper.jar");

        CloudApi.getINSTANCE().getServerGroupManager().startNewService(newGroup);





    }



}

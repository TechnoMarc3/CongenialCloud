package de.linux3000.manager;

import de.linux300.api.CloudApi;
import de.linux3000.Cloud;
import de.linux3000.base.CloudServerGroup;
import de.linux3000.cache.caches.CachedCloudServerGroup;


public class CloudServerGroupManager {



    private String path = "./Cloud/Server";
    public void handleAnswers() {
        CachedCloudServerGroup cachedCloudServerGroup = Cloud.getINSTANCE().getCloudServerGroupCacheManager().get();
        Cloud.getINSTANCE().getCloudServerGroupCacheManager().recycle();

        CloudServerGroup newGroup = new CloudServerGroup(cachedCloudServerGroup.name(), cachedCloudServerGroup.version(), cachedCloudServerGroup.percentageForNewServer(), cachedCloudServerGroup.playerForNewServer(), cachedCloudServerGroup.maxMemory());



        CloudApi.getINSTANCE().getServerGroupManager().startNewService(newGroup);




    }



}

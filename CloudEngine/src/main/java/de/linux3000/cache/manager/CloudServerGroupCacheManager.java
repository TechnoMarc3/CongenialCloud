package de.linux3000.cache.manager;

import de.linux3000.cache.ICacheManager;
import de.linux3000.cache.caches.CachedCloudServerGroup;


public class CloudServerGroupCacheManager implements ICacheManager<CachedCloudServerGroup> {


    private CachedCloudServerGroup cache;



    @Override
    public CachedCloudServerGroup get() {
        if(cache == null) {
            cache = new CachedCloudServerGroup();
        }
        System.out.println(cache);
        return cache;
    }

    @Override
    public boolean recycle() {
        cache = null;
        return true;
    }


}

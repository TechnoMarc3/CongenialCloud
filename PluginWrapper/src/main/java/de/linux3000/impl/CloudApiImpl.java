package de.linux3000.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.ICloudPlayerManager;
import de.linux300.api.manager.ICloudProcessManager;
import de.linux300.api.manager.ICloudServerGroupManager;
import de.linux300.api.manager.ICloudServerManager;
import de.linux300.api.manager.impl.AbstractCloudProcessManager;

public class CloudApiImpl extends CloudApi {


    @Override
    public ICloudServerManager getServerManager() {
        return new CloudServerManagerImpl();
    }

    @Override
    public ICloudPlayerManager getPlayerManager() {
        return new CloudPlayerManagerImpl();
    }

    @Override
    public ICloudServerGroupManager getServerGroupManager() {
        return new CloudServerGroupManagerImpl();
    }

    @Override
    public ICloudProcessManager getProcessManager() {
        return new AbstractCloudProcessManager();
    }

    @Override
    public boolean isPlugin() {
        return true;
    }

    @Override
    public boolean isManager() {
        return false;
    }
}

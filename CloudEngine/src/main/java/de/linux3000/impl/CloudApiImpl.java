package de.linux3000.impl;

import de.linux300.api.CloudApi;
import de.linux300.api.manager.*;
import de.linux300.api.manager.impl.AbstractCloudProcessManager;
import de.linux300.api.versions.Versions;

public class CloudApiImpl extends CloudApi {

    private CloudServerGroupManagerImpl cloudServerGroupManager;
    private CloudPlayerManagerImpl cloudPlayerManager;
    private CloudServerManagerImpl cloudServerManager;
    private AbstractCloudProcessManager cloudProcessManager;
    private CloudEventManagerImpl cloudEventManager;


    public CloudApiImpl() {
        this.cloudPlayerManager = new CloudPlayerManagerImpl();
        this.cloudServerGroupManager = new CloudServerGroupManagerImpl();
        this.cloudServerManager = new CloudServerManagerImpl();
        this.cloudProcessManager = new AbstractCloudProcessManager();
        this.cloudEventManager = new CloudEventManagerImpl();

    }

    @Override
    public ICloudServerManager getServerManager() {
        return cloudServerManager;
    }

    @Override
    public ICloudEventManager getEventManager() {
        return cloudEventManager;
    }

    @Override
    public ICloudPlayerManager getPlayerManager() {
        return cloudPlayerManager;
    }

    @Override
    public ICloudServerGroupManager getServerGroupManager() {
        return cloudServerGroupManager;
    }

    @Override
    public ICloudProcessManager getProcessManager() {
        return this.cloudProcessManager;
    }



    @Override
    public boolean isPlugin() {
        return false;
    }

    @Override
    public boolean isManager() {
        return true;
    }
}

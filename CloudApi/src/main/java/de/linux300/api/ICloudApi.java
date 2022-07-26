package de.linux300.api;

import de.linux300.api.manager.ICloudPlayerManager;
import de.linux300.api.manager.ICloudProcessManager;
import de.linux300.api.manager.ICloudServerGroupManager;
import de.linux300.api.manager.ICloudServerManager;
import de.linux300.api.versions.Versions;

public interface ICloudApi {

    ICloudServerManager getServerManager();

    ICloudPlayerManager getPlayerManager();

    ICloudServerGroupManager getServerGroupManager();

    ICloudProcessManager getProcessManager();




    boolean isPlugin();
    boolean isManager();

}

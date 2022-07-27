package de.linux300.api;

import de.linux300.api.manager.*;
import de.linux300.api.versions.Versions;

public interface ICloudApi {

    ICloudServerManager getServerManager();

    ICloudEventManager getEventManager();

    ICloudPlayerManager getPlayerManager();

    ICloudServerGroupManager getServerGroupManager();

    ICloudProcessManager getProcessManager();




    boolean isPlugin();
    boolean isManager();

}

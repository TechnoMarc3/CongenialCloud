package de.linux300.api.manager;

import de.linux300.api.server.ICloudServer;

public interface ICloudProcessManager {

    Process getProcess(ICloudServer server);

    void registerProcess(Process process, ICloudServer server);

    void unregisterProcess(ICloudServer server);



}

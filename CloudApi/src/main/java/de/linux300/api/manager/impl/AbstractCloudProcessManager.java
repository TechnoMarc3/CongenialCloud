package de.linux300.api.manager.impl;

import de.linux300.api.manager.ICloudProcessManager;
import de.linux300.api.server.ICloudServer;

import java.util.HashMap;

public class AbstractCloudProcessManager implements ICloudProcessManager {

    private HashMap<ICloudServer, Process> processes = new HashMap<>();

    @Override
    public Process getProcess(ICloudServer server) {
        return processes.get(server);
    }

    @Override
    public void registerProcess(Process process, ICloudServer server) {
        processes.put(server, process);
    }

    @Override
    public void unregisterProcess(ICloudServer server) {
        processes.remove(server);
    }
}

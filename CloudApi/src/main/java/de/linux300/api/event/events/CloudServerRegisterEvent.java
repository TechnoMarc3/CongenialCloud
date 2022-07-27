package de.linux300.api.event.events;

import de.linux300.api.CloudApi;
import de.linux300.api.event.IEvent;
import de.linux300.api.server.ICloudServer;

import java.util.UUID;

public class CloudServerRegisterEvent implements IEvent {

    private ICloudServer server;

    public CloudServerRegisterEvent(ICloudServer server) {
        this.server = server;
    }

    public CloudServerRegisterEvent(UUID uuid) {
        this.server = CloudApi.getINSTANCE().getServerManager().getServerByUUID(uuid);
    }

    public ICloudServer getServer() {
        return server;
    }
}

package de.linux300.api.player;

import de.linux300.api.CloudApi;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.serverGroup.ICloudServerGroup;

import java.util.UUID;

public class CloudPlayer implements ICloudPlayer{

    private String name;
    private UUID uuid;
    private ICloudServer server;
    private ICloudServer proxy;

    public CloudPlayer(String name, UUID uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public void setServer(ICloudServer server) {
        this.server = server;
    }

    public void setProxy(ICloudServer proxy) {
        this.proxy = proxy;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public UUID uuid() {
        return uuid;
    }

    @Override
    public ICloudServer getConnectedServer() {
        return server;
    }

    @Override
    public ICloudServer getConnectedProxy() {
        return proxy;
    }

    @Override
    public String getConnectedServerName() {
        return server.name();
    }

    @Override
    public String getConnectedProxyName() {
        return proxy.name();
    }

}

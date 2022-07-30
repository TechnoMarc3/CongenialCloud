package de.linux3000;

import de.linux300.api.event.EventListener;
import de.linux300.api.event.events.CloudServerRegisterEvent;
import de.linux300.api.event.events.CloudServerUnregisterEvent;
import de.linux300.api.event.events.CloudServerUpdateEvent;
import de.linux300.api.server.ServerTypes;

public class CloudMainListener extends EventListener {

    public void onServerRegister(CloudServerRegisterEvent event) {
        System.out.println("call");
        if (event.getServer().serverType() == ServerTypes.PROXY) {
            System.out.println("is proxy");
            return;
        }
        if (event.getServer().host() == null) {

            System.out.println("host is null");
            return;
        }
        if (event.getServer().port() == 0) {

            System.out.println("port is null");
            return;
        }
        Main.getINSTANCE().addServerToProxy(event.getServer());
    }

    public void onServerUnregister(CloudServerUnregisterEvent event) {
        System.out.println("call");
        if(event.getServer().serverType() == ServerTypes.PROXY) return;
        Main.getINSTANCE().removeServerFromProxy(event.getServer());
    }

}

package de.linux3000.listener;

import de.linux300.api.event.EventListener;
import de.linux300.api.event.events.CloudServerRegisterEvent;

public class CloudListener extends EventListener {

    public void onServerCreate(CloudServerRegisterEvent event) {
        System.out.println(event.getServer() + "   type: "+event.getServer().serverType());
    }

}

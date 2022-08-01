package de.linux3000.impl;

import de.linux300.api.event.EventListener;
import de.linux300.api.event.IEvent;
import de.linux300.api.event.events.CloudPlayerUpdateEvent;
import de.linux300.api.event.events.CloudServerRegisterEvent;
import de.linux300.api.event.events.CloudServerUpdateEvent;
import de.linux300.api.manager.ICloudEventManager;
import de.linux300.api.server.CloudServer;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.server.ServerTypes;
import de.linux3000.ServerWrapper;
import de.linux3000.networking.packets.pkts.CloudRegisterServerPacket;
import de.linux3000.networking.packets.pkts.CloudUpdatePlayerPacket;
import de.linux3000.networking.packets.pkts.CloudUpdateServerPacket;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CloudEventManagerImpl implements ICloudEventManager {
    @Override
    public void callEvent(IEvent iEvent) {
        System.out.println("call");
        if(iEvent instanceof CloudServerRegisterEvent) {
            CloudServerRegisterEvent event = (CloudServerRegisterEvent) iEvent;
            System.out.println("server register: "  + event);
            CloudServer server = (CloudServer) event.getServer();
            ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new CloudRegisterServerPacket(server.uniqueId(), server.serverType().name(), server.host(), server.port(), server.name()));
            System.out.println("sent");
        }
        if(iEvent instanceof CloudServerUpdateEvent) {
            System.out.println(iEvent);
            CloudServerUpdateEvent event = (CloudServerUpdateEvent) iEvent;

            ICloudServer server = event.getServer();
            System.out.println(server);
            System.out.println(server.player());
            List<UUID> uuids = new ArrayList<>();
            if(server.player() != null) {
                server.player().forEach(player -> uuids.add(player.uuid()));
            }

            ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new CloudUpdateServerPacket(server.uniqueId(), server.playerAmount(), server.host(), server.port(), server.isFull(), server.isOnline(),uuids ));
            System.out.println("sent");
        }

        if(iEvent instanceof CloudPlayerUpdateEvent) {
            CloudPlayerUpdateEvent event = (CloudPlayerUpdateEvent) iEvent;
            System.out.println(event.getPlayer());
            System.out.println(event.getPlayer().getConnectedServer().uniqueId());
            System.out.println(event.getPlayer().getConnectedProxy().uniqueId());
            ServerWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new CloudUpdatePlayerPacket(event.getPlayer().uuid(), event.getPlayer().getConnectedServer() == null ? "null" : event.getPlayer().getConnectedServer().uniqueId().toString(), event.getPlayer().getConnectedProxy() == null ? "null" :event.getPlayer().getConnectedProxy().uniqueId().toString() ));
            System.out.println("sent");
        }

        //invoke listener
        System.out.println("invoking");
        for(EventListener listener : ServerWrapper.getINSTANCE().getListeners()) {
            Class<? extends EventListener> listenerClass = listener.getClass();
            System.out.println(listenerClass);
            for(Method method : listenerClass.getMethods()) {
                Class<?>[] parameter = method.getParameterTypes();
                if(parameter.length == 1) {
                    if(parameter[0] == iEvent.getClass()) {
                        try {
                            method.invoke(listener, iEvent);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}

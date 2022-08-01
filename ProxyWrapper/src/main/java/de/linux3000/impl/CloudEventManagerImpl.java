package de.linux3000.impl;

import de.linux300.api.event.EventListener;
import de.linux300.api.event.IEvent;
import de.linux300.api.event.events.CloudPlayerRegisterEvent;
import de.linux300.api.event.events.CloudPlayerUpdateEvent;
import de.linux300.api.event.events.CloudServerRegisterEvent;
import de.linux300.api.event.events.CloudServerUpdateEvent;
import de.linux300.api.manager.ICloudEventManager;
import de.linux300.api.server.ICloudServer;
import de.linux300.api.server.ServerTypes;
import de.linux3000.ProxyWrapper;

import de.linux3000.networking.packets.pkts.CloudRegisterPlayerPacket;
import de.linux3000.networking.packets.pkts.CloudRegisterServerPacket;
import de.linux3000.networking.packets.pkts.CloudUpdatePlayerPacket;
import de.linux3000.networking.packets.pkts.CloudUpdateServerPacket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CloudEventManagerImpl implements ICloudEventManager {
    @Override
    public void callEvent(IEvent iEvent) {
        System.out.println("call");
    if(iEvent instanceof CloudServerRegisterEvent) {
        CloudServerRegisterEvent event = (CloudServerRegisterEvent) iEvent;
        System.out.println(event.getServer().host());
        if(event.getServer().serverType().equals(ServerTypes.PROXY)) {
        ProxyWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new CloudRegisterServerPacket(event.getServer().uniqueId(), event.getServer().serverType().name(), "0.0.0.0",25577, event.getServer().name()));
        System.out.println("sent"); }
    }
        else if(iEvent instanceof CloudServerUpdateEvent) {
            CloudServerUpdateEvent event = (CloudServerUpdateEvent) iEvent;

            ICloudServer server = event.getServer();
            if(server.uniqueId().equals(ProxyWrapper.server.uniqueId())) {
                List<UUID> uuids = new ArrayList<>();
                server.player().forEach(player -> uuids.add(player.uuid()));
                ProxyWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new CloudUpdateServerPacket(server.uniqueId(), server.playerAmount(), server.host(), server.port(), server.isFull(), server.isOnline(),uuids ));
                System.out.println("sent");
            }
        }
        else if(iEvent instanceof CloudPlayerRegisterEvent) {
            CloudPlayerRegisterEvent event = (CloudPlayerRegisterEvent) iEvent;
            ProxyWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new CloudRegisterPlayerPacket(event.getPlayer().name(), event.getPlayer().uuid()));
        System.out.println("sent");
        }
        else if(iEvent instanceof CloudPlayerUpdateEvent) {
            CloudPlayerUpdateEvent event = (CloudPlayerUpdateEvent)iEvent;

            ProxyWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new CloudUpdatePlayerPacket(event.getPlayer().uuid(), event.getPlayer().getConnectedServer() == null ? "null" : event.getPlayer().getConnectedServer().uniqueId().toString(), event.getPlayer().getConnectedProxy() == null ? "null" :event.getPlayer().getConnectedProxy().uniqueId().toString() ));
    }


    //invoke listener

        for(EventListener listener : ProxyWrapper.getINSTANCE().getListeners()) {

            Class<? extends EventListener> listenerClass = listener.getClass();

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

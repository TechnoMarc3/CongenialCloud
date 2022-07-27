package de.linux3000.impl;

import de.linux300.api.event.EventListener;
import de.linux300.api.event.IEvent;
import de.linux300.api.event.events.CloudServerRegisterEvent;
import de.linux300.api.manager.ICloudEventManager;
import de.linux3000.ProxyWrapper;
import de.linux3000.networking.packets.Packet;
import de.linux3000.networking.packets.pkts.CloudRegisterServerPacket;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CloudEventManagerImpl implements ICloudEventManager {
    @Override
    public void callEvent(IEvent iEvent) {
        System.out.println("call");
    if(iEvent instanceof CloudServerRegisterEvent) {
        CloudServerRegisterEvent event = (CloudServerRegisterEvent) iEvent;
        ProxyWrapper.getINSTANCE().getConnectionToManager().writeAndFlush(new CloudRegisterServerPacket(event.getServer().uniqueId(), event.getServer().serverType().name()));
        System.out.println("sent");
    }

    //invoke listener
        System.out.println("invoking");
        for(EventListener listener : ProxyWrapper.getINSTANCE().getListeners()) {
            System.out.println("listener: " + listener);
            Class<? extends EventListener> listenerClass = listener.getClass();
            System.out.println(listenerClass);
            for(Method method : listenerClass.getMethods()) {
                System.out.println(method);
                Class<?>[] parameter = method.getParameterTypes();
                System.out.println(Arrays.toString(parameter));
                System.out.println(parameter.length);
                if(parameter.length == 1) {
                    System.out.println("ja");
                    if(parameter[0] == iEvent.getClass()) {
                        System.out.println("doppel ja");
                        try {
                            method.invoke(null, iEvent);
                            System.out.println("invoke");
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}

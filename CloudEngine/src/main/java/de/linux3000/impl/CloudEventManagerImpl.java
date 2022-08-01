package de.linux3000.impl;

import de.linux300.api.event.EventListener;
import de.linux300.api.event.IEvent;
import de.linux300.api.manager.ICloudEventManager;
import de.linux3000.Cloud;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CloudEventManagerImpl implements ICloudEventManager {
    @Override
    public void callEvent(IEvent iEvent) {


        System.out.println("call: " + iEvent);
        for(EventListener listener : Cloud.getINSTANCE().getListeners()) {
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

package de.linux300.api.manager;

import de.linux300.api.event.IEvent;

public interface ICloudEventManager {

    void callEvent(IEvent event);

}

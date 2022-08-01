package de.linux300.api.event.events;

import de.linux300.api.event.IEvent;
import de.linux300.api.player.ICloudPlayer;

public class CloudPlayerUpdateEvent implements IEvent {

    private ICloudPlayer player;

    public CloudPlayerUpdateEvent(ICloudPlayer player) {
        this.player = player;
    }

    public ICloudPlayer getPlayer() {
        return player;
    }
}

package de.linux300.api;

import de.linux300.api.manager.ICloudPlayerManager;
import de.linux300.api.manager.ICloudServerManager;
import de.linux300.api.versions.Versions;

public abstract class CloudApi implements ICloudApi{

    static CloudApi INSTANCE;



    public CloudApi() {
        INSTANCE = this;
    }

    public static CloudApi getINSTANCE() {
        return INSTANCE;
    }



    public static void setInstance(CloudApi INSTANCE) {
        CloudApi.INSTANCE = INSTANCE;
    }
}

package de.linux3000.base;

public enum SubCloudServer {



    LOBBY("Lobby"),
    HUB("Hub");



    private String name;

    SubCloudServer(String name) {
        this.name = name;
    }
}

package de.linux300.api.versions;



import java.io.File;

public enum Versions {
    SPIGOT_1_19("server", new File("")),
    SPIGOT_1_16_4("server", new File("")),
    NULL("any", new File(""));

    private String key;
    private File file;

    Versions(String key, File file) {
        this.key = key;
        this.file = file;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}

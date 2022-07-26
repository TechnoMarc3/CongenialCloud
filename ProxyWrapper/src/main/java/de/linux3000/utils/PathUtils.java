package de.linux3000.utils;

public class PathUtils {

    public static String toWindowsString(String path) {
        String g = "";
        for(char s : path.toCharArray()) {

            if(s == "\"".charAt(0)) {
                g+="\\\"";
            }else {g+=s; }
        }
        return g;
    }

}

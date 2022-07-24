package de.linux3000.utils;

public class StringUtils {

    public static boolean onlyDigits(String str)
    {
        for (int i = 0; i < str.length()-1; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}

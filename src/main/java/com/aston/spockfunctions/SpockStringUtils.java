package com.aston.spockfunctions;

public class SpockStringUtils {

    public String removeA (String string) {
        return string.replaceAll("A", "");
    }

    public String clearSpaces (String string) {
        return string.replaceAll(" ", "");
    }
}

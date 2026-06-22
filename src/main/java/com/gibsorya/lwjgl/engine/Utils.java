package com.gibsorya.lwjgl.engine;

import java.io.IOException;
import java.nio.file.*;

public class Utils {
    private Utils() {
    }

    public static String readFile(String filePath) {
        String code;
        try {
            code = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch(IOException excp) {
            throw new RuntimeException("Error reading file: [ " + filePath + " ]", excp);
        }

        return code;
    }
}

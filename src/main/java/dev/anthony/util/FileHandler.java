package dev.anthony.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler {
    public static void saveLine(String path, String content) {
        try {
            Files.writeString(Path.of(path), content + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (Exception e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    public static String readContent(String path) {
        try {
            Path p = Path.of(path);
            return Files.exists(p) ? Files.readString(p) : "";
        } catch (Exception e) {
            return "";
        }
    }
}

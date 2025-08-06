package com.delhivery.core.utils;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class FileUtils {
    public static void removeFile(List<String> filePathList) {
        filePathList.stream()
                .map(File::new)
                .filter(File::exists)
                .filter(File::isDirectory)
                .map(File::listFiles)
                .filter(Objects::nonNull)
                .flatMap(Arrays::stream)
                .forEach(File::delete);
        System.out.println("Files removed successfully.");
    }

    public static void createFileIfNotExist(String filePath) {
        try {
            File directory = new File(filePath);
            if (!directory.exists()) {
                directory.mkdirs();
                System.out.println("Directory created successfully");
            } else {
                System.out.println("Directory already exists");
            }
        } catch (Exception e) {
            System.out.println("Error creating directory: " + e.getMessage());
        }
    }
}

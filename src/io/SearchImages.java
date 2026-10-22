package io;

import java.io.File;

import static utils.ConsoleIO.*;

public class SearchImages {

    public static File[] getTiffs(String filePath) {
        File directory = new File(filePath);
        File[] tiffFiles = directory.listFiles(((dir, name) -> name.toLowerCase().endsWith(".tif")));

        if (tiffFiles == null) {
            tiffFiles = new File[0];
        }

        if (tiffFiles.length == 0) {
            println("No Tiff files found!");
        }

        return tiffFiles;
    }
}

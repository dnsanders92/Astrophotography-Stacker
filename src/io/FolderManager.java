package io;

import java.io.File;

import static utils.ConsoleIO.*;

public class FolderManager {

    public static String getFolderName() {
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";

        while (true) {
            println("Enter folder name:");
            String folderName = readLine();

            File targetFolder = new File(desktopPath, folderName);

            if (targetFolder.exists()) {
                println("Directory Found!");
                return targetFolder.getAbsolutePath();
            } else {
                clearConsole();
                println("Folder does not exist, please enter valid name:");
            }
        }
    }
}

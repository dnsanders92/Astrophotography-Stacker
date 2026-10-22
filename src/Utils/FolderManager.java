package Utils;

import java.io.File;
import java.util.Scanner;

public class FolderManager {

    public static String getFolderName() {
        Scanner scanner = new Scanner(System.in);
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        String fullPath = "";

        while (true) {
            System.out.println("Enter folder name:");
            String folderName = scanner.nextLine();

            File targetFolder = new File(desktopPath, folderName);

            if (targetFolder.exists()) {
                System.out.println("Directory Found!");
                return targetFolder.getAbsolutePath();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Folder does not exist, please enter valid name:");
            }
        }
    }
}

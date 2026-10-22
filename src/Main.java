import Utils.FolderManager;
import Utils.ImageConverter;

import java.awt.image.BufferedImage;
import java.io.File;

import static Utils.ConsoleIO.*;
import static Utils.SearchImages.*;
import static Utils.LoadImage.*;

public class Main {
    public static void main(String[] args) {

        String filePath = FolderManager.getFolderName();
        File[] tiffFiles = getTiffs(filePath);

        printArray(tiffFiles);
        BufferedImage image = getImage(tiffFiles[1]);

        int[][] imageArray = ImageConverter.toMonoArray(image);
        println(String.valueOf(imageArray[143][594]));
    }
}
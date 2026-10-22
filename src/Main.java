import io.FolderManager;
import io.ImageConverter;

import java.awt.image.BufferedImage;
import java.io.File;

import static utils.ConsoleIO.*;
import static io.SearchImages.*;
import static io.LoadImage.*;

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
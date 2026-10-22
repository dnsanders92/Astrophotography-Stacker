import io.FolderManager;
import io.ImageConverter;
import pixelforge.RawImage;

import java.awt.image.BufferedImage;
import java.io.File;

import static utils.ConsoleIO.*;
import static io.SearchImages.*;
import static io.LoadImage.*;

public class Main {
    public static void main(String[] args) {

        // Prompts user for folder on desktop and returns full file path.
        String filePath = FolderManager.getFolderName();

        // Loads all file paths for each image in destination folder that has extension .tif
        File[] tiffFiles = getTiffs(filePath);

        // Prints out all .tiff filePaths from destination folder.
        printArray(tiffFiles);

        // Loads a file path as a buffered image.
        BufferedImage image = getImage(tiffFiles[1]);

        // Converts buffered image into a RawImage object which is an int[][].
        RawImage raw = ImageConverter.toMonoArray(image);

        // Prints the value from an xy coordinate within the RawImage raw.
        println(String.valueOf(raw.get(583, 792)));
    }
}
import io.FolderManager;
import io.ImageConverter;
import pixelforge.RGBImage;
import pixelforge.RawImage;

import java.awt.image.BufferedImage;
import java.io.File;

import static utils.ConsoleIO.*;
import static io.SearchImages.*;
import static io.LoadImage.*;
import static pixelforge.Debayer.*;

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

        RGBImage rgbImage = debayer(raw);

        // Prints the value from a xy coordinate within the RawImage raw and then corresponding pixels in RGBImage.
        println(String.valueOf(raw.get(128, 128)));
        println(String.valueOf(rgbImage.get(128, 128, 0)));

        println(String.valueOf(raw.get(129, 128)));
        println(String.valueOf(rgbImage.get(129, 128, 1)));

        println(String.valueOf(raw.get(129, 129)));
        println(String.valueOf(rgbImage.get(129, 129, 2)));
    }
}
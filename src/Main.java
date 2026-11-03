import io.FolderManager;
import io.ImageManager;
import pixelforge.RGBImage;
import pixelforge.RawImage;
import pixelforge.Stacking;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static io.SearchImages.*;
import static io.ImageManager.*;
import static pixelforge.Debayer.*;
import static io.ImageConverter.*;
import static utils.ConsoleIO.*;

public class Main {
    public static void main(String[] args) {

        // Prompts user to enter a valid file name on desktop;
        String filePath = FolderManager.getFolderName();

        // Gets all file paths for .tiff files in target folder.
        File[] files = getTiffs(filePath);

        // Debayer and save images with prefix debayered_
        for(int i = 0; i < files.length; i++) {

            BufferedImage currentImage = getImage(files[i]);
            RawImage rawImage = toMonoArray(currentImage);
            currentImage.flush();

            RGBImage rgbImage = debayer(rawImage, "RGGB");
            BufferedImage bufferedImage = toBufferedImageRGB(rgbImage);

            File outputFile = new File(files[i].getParent(), "debayered_" + files[i].getName());
            saveImage(bufferedImage, outputFile);

            bufferedImage.flush();

            println("Image " + (i + 1) + "/" + files.length + " saved");
        }

        /*
            Images are currently outsourced to siril to register the images.
            Image registration will be done within this application is a future iteration.
         */

        println("Press Y to confirm images registered in Siril");
        println("Ensure registered images are on target folder");

        // Checks decision is equal to y
        while (true) {
            String decision = "";
            decision = readLine().toLowerCase(Locale.ROOT);

            if (decision.equals("y")) {
                break;
            } else {
                println("Invalid Input, Enter Y to confirm!");
            }
        }

        clearConsole();
        println("starting image stacking...");

        // Gets all file paths for .tiff files in target folder.
        File[] allTiffs = getTiffs(filePath);

        // Creates new file list.
        List <File> registeredList = new ArrayList<>();

        // Gets file paths for all tiffs with prefix registered_
        for (int i = 0; i < allTiffs.length; i++) {
            if (allTiffs[i].getName().startsWith("registered_")) {
                registeredList.add(allTiffs[i]);
            }
        }

        // Sets list to a file array
        File[] registeredFiles = registeredList.toArray(new File[0]);

        // Stacks images and loads it as a buffered image
        RGBImage stackedImage = Stacking.meanStacking(registeredFiles);
        BufferedImage finalStacked = toBufferedImageRGB(stackedImage);

        // Save stacked 16 bit image as "stackedImage.tiff" to target folder.
        File stackedFile = new File(filePath, "stackedImage.tiff");
        saveImage(finalStacked, stackedFile);

        println("Stacked image saved successfully as stackedImage.tiff in target folder");

    }
}
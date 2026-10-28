package pixelforge;

import java.awt.image.BufferedImage;
import java.io.File;

import static io.LoadImage.*;
import static io.ImageConverter.*;
import static utils.ConsoleIO.*;

public class Stacking {

    public static int[][][] meanStacking(File[] imagePaths) {

        BufferedImage refImage = getImage(imagePaths[0]);
        int height = refImage.getHeight();
        int width = refImage.getWidth();

        int[][][] imageStack = new int[3][height][width];

        for (int i = 0; i < imagePaths.length; i++) {

            BufferedImage currentImage = getImage(imagePaths[i]);
            RGBImage image = toRgbArray(currentImage);

            for (int channel = 0; channel < imageStack.length; channel++) {
                for (int row = 0; row < height; row++) {
                    for (int col = 0; col < width; col++) {
                        imageStack[channel][row][col] += image.get(col, row, channel);
                    }
                }
            }

            print((i + 1) + "/" + imagePaths.length + " stacked");
            currentImage.flush();
        }

        for (int channel = 0; channel < imageStack.length; channel++) {
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    imageStack[channel][row][col] = imageStack[channel][row][col] / imagePaths.length;
                }
            }
        }

        return imageStack;
    }
}

package io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utils.ConsoleIO.*;

public class ImageManager {

    public static BufferedImage getImage(File filePath) {
        try {
            return ImageIO.read(filePath);
        } catch (IOException e) {
            println("Failed to load image: " + filePath.getName());
            return null;
        }
    }

    public static void saveImage(BufferedImage bufferedImage, File filePath) {
        try {
            ImageIO.write(bufferedImage, "tiff", filePath);
        } catch (IOException e) {
            println("Failed to save image: " + filePath.getName());
        }
    }
}

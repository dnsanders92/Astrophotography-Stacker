package Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Utils.ConsoleIO.*;

public class LoadImage {

    public static BufferedImage getImage(File filePath) {
        try {
            return ImageIO.read(filePath);
        } catch (IOException e) {
            println("Failed to load image: " + filePath.getName());
            return null;
        }
    }
}

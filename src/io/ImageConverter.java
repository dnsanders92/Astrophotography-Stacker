package io;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class ImageConverter {

    public static int[][] toMonoArray(BufferedImage image) {
        Raster raster = image.getRaster();

        int height = image.getHeight();
        int width = image.getWidth();

        int[][] imageArray = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                imageArray[y][x] = raster.getSample(x, y, 0);
            }
        }

        return imageArray;
    }
}

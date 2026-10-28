package io;

import pixelforge.RGBImage;
import pixelforge.RawImage;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class ImageConverter {

    public static RawImage toMonoArray(BufferedImage image) {
        Raster raster = image.getRaster();

        int height = image.getHeight();
        int width = image.getWidth();

        int[][] monoArray = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                monoArray[y][x] = raster.getSample(x, y, 0);
            }
        }

        return new RawImage(monoArray);
    }

    public static RGBImage toRgbArray(BufferedImage image) {
        Raster raster = image.getRaster();

        int height = image.getHeight();
        int width = image.getWidth();

        int[][][] rgbArray = new int[3][height][width];

        for(int channel = 0; channel < rgbArray.length; channel++) {
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    rgbArray[channel][y][x] = raster.getSample(x, y, channel);
                }
            }
        }

        return new RGBImage(rgbArray);
    }
}

package io;

import pixelforge.RGBImage;
import pixelforge.RawImage;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;

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

    public static BufferedImage toBufferedImageRGB(RGBImage rgbImage) {
        int height = rgbImage.getHeight();
        int width = rgbImage.getWidth();

        ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        ColorModel colorModel = new ComponentColorModel(
          colorSpace,
          false,
          false,
          Transparency.OPAQUE,
          DataBuffer.TYPE_USHORT
        );

        WritableRaster raster = Raster.createInterleavedRaster(
                DataBuffer.TYPE_USHORT,
                width,
                height,
                width * 3,
                3,
                new int[]{0, 1, 2},
                null
        );

        BufferedImage bufferedImage = new BufferedImage(
                colorModel,
                raster,
                colorModel.isAlphaPremultiplied(),
                null
        );

        int[] pixel = new int[3];

        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {

                pixel[0] = rgbImage.get(col, row, 0);
                pixel[1] = rgbImage.get(col, row, 1);
                pixel[2] = rgbImage.get(col, row, 2);

                raster.setPixel(col, row, pixel);
            }
        }

        return bufferedImage;
    }
}

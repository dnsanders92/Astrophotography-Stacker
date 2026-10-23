package pixelforge;

import java.util.Objects;

public class BayerChannels {

    public static int[][][] extract (RawImage rawImage, String bayerProfile) {
        int height = rawImage.getHeight();
        int width = rawImage.getWidth();

        int[][][] data = new int[3][height][width];

        if (Objects.equals(bayerProfile, "RGGB")) {
            data = RGGB(rawImage, height, width);
        }

        // TODO: Add BGGR, GRBG, GBRG.
        // This iteration only uses most common bayer matrix RGGB (Red, Green, Green, Blue).

        return data;
    }

    // Splits RGGB matrix and returns non interpolated rgb int[][][].
    private static int[][][] RGGB(RawImage raw, int height, int width) {

        int[][][] rgb = new int[3][height][width];

        copyPixels(raw, rgb[0], 0, 0);
        copyPixels(raw, rgb[1], 0, 1);
        copyPixels(raw, rgb[1], 1, 0);
        copyPixels(raw, rgb[2], 1, 1);

        return rgb;
    }

    private static void copyPixels(RawImage raw, int[][] plane, int rowStart, int colStart) {
        for (int row = rowStart; row < raw.getHeight(); row += 2) {
            for (int col = colStart; col < raw.getWidth(); col += 2) {
                plane[row][col] = raw.get(col, row);
            }
        }
    }
}

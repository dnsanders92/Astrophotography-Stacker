package pixelforge;

public class Interpolation {

    public static void interpolate(int[][][] rgb, String bayerProfile) {
        if ("RGGB".equals(bayerProfile)) {
            rggb(rgb);
        } else {
            throw new IllegalArgumentException("Unsupported Bayer pattern: " + bayerProfile);
        }
    }

    private static void rggb(int[][][] rgb) {
        // rgb dimensions are [channel][height][width]
        int height = rgb[0].length;
        int width = rgb[0][0].length;

        // Interpolate red channel (channel 0)
        // First Pass (Horizontal)
        for (int row = 0; row < height; row += 2) {
            for (int col = 1; col < width; col += 2) {
                if (col + 1 < width) {
                    rgb[0][row][col] = (rgb[0][row][col - 1] + rgb[0][row][col + 1]) / 2;
                } else {
                    rgb[0][row][col] = rgb[0][row][col - 1];
                }
            }
        }

        // Second Pass (Vertical)
        for (int row = 1; row < height; row += 2) {
            for (int col = 0; col < width; col++) {
                if (row + 1 < height) {
                    rgb[0][row][col] = (rgb[0][row - 1][col] + rgb[0][row + 1][col]) / 2;
                } else {
                    rgb[0][row][col] = rgb[0][row - 1][col];
                }
            }
        }

        // Interpolate green channel (channel 1)
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {

                if ((row + col) % 2 == 0) {
                    int sum = 0;
                    int count = 0;

                    if (row > 0) {
                        sum += rgb[1][row - 1][col]; count++;
                    }
                    if (row < height - 1) {
                        sum += rgb[1][row + 1][col]; count++;
                    }
                    if (col > 0) {
                        sum += rgb[1][row][col - 1]; count++;
                    }
                    if (col < width - 1) {
                        sum += rgb[1][row][col + 1]; count++;
                    }

                    rgb[1][row][col] = sum / count;
                }
            }
        }

        // Interpolate blue channel (channel 2)
        // Pass 1 (Horizontal)
        for (int row = 1; row < height; row += 2) {
            for (int col = 0; col < width; col += 2) {
                if (col == 0) {
                    rgb[2][row][col] = rgb[2][row][col + 1];
                } else if (col + 1 < width) {
                    rgb[2][row][col] = (rgb[2][row][col - 1] + rgb[2][row][col + 1]) / 2;
                } else {
                    rgb[2][row][col] = rgb[2][row][col - 1];
                }
            }
        }

        // Pass 2 (Vertical)
        for (int row = 0; row < height; row += 2) {
            for (int col = 0; col < width; col++) {
                if (row == 0) {
                    rgb[2][row][col] = rgb[2][row + 1][col];
                } else if (row + 1 < height) {
                    rgb[2][row][col] = (rgb[2][row - 1][col] + rgb[2][row + 1][col]) / 2;
                } else {
                    rgb[2][row][col] = rgb[2][row - 1][col];
                }
            }
        }
    }
}
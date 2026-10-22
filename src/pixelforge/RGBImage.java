package pixelforge;

public class RGBImage {

    private final int[][][] data;
    private final int height;
    private final int width;

    public RGBImage(int[][][] data) {
        this.data = data;
        this.height = data[0].length;
        this.width = data[0][0].length;
    }

    public int[][][] getData() {
        return data;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int get(int x, int y, int channel) {
        return data[channel][y][x];
    }
}

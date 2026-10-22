package pixelforge;

public class RawImage {

    private final int[][] data;
    private final int height;
    private final int width;

    public RawImage(int[][] data) {
        this.data = data;
        this.height = data.length;
        this.width = data[0].length;
    }

    public int[][] getData() {
        return data;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int get(int x, int y) {
        return data[y][x];
    }
}

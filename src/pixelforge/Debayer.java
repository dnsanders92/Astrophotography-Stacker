package pixelforge;

public class Debayer {

    public static RGBImage debayer(RawImage rawImage) {

        int[][][] rgb = BayerChannels.extract(rawImage, "RGGB");

        // TODO: Interpolation logic via dedicated interpolation class.

        return new RGBImage(rgb);
    }
}

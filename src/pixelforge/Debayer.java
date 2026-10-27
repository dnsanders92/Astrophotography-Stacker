package pixelforge;

import static pixelforge.Interpolation.*;

public class Debayer {

    public static RGBImage debayer(RawImage rawImage, String bayerPattern) {

        int[][][] rgb = BayerChannels.extract(rawImage, "RGGB");

        interpolate(rgb, bayerPattern);

        return new RGBImage(rgb);
    }
}

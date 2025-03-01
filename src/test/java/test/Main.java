package test;

import com.fej1fun.ColoredNoiseMap;
import com.fej1fun.EarthBiomes;
import com.fej1fun.Noise;
import com.fej1fun.util.Util;
import de.articdive.jnoise.core.util.MathUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Noise n = new Noise(1, 0.999f, 32, 32);
        System.out.println((int) MathUtil.log2(1024));
        //BufferedImage noiseImage = Util.getImageFromNoiseMap(n.getNoiseElevationMap());
        BufferedImage noiseImage = ColoredNoiseMap.getColorfulImage(n, EarthBiomes.DESERT);

        File output = new File("image.png");
        try {
            ImageIO.write(noiseImage, "png", output);
        } catch (IOException ignored) {
        }
    }
}

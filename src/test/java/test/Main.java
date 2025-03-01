package test;

import com.fej1fun.ColoredNoiseMap;
import com.fej1fun.EarthBiomes;
import com.fej1fun.Noise;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Noise n = new Noise(80803, 5/6f, 32, 32);
        BufferedImage noiseImage = ColoredNoiseMap.getColorfulImage(n, EarthBiomes.DESERT);

        File output = new File("image.png");
        try {
            ImageIO.write(noiseImage, "png", output);
        } catch (IOException ignored) {
        }
    }
}

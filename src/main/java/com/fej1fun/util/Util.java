package com.fej1fun.util;

import com.fej1fun.BiomeInterface;
import com.fej1fun.Noise;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Util {
    public static BufferedImage getImageFromNoiseMap(double[][] noiseMap) {
        BufferedImage image = new BufferedImage(noiseMap.length, noiseMap[0].length, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < noiseMap.length; y++) {
            for (int x = 0; x < noiseMap[y].length; x++) {
                image.setRGB(x, y, MathF.lerpColor(Color.WHITE, Color.BLACK, noiseMap[x][y]).getRGB());
            }
        }
        return image;
    }

    public static <T extends BiomeInterface<? extends Enum<?>>> BufferedImage getColorfulImage(Noise n, T biomeInterface) {

        BufferedImage image = new BufferedImage(n.width, n.height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < n.height; y++)
            for (int x = 0; x < n.width; x++)
                image.setRGB(x, y, biomeInterface.getColorForNoisePoint(x, y, n, 0.2f).getRGB());

        return image;
    }
}

package com.fej1fun;

import java.awt.image.BufferedImage;

public class ColoredNoiseMap {
    public static <T extends BiomeInterface<? extends Enum<?>>> BufferedImage getColorfulImage(Noise n, T biomeInterface) {

        BufferedImage image = new BufferedImage(n.width, n.height, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < n.height; y++)
            for (int x = 0; x < n.width; x++)
                image.setRGB(x, y, biomeInterface.getColorForNoisePoint(x, y, n, 0.225f).getRGB());

        return image;
    }
}

package com.fej1fun;

import de.articdive.jnoise.core.api.functions.Interpolation;
import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.modules.octavation.fractal_functions.FractalFunction;
import de.articdive.jnoise.pipeline.JNoise;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Noise n = new Noise(3643425634563456L, 2/3f, 1000, 1000);
        BufferedImage noiseImage = ColoredNoiseMap.getColorfulImage(n, EarthBiomes.DESERT);


        File output = new File("image.png");
        try {
            ImageIO.write(noiseImage, "png", output);
        } catch (IOException ignored) {
        }

    }

    public static double[][] getNoiseMap(int width, int height, long seed, float scale) {
        if (scale <= 0)
            scale = 0.00001f;
        double[][] noiseMap = new double[width][height];

        JNoise generator = JNoise.newBuilder().perlin(seed, Interpolation.LINEAR,FadeFunction.QUINTIC_POLY).addModifier(v -> (v + 1) / 2.0).scale(scale)
                .clamp(0,1).octavate(4, 2.5, 0.575, FractalFunction.FBM, true).build();
        for (int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                noiseMap[x][y] = Math.abs(generator.evaluateNoise(x, y, 100));
            }
        }
        return noiseMap;
    }

    public static BufferedImage getImageFromNoiseMap(double[][] noiseMap) {
        BufferedImage image = new BufferedImage(noiseMap.length, noiseMap[0].length, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < noiseMap.length; y++) {
            for (int x = 0; x < noiseMap[y].length; x++) {
                 image.setRGB(x, y, MathF.lerpColor(Color.WHITE, Color.BLACK, noiseMap[x][y]).getRGB());
            }
        }
        return image;
    }
}
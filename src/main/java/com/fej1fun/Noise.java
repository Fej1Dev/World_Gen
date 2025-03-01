package com.fej1fun;

import de.articdive.jnoise.core.api.functions.Interpolation;
import de.articdive.jnoise.core.util.MathUtil;
import de.articdive.jnoise.generators.noise_parameters.fade_functions.FadeFunction;
import de.articdive.jnoise.generators.noisegen.opensimplex.SuperSimplexNoiseGenerator;
import de.articdive.jnoise.modules.octavation.fractal_functions.FractalFunction;
import de.articdive.jnoise.pipeline.JNoise;

public class Noise {
    final float scale;
    public final int width;
    public final int height;

    private long seed;

    private double[][] temperatureMap = null;
    private double[][] precipitationMap = null;
    private double[][] elevationMap = null;

    /// Scale should be between 1 and 0 exclusive
    public Noise(long seed, float scale, int width, int height) {
        this.seed = seed;

        if (scale <= 0 || scale>=1)
            throw new IndexOutOfBoundsException("scale should be between 1 and 0 exclusive. scale is: " + scale);
        this.scale = scale;

        this.width = width;
        this.height = height;
    }

    public double[][] getNoiseTemperatureMap() {
        if (temperatureMap != null) return temperatureMap;

        return temperatureMap = getPerlinNoiseMap(Interpolation.LINEAR, FadeFunction.QUINTIC_POLY, FractalFunction.FBM,
                4, 10f, 0.25f, 1,1);
    }

    public double[][] getNoisePrecipitationMap() {
        if (precipitationMap != null) return precipitationMap;

        return precipitationMap = getPerlinNoiseMap(Interpolation.LINEAR, FadeFunction.QUINTIC_POLY, FractalFunction.FBM,
                5, 3f, 0.45f, 1,1);
    }

    public double[][] getNoiseElevationMap() {
        if (elevationMap != null) return elevationMap;

        double[][] noiseMap = new double[width][height];

        JNoise generator = JNoise.newBuilder()
                .superSimplex(SuperSimplexNoiseGenerator.newBuilder().setSeed(seed).build())
                .addModifier(v -> (v + 1) / 2.0)
                .scale(scale)
                .clamp(0,1)
                .octavate((int) MathUtil.log2(Math.sqrt(width*height)), 2.01f, 0.5f, FractalFunction.FBM, true)
                .build();
        for (int y = 0; y < width; y++)
            for (int x = 0; x < height; x++)
                noiseMap[x][y] = Math.pow(generator.evaluateNoise(x, y) * 1.3, 1.95);

        return elevationMap = noiseMap;
    }

    public double[][] getPerlinNoiseMap(Interpolation inter,
                                         FadeFunction fade,
                                         FractalFunction fractal,
                                         int octaves,
                                         double gain,
                                         double lacunarity,
                                         float fudge_factor,
                                         float pow) {

        double[][] noiseMap = new double[width][height];

        JNoise generator = JNoise.newBuilder()
                .perlin(seed, inter, fade)
                .addModifier(v -> (v + 1) / 2.0)
                .scale(scale)
                .clamp(0,1)
                .octavate(octaves, gain, lacunarity, fractal, true)
                .build();
        for (int y = 0; y < width; y++)
            for (int x = 0; x < height; x++)
                noiseMap[x][y] = Math.pow(generator.evaluateNoise(x, y) * fudge_factor, pow);

        return noiseMap;
    }

    public void setSeed(long seed) {
        this.seed = seed;

        this.temperatureMap = null;
        this.precipitationMap = null;
        this.elevationMap = null;
    }
}

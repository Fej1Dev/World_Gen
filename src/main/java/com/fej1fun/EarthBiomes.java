package com.fej1fun;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;

public enum EarthBiomes implements BiomeInterface<EarthBiomes> {

    POLAR(0.2275f, 0.5f, new Color(218, 213, 213)),
    TUNDRA(0.33f, 0.5f, new Color(255, 255, 255)),
    TAIGA(0.4f, 0.675f, new Color(150, 180, 147)),
    GRASSLAND(0.56f, 0.325f, new Color(117, 193, 75)),
    MEDITERRANEAN(0.59f, 0.56f, new Color(46, 121, 38)),
    TEMPERATE_FOREST(0.69f, 0.79f, new Color(32, 91, 27)),
    STEPPE(0.695f, 0.25f, new Color(92, 112, 52)),
    DESERT(0.72f, 0.251f, new Color(225, 197, 23)),
    SAVANNAH(0.775f, 0.52f, new Color(78, 67, 10)),
    TROPICAL_RAINFOREST(0.835f, 0.77f, new Color(20, 62, 14));

    public final Point2D.Double temp_prec;
    public final Color color;

    EarthBiomes(float temperature, float precipitation, Color color) {
        this.temp_prec = new Point2D.Double(temperature, precipitation);
        this.color = color;
    }

    public static Point2D.Double[] points() {
        return (Point2D.Double[]) Arrays.stream(values()).map(biome-> biome.temp_prec).toArray();
    }

    @Override
    public final Point2D.Double getPoint() {
        return temp_prec;
    }

    @Override
    public final Color getColor() {
        return color;
    }

    public Color getColorForNoisePoint(int x, int y, Noise n, float waterCutoff) {
        double elev = n.getNoiseElevationMap()[x][y];
        double temp = n.getNoiseTemperatureMap()[x][y];
        if (elev > 0.9f) return Color.GRAY; //Mountain Rock
        if (elev > 0.8f) return Color.WHITE; //Snow
        if (elev < waterCutoff) return Color.BLUE;

        double prec = n.getNoisePrecipitationMap()[x][y];

        Point2D.Double point = new Point2D.Double(temp, prec);
        EarthBiomes biomeMin = values()[0];
        double minBiomeDis = Double.MAX_VALUE;

        double biomeDis;
        for (EarthBiomes b : values()) {
            biomeDis = b.getPoint().distanceSq(point);
            if (biomeDis < minBiomeDis) {
                minBiomeDis = biomeDis;
                biomeMin = b;
            }
        }

        return biomeMin.getColor();
    }
}

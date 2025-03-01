package com.fej1fun;

import java.awt.*;
import java.awt.geom.Point2D;

public interface BiomeInterface<E extends Enum<E>> {
    Point2D.Double getPoint();
    Color getColor();

    /// Use negative number for waterCutoff for no water
    Color getColorForNoisePoint(int x, int y, Noise n, float waterCutoff);
}

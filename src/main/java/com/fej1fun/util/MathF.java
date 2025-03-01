package com.fej1fun.util;

import java.awt.*;

public class MathF {
    public static double lerp(double a, double b, double t) {
        t = Math.clamp(t,0,1);
        return a + (b - a) * t; // returns v
    }

    public static double inverseLerp(double a, double b, double v) {
        return (v - a) / (b - a); // returns t
    }

    public static Color lerpColor(Color a, Color b, double t) {
        return new Color(
                (int)lerp(a.getRed(), b.getRed(), t),
                (int)lerp(a.getGreen(), b.getGreen(), t),
                (int)lerp(a.getBlue(), b.getBlue(), t)
        );
    }


}

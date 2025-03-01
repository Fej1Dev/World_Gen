package com.fej1fun.util;

import javax.swing.*;
import java.awt.*;

public class ShowImage extends JFrame {

    private Image img;

    public ShowImage(Image img) {
        super("Noise");
        this.img = img;

        setSize(img.getWidth(this),img.getHeight(this));

        Color c = Color.WHITE;
        JPanel p = new JPanel();
        p.setBackground(c);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(p);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(img,0,0, this);
    }
}

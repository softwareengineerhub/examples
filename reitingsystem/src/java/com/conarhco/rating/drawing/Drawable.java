/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.conarhco.rating.drawing;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author 777
 */
public class Drawable {

    private int imgWidth;
    private int imgHeight;
    private Integer[][] values;
    private int pointsAmount;
    private List<Color> colorList;

    public Drawable(int width, int height, Integer[][] values, List<Color> colors) {
        if (width == 0 || height == 0 || values == null || colors == null) {
            throw new IllegalStateException("Incorrect parameters to draw");
        }
        this.imgWidth = width;
        this.imgHeight = height;
        this.values = values;
        pointsAmount = values[0].length;
        colorList = colors;
    }

    public BufferedImage draw() {

        BufferedImage img = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        g2.setColor(Color.white);
        g2.fillRect(0, 0, imgWidth, imgHeight);
        g2.setColor(Color.GRAY);
        Stroke old = g2.getStroke();
        float dash1[] = {10.0f};
        BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
        g2.setStroke(dashed);
        double w = ((float) imgWidth) / (pointsAmount -1);
        double h = ((float) imgHeight) / 6;
        for (int i = 0; i < pointsAmount; i++) {
            g2.draw(new Line2D.Double(i * w, 0, w * i, imgHeight));
        }
        for (int i = 0; i < 12; i++) {
            g2.draw(new Line2D.Double(0, i * h, imgWidth, i * h));
        }
        g2.setStroke(old);
        int height = imgHeight - 10;
        int max = getMax(values);
        if (max == 0) {
            max = 1;
        }
        double scale = height / max;
        for (int j = 0; j < values.length; j++) {
            for (int i = 1; i < values[j].length; i++) {
                Color c = colorList.get(j);
                g2.setColor(c);
                g2.draw(new Line2D.Double((i - 1) * w, height - values[j][i - 1] * scale, (i) * w, height - values[j][i] * scale));
                if (values[j][i - 1] == values[j][i]) {
                    g2.draw(new Line2D.Double((i - 1) * w, -1 + height - values[j][i - 1] * scale, (i) * w, height - values[j][i] * scale - 1));
                    g2.draw(new Line2D.Double((i - 1) * w, 1 + height - values[j][i - 1] * scale, (i) * w, height - values[j][i] * scale + 1));
                } else {
                    g2.draw(new Line2D.Double((i - 1) * w - 1, height - values[j][i - 1] * scale, (i) * w - 1, height - values[j][i] * scale));
                    g2.draw(new Line2D.Double((i - 1) * w + 1, height - values[j][i - 1] * scale, (i) * w + 1, height - values[j][i] * scale));
                }
            }
        }
        g2.setColor(Color.BLACK);
        g2.draw(new Line2D.Double(0, 0, 0, imgHeight));
        g2.draw(new Line2D.Double(1, 0, 1, imgHeight));
        g2.draw(new Line2D.Double(2, 0, 2, imgHeight));
        g2.draw(new Line2D.Double(0, imgHeight, imgWidth, imgHeight));
        g2.draw(new Line2D.Double(0, imgHeight - 1, imgWidth, imgHeight - 1));
        g2.draw(new Line2D.Double(0, imgHeight - 2, imgWidth, imgHeight - 2));
        g2.dispose();
        return img;
    }

    private int getMax(Integer[][] src) {
        int max = getMax(src[0]);
        for (int i = 1; i < src.length; i++) {
            max = Math.max(getMax(src[i]), max);
        }
        return max;
    }

    private int getMax(Integer[] src) {
        if (src.length == 0) {
            return 0;
        }
        int max = src[0];
        for (int i = 1; i < src.length; i++) {
            max = Math.max(src[i], max);
        }
        return max;
    }
}

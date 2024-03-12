package core.level.room.contents.lighting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.Panel;
import core.level.room.Point;

public class RadialLight extends Light {
    private int radius;

    public RadialLight(Point point, int radius, float luminosity) {
        super(point, luminosity);
        this.radius = radius;
        build();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, (int) point.getX() - image.getWidth() / 2,
                (int) point.getY() - image.getHeight() / 2, image.getWidth(), image.getHeight(),
                null);
    }

    public void build() {
        image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        Panel.setHints(g2d);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < radius; i++) {
            double luma = 1.0D - Math.pow((i + 0.001) / radius, 0.1);
            int alpha = Math.min((int) (255.0D * luma * luminosity), 255);
            g2d.setColor(new Color(0, 0, 0, alpha));
            g2d.drawOval(radius - i, radius - i, 2 * i, 2 * i);
        }
        g2d.dispose();
    }

    public void setRadius(int radius) {
        this.radius = radius;
        build();
    }
}

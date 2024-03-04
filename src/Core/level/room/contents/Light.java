package core.level.room.contents;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.level.room.Point;

public class Light {
    private BufferedImage image;
    private Point point;

    public Light(Point point, int radius, float luminosity) {
        this.point = point;
        image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        for (int i = 0; i < radius; i++) {
            double luma = 1.0D - ((i + 0.001) / radius);
            int alpha = Math.min((int) (255.0D * luma * luminosity), 255);
            g2d.setColor(new Color(0, 0, 0, alpha));
            g2d.setStroke(new BasicStroke(2));
            g2d.drawOval(radius - i / 2, radius - i / 2, i, i);
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, point.getX() - image.getWidth() / 2, point.getY() - image.getHeight() / 2, image.getWidth(), image.getHeight(),
                null);
    }

}

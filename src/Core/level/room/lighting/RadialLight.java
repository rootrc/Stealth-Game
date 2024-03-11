package core.level.room.lighting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import core.level.room.Point;
import core.level.room.objects.TileToScreen;

public class RadialLight extends Light {
    private int radius;

    public RadialLight(Point point, int radius, float luminosity) {
        super(point, luminosity);
        this.radius = radius;
        build();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, (int) point.getX() - image.getWidth() / 2 - TileToScreen.adjustX,
                (int) point.getY() - image.getHeight() / 2 - TileToScreen.adjustY, image.getWidth(), image.getHeight(),
                null);
    }

    public void build() {
        image = new BufferedImage(radius * 2, radius * 2, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        // will cause lag
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
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

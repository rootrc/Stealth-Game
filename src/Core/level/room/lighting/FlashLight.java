package core.level.room.lighting;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import core.level.room.Point;

public class FlashLight extends Light{
    private int minX;
    private int minY;

    public FlashLight(Point point, int distance, double angle, double beamWidth, float luminosity) {
        super(point);
        int x2 = (int) (distance * Math.cos(angle + beamWidth / 2));
        int y2 = (int) (distance * Math.sin(angle + beamWidth / 2));
        int x3 = (int) (distance * Math.cos(angle - beamWidth / 2));
        int y3 = (int) (distance * Math.sin(angle - beamWidth / 2));
        minX = Math.min(0, Math.min(x2, x3));
        int maxX = Math.max(0, Math.max(x2, x3));
        minY = Math.min(0, Math.min(y2, y3));
        int maxY = Math.max(0, Math.max(y2, y3));
        image = new BufferedImage(maxX - minX, maxY - minY, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        // will cause lag
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < distance; i += 2) {
            double luma = 1.0D - Math.pow((i + 0.001) / distance, 0.01);
            int alpha = Math.min((int) (255.0D * luma * luminosity), 255);
            g2d.setColor(new Color(0, 0, 0, alpha));
            Polygon beam = new Polygon();
            beam.addPoint(-minX, -minY);
            beam.addPoint(-minX + (int) (x2 * i / distance), -minY + (int) (y2 * i / distance));
            beam.addPoint(-minX + (int) (x3 * i / distance), -minY + (int) (y3 * i / distance));
            g2d.setClip(beam);
            g2d.fillRect(0, 0, image.getWidth(), image.getHeight());
        }
        g2d.dispose();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, (int) (point.getX() + minX), (int) (point.getY() + minY), null);
    }
}
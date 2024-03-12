package core.level.room.contents.lighting;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.level.room.Point;

public abstract class Light {
    protected BufferedImage image;
    protected Point point;
    protected float luminosity;

    public Light(Point point, float luminosity) {
        this.point = point;
        this.luminosity = luminosity;
    }

    public abstract void draw(Graphics2D g2d);

    protected abstract void build();

    public Point getPoint() {
        return point;
    }

    public double getX() {
        return point.getX();
    }

    public double getY() {
        return point.getY();
    }

    public void setLuminosity(float luminosity) {
        this.luminosity = luminosity;
        build();

    }
}

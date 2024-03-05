package core.level.room.lighting;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import core.level.room.Point;

public abstract class Light {
    protected BufferedImage image;
    protected Point point;

    public Light(Point point) {
        this.point = point;
    }

    abstract void draw(Graphics2D g2d);
}

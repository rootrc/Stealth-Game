package core.level.room.contents;

import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

import core.level.room.Point;

public class Light {
    // int x;
    // int y;
    Point location;
    int radius;

    public Light(Point location, int radius) {
        this.location = location;
        this.radius = radius;
    }

    // public Light(int x, int y, int radius) {
    //     this.x = x;
    //     this.y = y;
    //     this.radius = radius;
    // }

    public Area getArea() {
        return new Area(new Ellipse2D.Double(location.getX() - radius / 2, location.getY() - radius / 2, radius, radius));
    }

}

package core.level.objects.edges;
import java.awt.Graphics2D;

import core.level.Location;
import core.level.objects.LevelObject;

public abstract class Edge extends LevelObject {
    protected int x1, y1;
    protected int x2, y2;
    protected Location location1;
    protected Location location2;
    
    public Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        location1 = new Location(x1, y1);
        location2 = new Location(x2, y1);
    }

    public abstract void draw(Graphics2D g2d);

    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getX2() {
        return x2;
    }

    public int getY2() {
        return y2;
    }

    public Location getLocation1() {
        return location1;
    }

    public Location getLocation2() {
        return location2;
    }
}

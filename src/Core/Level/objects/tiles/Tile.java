package core.level.objects.tiles;
import java.awt.Graphics2D;

import core.level.Location;
import core.level.objects.LevelObject;
import core.level.objects.edges.Edge;

public abstract class Tile extends LevelObject {
    protected int x;
    protected int y;
    protected Location location;
    protected Edge edges[];

    public Tile(int x, int y, Edge[] edges) {
        this.x = x;
        this.y = y;
        location = new Location(x, y);
        this.edges = edges;
    }

    public void draw(Graphics2D g2d) {
        for (Edge edge: edges) {
            edge.draw(g2d);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Location getLocation() {
        return location;
    }

    public Edge getEdge(int i) {
        return edges[i];
    }
}

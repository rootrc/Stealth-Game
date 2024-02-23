package Core.Level.LevelElement.Tile;
import java.awt.Graphics2D;

import Core.Level.LevelElement.LevelElement;
import Core.Level.LevelElement.Edge.Edge;

public abstract class Tile extends LevelElement {
    protected int x;
    protected int y;
    protected Edge edges[];

    public Tile(int x, int y, Edge[] edges) {
        this.x = x;
        this.y = y;
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
}

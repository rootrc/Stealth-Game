package Core.Level.Tile;
import java.awt.Graphics2D;

import Core.Level.LevelElement;
import Core.Level.Edge.Edge;

public abstract class Tile extends LevelElement {
    int x;
    int y;
    Edge edges[] = new Edge[4];

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Graphics2D g2d);
}

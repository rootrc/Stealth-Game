package Core.Level.LevelElement.Tile;
import java.awt.Graphics2D;

import Core.Level.LevelElement.Edge.Edge;

public class EmptyTile extends Tile {
    public EmptyTile(int x, int y, Edge[] edges) {
        super(x, y, edges);
        collidable = false;
        seeThrough = true;
    }

    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }
}

package core.level.objects.tiles;
import java.awt.Graphics2D;

import core.level.objects.edges.Edge;

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

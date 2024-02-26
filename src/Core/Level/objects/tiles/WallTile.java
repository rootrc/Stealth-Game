package core.level.objects.tiles;
import java.awt.Graphics2D;

import core.level.objects.edges.Edge;

public class WallTile extends Tile {
    public WallTile(int x, int y, Edge[] edges) {
        super(x, y, edges);
        collidable = true;
        seeThrough = false;
    }
    
    public void draw(Graphics2D g2d) {
        super.draw(g2d);
    }
}

package core.level.room.objects.edges;
import java.awt.Graphics2D;

import core.level.room.objects.TileToScreen;

public class WallEdge extends Edge {
    public WallEdge(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        collidable = true;
        seeThrough = false;
    }

    public void draw(Graphics2D g2d) {
        g2d.drawLine(TileToScreen.xToScreenX(x1),TileToScreen.yToScreenY(y1), TileToScreen.xToScreenX(x2), TileToScreen.yToScreenY(y2));
    }
}

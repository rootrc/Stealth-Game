package core.level.room.objects.edges;
import java.awt.Color;
import java.awt.Graphics2D;

import core.level.room.objects.Edge;
import core.level.room.objects.TileToScreen;

public class WallEdge extends Edge {
    public WallEdge(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        collidable = true;
        seeThrough = false;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.black);
        g2d.drawLine(TileToScreen.toScreen(x1), TileToScreen.toScreen(y1), TileToScreen.toScreen(x2), TileToScreen.toScreen(y2));
    }
}

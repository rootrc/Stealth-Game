package core.level.room.objects.edges;
import java.awt.Graphics2D;

import core.level.room.objects.Edge;

public class EmptyEdge extends Edge {
    public EmptyEdge(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        collidable = false;
        seeThrough = true;
    }

    public void draw(Graphics2D g2d) {
        
    }
}

package core.level.objects.edges;
import java.awt.Graphics2D;

public class WallEdge extends Edge {
    public WallEdge(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        collidable = true;
        seeThrough = false;
    }

    public void draw(Graphics2D g2d) {
        // test
        g2d.drawLine(32 * x1 + 32, 32 * y1 + 32, 32 * x2 + 32, 32 * y2 + 32);
    }
}

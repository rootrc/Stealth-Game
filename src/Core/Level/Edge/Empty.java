package Core.Level.Edge;
import java.awt.Graphics2D;

public class Empty extends Edge {
    public Empty(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        collidable = false;
        seeThrough = true;
    }

    public void draw(Graphics2D g2d) {
        
    }
}

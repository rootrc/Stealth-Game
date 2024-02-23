package Core.Level.Tile;
import java.awt.Graphics2D;

public class Empty extends Tile {
    public Empty(int x, int y) {
        super(x, y);
        collidable = false;
        seeThrough = true;
    }

    public void draw(Graphics2D g2d) {
        
    }
}

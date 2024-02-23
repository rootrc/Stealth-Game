package Core.Level.Tile;
import java.awt.Graphics2D;

public class Wall extends Tile {
    public Wall(int x, int y) {
        super(x, y);
        collidable = true;
        seeThrough = false;
    }
    
    public void draw(Graphics2D g2d) {
        g2d.fillRect(32*x, 32*y, 32, 32);
    }
}

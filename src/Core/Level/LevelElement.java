package Core.Level;
import java.awt.Graphics2D;

public abstract class LevelElement {
    public boolean collidable;
    public boolean seeThrough;

    public LevelElement() {
        
    }

    public abstract void draw(Graphics2D g2d);

}

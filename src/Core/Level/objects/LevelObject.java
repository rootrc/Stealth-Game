package core.level.objects;
import java.awt.Graphics2D;

public abstract class LevelObject {
    protected boolean collidable;
    protected boolean seeThrough;

    public abstract void draw(Graphics2D g2d);

    public boolean getCollidable() {
        return collidable;
    }

    public boolean getSeeThrough() {
        return seeThrough;
    }
    
}

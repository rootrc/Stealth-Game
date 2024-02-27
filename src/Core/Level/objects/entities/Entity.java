package core.level.objects.entities;

import java.awt.Graphics2D;

import core.level.Level;
import core.level.Point;
import core.level.objects.TileToScreen;

public abstract class Entity {
    protected Level level;
    protected int x;
    protected int y;
    protected int screenX;
    protected int screenY;
    protected boolean movement[];
    protected Point location;
    protected int size;
    protected int speed;

    public Entity(int x, int y, int size, int speed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        screenX = TileToScreen.xToScreenX(x) + (TileToScreen.tileSize) / 2;
        screenY = TileToScreen.yToScreenY(y) + (TileToScreen.tileSize) / 2;
    }

    public abstract void draw(Graphics2D g2d);
    public abstract void move();

    public void process() {
        x = TileToScreen.screenXToX(screenX);
        y = TileToScreen.screenYToY(screenY);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScreenX() {
        return screenX;
    }

    public int getScreenY() {
        return screenY;
    }

    public Point getLocation() {
        return location;
    }

    public boolean getMovement(int i) {
        return movement[i];
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }
}

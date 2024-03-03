package core.level.room.objects.entities;

import java.awt.Graphics2D;

import core.level.room.Point;
import core.level.room.Room;
import core.level.room.objects.TileToScreen;

public abstract class Entity {
    protected Room level;
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
        movement = new boolean[4];
        this.size = size;
        this.speed = speed;
        screenX = TileToScreen.xToScreenX(x) + (TileToScreen.tileSize) / 2;
        screenY = TileToScreen.yToScreenY(y) + (TileToScreen.tileSize) / 2;
    }

    public abstract void draw(Graphics2D g2d);
    public abstract void move(int d);

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

    public boolean getMovement(int d) {
        return movement[d];
    }

    public void setMovement(int d, boolean b) {
        movement[d] = b;
    }

    public void setMovement(boolean b) {
        for (int d = 0; d < 4; d++) {
            setMovement(d, b);
        }
    }

    public int getSize() {
        return size;
    }

    public int getSpeed() {
        return speed;
    }
}

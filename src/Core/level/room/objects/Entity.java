package core.level.room.objects;

import java.awt.Graphics2D;

import core.level.room.Point;
import core.level.room.Room;

public abstract class Entity {
    protected Room level;
    protected int x;
    protected int y;
    protected int screenX;
    protected int screenY;
    protected Point location;
    protected boolean movement[];
    protected int size;
    protected int speed;

    public Entity(int x, int y, int size, int speed) {
        this.x = x;
        this.y = y;
        movement = new boolean[4];
        this.size = size;
        this.speed = speed;
        screenX = TileToScreen.toScreen(x) + (TileToScreen.tileSize) / 2;
        screenY = TileToScreen.toScreen(y) + (TileToScreen.tileSize) / 2;
        location = new Point(screenX, screenY);
    }

    public abstract void draw(Graphics2D g2d);

    public void process() {
        x = screenX / TileToScreen.tileSize;
        y = screenY / TileToScreen.tileSize;
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };
    
    public void move(int d) {
        if (movement[d]) {
            screenX += speed * direct[d][0];
            screenY += speed * direct[d][1];
            location.moveX(speed * direct[d][0]);
            location.moveY(speed * direct[d][1]);
        }
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

package core.level.objects.entities;

import java.awt.Graphics2D;

import core.level.Point;

public abstract class Entity {
    protected int x;
    protected int y;
    protected int screenX;
    protected int screenY;
    protected Point location;
    protected int size;
    protected int speed;

    public Entity(int x, int y, int size, int speed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.speed = speed;
        screenX = TileToScreen.tileSize + (TileToScreen.tileSize - size) / 2;
        screenY = TileToScreen.tileSize + (TileToScreen.tileSize - size) / 2;
    }

    public abstract void draw(Graphics2D g2d);
    public abstract void process();

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
}

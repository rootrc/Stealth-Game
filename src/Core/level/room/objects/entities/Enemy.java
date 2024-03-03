package core.level.room.objects.entities;

import java.awt.Graphics2D;

public abstract class Enemy extends Entity {
    public Enemy(int x, int y, int size, int speed) {
        super(x, y, size, speed);
    }

    public abstract void draw(Graphics2D g2d);
    public abstract void move(int d);

    public void process() {
        super.process();
    }

}

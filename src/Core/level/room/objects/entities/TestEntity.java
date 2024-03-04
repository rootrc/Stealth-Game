package core.level.room.objects.entities;

import java.awt.Color;
import java.awt.Graphics2D;

public class TestEntity extends Enemy {
    public TestEntity(int x, int y) {
        super(x, y, 16, 4);
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.red);
        g2d.fillRect(screenX - size / 2, screenY - size / 2, size, size);
    }

    public void process() {
        super.process();
    }
}
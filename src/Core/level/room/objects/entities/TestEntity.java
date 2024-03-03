package core.level.room.objects.entities;

import java.awt.Graphics2D;

import core.level.room.contents.AI;

public class TestEntity extends Enemy {
    public TestEntity(int x, int y) {
        super(x, y, 16, 4);
        movement = new boolean[4];
    }

    public void draw(Graphics2D g2d) {
        g2d.fillRect(screenX - size / 2, screenY - size / 2, size, size);
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    public void process() {
        super.process();
    }

    public void move(int d) {
        screenX += speed * direct[d][0];
        screenY += speed * direct[d][1];
    }
}
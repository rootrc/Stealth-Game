package core.level.room.objects.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Player extends Entity {
    public Player(int x, int y) {
        super(x, y, 16, 4);
    }

    public void draw(Graphics2D g2d) {
        g2d.fillRect(screenX - size / 2, screenY - size / 2, size, size);
    }

    public void process() {
        super.process();
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    public void move(int d) {
        if (movement[d]) {
            screenX += speed * direct[d][0];
            screenY += speed * direct[d][1];
        }
    }
}

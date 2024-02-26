package core.level.objects.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Player extends Entity {
    private boolean movement[];

    public Player(int x, int y) {
        super(x, y, 16, 4);
        movement = new boolean[4];
    }

    public void draw(Graphics2D g2d) {
        g2d.fillRect(screenX, screenY, size, size);
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    public void process() {
        for (int i = 0; i < 4; i++) {
            if (movement[i]) {
                screenX += speed * direct[i][0];
                screenY += speed * direct[i][1];
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            movement[0] = true;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            movement[1] = true;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            movement[2] = true;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            movement[3] = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            movement[0] = false;
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            movement[1] = false;
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            movement[2] = false;
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            movement[3] = false;
        }
    }
}

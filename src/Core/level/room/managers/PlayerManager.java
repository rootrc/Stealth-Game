package core.level.room.managers;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import core.level.room.contents.CollisionDetection;
import core.level.room.objects.entities.Player;

public class PlayerManager {
    private Player player;
    private CollisionDetection collision;

    public PlayerManager(Player player, CollisionDetection collision) {
        this.player = player;
        this.collision = collision;
    }

    public void draw(Graphics2D g2d) {
        player.draw(g2d);
    }

    public void process() {
        player.process();
        for (int d = 0; d < 4; d++) {
            if (player.getMovement(d)) {
                if (collision.canMove(player, d)) {
                    player.move(d);
                }
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            player.setMovement(0, true);
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            player.setMovement(1, true);
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            player.setMovement(2, true);
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            player.setMovement(3, true);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
            player.setMovement(0, false);
        } else if (key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) {
            player.setMovement(1, false);
        } else if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
            player.setMovement(2, false);
        } else if (key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) {
            player.setMovement(3, false);
        }
    }
}

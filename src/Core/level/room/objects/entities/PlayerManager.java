package core.level.room.objects.entities;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import core.level.room.contents.ContentsManager;
import core.level.room.contents.lighting.RadialLight;

public class PlayerManager {
    private Player player;
    private ContentsManager contentsManager;

    public PlayerManager(Player player, ContentsManager contentsManager) {
        this.player = player;
        this.contentsManager = contentsManager;
        contentsManager.lightingEngine().addLight(new RadialLight(player.getLocation(), 200, 3f));
    }

    public void draw(Graphics2D g2d) {
        player.draw(g2d);
    }

    public void process() {
        player.process();
        for (int d = 0; d < 4; d++) {
            if (player.getMovement(d)) {
                if (contentsManager.collision().canMove(player, d)) {
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
    
    public int getX() {
        return player.getScreenX();
    }

    public int getY() {
        return player.getScreenY();
    }
}

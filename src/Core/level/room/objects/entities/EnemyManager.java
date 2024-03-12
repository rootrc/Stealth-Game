package core.level.room.objects.entities;

import java.awt.Graphics2D;

import core.level.room.contents.AI;
import core.level.room.contents.ContentsManager;
import core.level.room.contents.lighting.FlashLight;

class EnemyManager {
    private Enemy enemy;
    private ContentsManager contentsManager;

    public EnemyManager(Enemy enemy, ContentsManager contentsManager) {
        this.enemy = enemy;
        this.contentsManager = contentsManager;
        contentsManager.lightingEngine().addLight(new FlashLight(enemy.getLocation(), 200, 1, 0.5, 2f));
    }

    public void draw(Graphics2D g2d) {
        enemy.draw(g2d);
    }

    public void process() {
        enemy.process();
        for (int d = 0; d < 4; d++) {
            if (enemy.getMovement(d)) {
                if (contentsManager.collision().canMove(enemy, d)) {
                    enemy.move(d);
                }
            }
        }
        //test
        new AI(contentsManager.collision(), contentsManager.graph()).follow(this.enemy, 0, 0);
    }
}

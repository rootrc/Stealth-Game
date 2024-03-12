package core.level.room.objects.entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

import core.level.room.contents.ContentsManager;

class EnemiesManager {
    private ArrayList<EnemyManager> enemies;
    private ContentsManager contentsManager;

    public EnemiesManager(ContentsManager contentsManager) {
        this.contentsManager = contentsManager;
        enemies = new ArrayList<>();
        
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(new EnemyManager(enemy, contentsManager));
    }

    public void draw(Graphics2D g2d) {
        for (EnemyManager enemy : enemies) {
            enemy.draw(g2d);
        }
    }

    public void process() {
        for (EnemyManager enemy : enemies) {
            enemy.process();
        }
    }
}

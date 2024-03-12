package core.level.room.objects.entities;

import java.awt.Graphics2D;

import core.level.room.contents.ContentsManager;

public class EntityManager {
    private ContentsManager contentsManager;
    private EnemiesManager enemies;

    public EntityManager(ContentsManager contentsManager) {
        this.contentsManager = contentsManager;
        enemies = new EnemiesManager(contentsManager);
    }

    public void draw(Graphics2D g2d) {
        enemies.draw(g2d);
    }

    public void process() {
        enemies.process();
    }

    public void addEnemy() {
        //TODO
    }
}

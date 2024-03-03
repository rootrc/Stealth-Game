package core.level.room.contents.managers;

import java.awt.Graphics2D;
import java.util.ArrayList;

import core.level.room.contents.CollisionDetection;
import core.level.room.contents.GraphManager;
import core.level.room.objects.entities.Enemy;

public class EnemiesManager {
    private ArrayList<EnemyManager> enemies;
    private CollisionDetection collision;
    private GraphManager graph;

    public EnemiesManager(CollisionDetection collision, GraphManager graph) {
        enemies = new ArrayList<>();
        this.collision = collision;
        this.graph = graph;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(new EnemyManager(enemy, collision, graph));
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

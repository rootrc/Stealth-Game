package core.level.room.contents;

import java.awt.Graphics2D;

import core.level.room.objects.entities.Enemy;

public class EnemyManager {
    private Enemy enemy;
    private CollisionDetection collision;
    private GraphManager graph;

    public EnemyManager(Enemy enemy, CollisionDetection collision, GraphManager graph) {
        this.enemy = enemy;
        this.collision = collision;
        this.graph = graph;
    }

    public void draw(Graphics2D g2d) {
        enemy.draw(g2d);
    }

    public void process() {
        enemy.process();
        for (int d = 0; d < 4; d++) {
            if (enemy.getMovement(d)) {
                if (collision.canMove(enemy, d)) {
                    enemy.move(d);
                }
            }
        }
        //test
        new AI(collision, graph).follow(this.enemy, 0, 0);
    }
}

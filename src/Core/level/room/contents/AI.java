package core.level.room.contents;

import java.util.ArrayList;
import java.util.Random;

import core.level.room.managers.GraphManager;
import core.level.room.objects.Entity;
import core.level.room.objects.TileToScreen;

public class AI {
    private CollisionDetection collision;
    private GraphManager graph;

    public AI(CollisionDetection collison, GraphManager graph) {
        this.collision = collison;
        this.graph = graph;
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    public void follow(Entity entity, int targetX, int targetY) {
        if (entity.getX() == targetX && entity.getY() == targetY) {
            return;
        }
        entity.setMovement(false);  
        ArrayList<Integer> targetDirects = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            if (!collision.withinBounds(entity, d)) {
                continue;
            }
            if (graph.travelDistance(entity.getX(), entity.getY(), entity.getX() + direct[d][0],
                    entity.getY() + direct[d][1]) != 1) {
                continue;
            }
            int dist = graph.travelDistance(entity.getX() + direct[d][0], entity.getY() + direct[d][1],
                    targetX, targetY);
            if (minDist > dist) {
                minDist = dist;
                targetDirects.clear();
                targetDirects.add(d);
            } else if (minDist == dist) {
                targetDirects.add(d);
            }
        }
        if (targetDirects.size() == 1) {
            entity.setMovement(targetDirects.get(0), true);
        } else {
            entity.setMovement(targetDirects.get(new Random().nextInt(targetDirects.size())), true);
        }
        if (entity.getMovement(1) == false && entity.getMovement(3) == false) {
            if (entity.getScreenX() < TileToScreen.toScreen(entity.getX()) + TileToScreen.tileRadius) {
                entity.setMovement(1, true);
            } else if (entity.getScreenX() > TileToScreen.toScreen(entity.getX()) + TileToScreen.tileRadius) {
                entity.setMovement(3, true);
            }
        }
        if (entity.getMovement(0) == false && entity.getMovement(2) == false) {
            if (entity.getScreenY() < TileToScreen.toScreen(entity.getY()) + TileToScreen.tileRadius) {
                entity.setMovement(2, true);
            } else if (entity.getScreenY() > TileToScreen.toScreen(entity.getY()) + TileToScreen.tileRadius) {
                entity.setMovement(0, true);
            }
        }
    }
}

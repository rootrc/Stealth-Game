package core.level;

import java.util.ArrayList;
import java.util.Random;

import core.level.objects.TileToScreen;
import core.level.objects.entities.Entity;
import core.level.objects.entities.Player;
import core.level.objects.tiles.Tile;

public class AI {
    private Player player;
    private CollisionDetection collision;
    private NetworkManager network;

    public AI(Player player, CollisionDetection collison, NetworkManager network) {
        this.player = player;
        this.collision = collison;
        this.network = network;
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    public void follow(Entity entity, int targetX, int targetY) {
        if (entity.getX() == targetX && entity.getY() == targetY) {
            return;
        }
        ArrayList<Integer> targetDirects = new ArrayList<>();
        int minDist = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            entity.setMovement(d, false);
            if (!collision.withinBounds(entity, d)) {
                continue;
            }
            if (network.travelDistance(entity.getX(), entity.getY(), entity.getX() + direct[d][0],
                    entity.getY() + direct[d][1]) != 1) {
                continue;
            }
            int dist = network.travelDistance(entity.getX() + direct[d][0], entity.getY() + direct[d][1],
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
            if (entity.getScreenX() < TileToScreen.xToScreenX(entity.getX()) + TileToScreen.tileSize / 4) {
                entity.setMovement(1, true);
            } else if (entity.getScreenX() > TileToScreen.xToScreenX(entity.getX()) + 3 * TileToScreen.tileSize / 4) {
                entity.setMovement(3, true);
            }
        }
        if (entity.getMovement(0) == false && entity.getMovement(2) == false) {
            if (entity.getScreenY() < TileToScreen.yToScreenY(entity.getY()) + TileToScreen.tileSize / 4) {
                entity.setMovement(2, true);
            } else if (entity.getScreenY() > TileToScreen.yToScreenY(entity.getY()) + 3 * TileToScreen.tileSize / 4) {
                entity.setMovement(0, true);
            }
        }
    }
}

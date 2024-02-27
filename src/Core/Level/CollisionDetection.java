package core.level;

import core.level.objects.TileToScreen;
import core.level.objects.edges.Edge;
import core.level.objects.entities.Entity;
import core.level.objects.entities.Player;
import core.level.objects.tiles.Tile;

public class CollisionDetection {
    private int N;
    private int M;
    private Tile tileGrid[][];

    public CollisionDetection(int N, int M, Tile tileGrid[][]) {
        this.N = N;
        this.M = M;
        this.tileGrid = tileGrid;
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    public boolean canMove(Entity entity) {
        int nextX = entity.getScreenX();
        int nextY = entity.getScreenY();
        for (int d = 0; d < 4; d++) {
            if (entity.getMovement(d)) {
                nextX += entity.getSpeed() * direct[d][0];
                nextY += entity.getSpeed() * direct[d][1];
            }
        }
        for (int i = -1; i <= 1; i++) {
            if (entity.getX() + i < 0 || entity.getX() + i >= N) {
                continue;
            }
            if (intersects(nextX, nextY, entity.getSize() / 2, tileGrid[entity.getX() + i][entity.getY()].getEdge(0))) {
                return false;
            }
            if (intersects(nextX, nextY, entity.getSize() / 2, tileGrid[entity.getX() + i][entity.getY()].getEdge(2))) {
                return false;
            }
        }
        for (int i = -1; i <= 1; i++) {
            if (entity.getY() + i < 0 || entity.getY() + i >= M) {
                continue;
            }
            if (intersects(nextX, nextY, entity.getSize() / 2, tileGrid[entity.getX()][entity.getY() + i].getEdge(1))) {
                return false;
            }
            if (intersects(nextX, nextY, entity.getSize() / 2, tileGrid[entity.getX()][entity.getY() + i].getEdge(3))) {
                return false;
            }
        }
        return true;
    }

    private boolean intersects(int x, int y, int radius, Edge edge) {
        if (!edge.getCollidable()) {
            return false;
        }
        int xMin = TileToScreen.xToScreenX(Math.min(edge.getX1(), edge.getX2()));
        int xMax = TileToScreen.xToScreenX(Math.max(edge.getX1(), edge.getX2()));
        int yMin = TileToScreen.yToScreenY(Math.min(edge.getY1(), edge.getY2()));
        int yMax = TileToScreen.yToScreenY(Math.max(edge.getY1(), edge.getY2()));
        if (x - radius < xMax && xMin < x + radius && y - radius < yMax && yMin < y + radius) {
            return true;
        }
        return false;
    }
}

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

    public boolean canMove(Player player, int d) {
        if (!player.getMovement(d)) {
            return false;
        }
        int nextX = player.getScreenX() + player.getSpeed() * direct[d][0];
        int nextY = player.getScreenY() + player.getSpeed() * direct[d][1];
        for (int i = -1; i <= 1; i++) {
            if (player.getX() + i < 0 || player.getX() + i >= N) {
                continue;
            }
            if (intersects(nextX, nextY, player.getSize() / 2, tileGrid[player.getX() + i][player.getY()].getEdge(0))) {
                return false;
            }
            if (intersects(nextX, nextY, player.getSize() / 2, tileGrid[player.getX() + i][player.getY()].getEdge(2))) {
                return false;
            }
        }
        for (int i = -1; i <= 1; i++) {
            if (player.getY() + i < 0 || player.getY() + i >= M) {
                continue;
            }
            if (intersects(nextX, nextY, player.getSize() / 2, tileGrid[player.getX()][player.getY() + i].getEdge(1))) {
                return false;
            }
            if (intersects(nextX, nextY, player.getSize() / 2, tileGrid[player.getX()][player.getY() + i].getEdge(3))) {
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
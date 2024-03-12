package core.level.room.contents;

import java.util.LinkedList;
import java.util.Queue;

import core.level.room.objects.tiles.Tile;

public class Graph {
    private int N;
    private int M;
    private TileGrid tileGrid;
    private int dist[][][][];

    public Graph(int N, int M, TileGrid tileGrid) {
        this.N = N;
        this.M = M;
        this.tileGrid = tileGrid;
        build();
    }

    public int travelDistance(int x1, int y1, int x2, int y2) {
        return dist[x1][y1][x2][y2];
    }

    public int travelDistance(Tile tile1, Tile tile2) {
        return dist[tile1.getX()][tile1.getY()][tile2.getX()][tile2.getY()];
    }

    private void build() {
        dist = new int[N][M][N][M];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                dist[x][y] = bfs(x, y);
            }
        }
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    private int[][] bfs(int startX, int startY) {
        Queue<Integer> qX = new LinkedList<>();
        Queue<Integer> qY = new LinkedList<>();
        qX.add(startX);
        qY.add(startY);
        int dist[][] = new int[N][M];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                dist[x][y] = -1;
            }
        }
        dist[startX][startY] = 0;
        while (!qX.isEmpty() && !qY.isEmpty()) {
            int x = qX.poll();
            int y = qY.poll();
            for (int d = 0; d < 4; d++) {
                int nextX = x + direct[d][0];
                int nextY = y + direct[d][1];
                if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                    continue;
                }
                if (!tileGrid.getTile(x, y).getEdge(d).getCollidable() && !tileGrid.getTile(nextX, nextY).getCollidable()) {
                    if (dist[nextX][nextY] == -1) {
                        dist[nextX][nextY] = dist[x][y] + 1;
                        qX.add(nextX);
                        qY.add(nextY);
                    }
                }
            }
        }
        return dist;
    }
}

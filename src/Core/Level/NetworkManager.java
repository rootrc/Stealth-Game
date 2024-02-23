package Core.Level;

import java.util.LinkedList;
import java.util.Queue;

import Core.Level.LevelElement.Tile.Tile;

public class NetworkManager {
    private int N;
    private int M;
    private int dist[][][][];

    public NetworkManager(int N, int M, Tile tileGrid[][]) {
        this.N = N;
        this.M = M;
        build(tileGrid);
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(dist[0][0][x][y]);
            }
            System.out.println();
        }
    }

    private void build(Tile tileGrid[][]) {
        dist = new int[N][M][N][M];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                dist[x][y] = bfs(x, y, tileGrid);
            }
        }
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    private int[][] bfs(int startX, int startY, Tile tileGrid[][]) {
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
                if (!tileGrid[x][y].getEdge(d).getCollidable()) {
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

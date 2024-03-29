package core.level.room;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import core.level.room.contents.TileGrid;
import core.level.room.objects.edges.*;
import core.level.room.objects.entities.Player;
import core.level.room.objects.tiles.*;

public class RoomFactory {
    private static int N;
    private static int M;
    private static BufferedReader br;

    public static Room createRoom(int id) {
        try {
            br = new BufferedReader(new FileReader("data/levels/lvl" + id));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            Tile tileGrid[][] = createTileGrid();
            Player player = createPlayer();
            return new Room(N, M, new TileGrid(N, M, tileGrid), player);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Tile[][] createTileGrid() {
        int tileData[][] = new int[N][M];
        try {
            StringTokenizer st;
            for (int y = 0; y < M; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    tileData[x][y] = Integer.parseInt(st.nextToken());
                }

            }
        } catch (IOException e) {
        }
        Edge edgeData[][][] = createEdges();
        Tile tileGrid[][] = new Tile[N][M];
        for (int y = 0; y < M; y++) {
            for (int x = 0; x < N; x++) {
                tileGrid[x][y] = createTile(tileData[x][y], x, y, edgeData[x][y]);
            }

        }
        return tileGrid;
    }

    private static Tile createTile(int id, int x, int y, Edge[] edges) {
        switch (id) {
            case 0:
                return new EmptyTile(x, y, edges);
            case 1:
                return new WallTile(x, y, edges);
            default:
                return null;
        }
    }

    private static Edge[][][] createEdges() {
        try {
            Edge edgeData[][][] = new Edge[N + 1][M + 1][4];
            br.readLine();
            StringTokenizer st;
            for (int y = 0; y < M; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x <= N; x++) {
                    Edge edge = createEdge(x, y, x, y + 1, Integer.parseInt(st.nextToken()));
                    if (x < N) {
                        edgeData[x][y][3] = edge;
                    }
                    if (x > 0) {
                        edgeData[x - 1][y][1] = edge;
                    }
                }
            }
            br.readLine();
            for (int y = 0; y <= M; y++) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    Edge edge = createEdge(x, y, x + 1, y, Integer.parseInt(st.nextToken()));
                    if (y < M) {
                        edgeData[x][y][0] = edge;
                    }
                    if (y > 0) {
                        edgeData[x][y - 1][2] = edge;
                    }
                }
            }
            return edgeData;
        } catch (IOException e) {

        }
        return null;
    }

    private static Edge createEdge(int x1, int y1, int x2, int y2, int id) {
        switch (id) {
            case 0:
                return new EmptyEdge(x1, y1, x2, y2);
            case 1:
                return new WallEdge(x1, y1, x2, y2);
            default:
                return null;
        }
    }

    private static Player createPlayer() {
        try {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            return new Player(x, y);
        } catch (IOException e) {

        }
        return null;
    }
}
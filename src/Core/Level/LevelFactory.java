package Core.Level;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import Core.Level.Tile.Empty;
import Core.Level.Tile.Tile;
import Core.Level.Tile.Wall;

public class LevelFactory {
    private static int N;
    private static int M;
    private static BufferedReader br;

    public static Level createLevel(int id) {
        try {
            br = new BufferedReader(new FileReader("data/levels/lvl" + id));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            Tile tileGrid[][] = createTileGrid();
            createEdges();

            return new Level(N, M, tileGrid);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Tile[][] createTileGrid() {
        Tile tileGrid[][] = new Tile[N][M]; 
        for (int y = 0; y < M; y++) {
            try {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    tileGrid[x][y] = createTile(x, y, Integer.parseInt(st.nextToken()));
                }
            } catch(IOException e) {

            }
        }
        return tileGrid;
    }

    private static Tile createTile(int x, int y, int id) {
        switch (id) {
            case 0:
                return new Empty(x, y);
            case 1:
                return new Wall(x, y);
            default:
                return null;
        }
    }

    private static void createEdges() {
        // br.readLine();
    }
}

package Core.Level;
import java.awt.Graphics2D;

import Core.Screen;
import Core.Level.LevelElement.Tile.Tile;

public class Level extends Screen {
    private int N;
    private int M;
    private Tile tileGrid[][];

    public Level(int N, int M, Tile tileGrid[][]) {
        this.N = N;
        this.M = M;
        this.tileGrid = tileGrid;
    }

    public void render(Graphics2D g2d) {
        drawTiles(g2d);
    }

    private void drawTiles(Graphics2D g2d) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                tileGrid[x][y].draw(g2d);
            }
        }
    }
}
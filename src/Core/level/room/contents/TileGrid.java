package core.level.room.contents;

import java.awt.Graphics2D;

import core.level.room.objects.tiles.Tile;

public class TileGrid {
    private int N;
    private int M;
    private Tile tileGrid[][];

    public TileGrid(int N, int M, Tile tileGrid[][]) {
        this.N = N;
        this.M = M;
        this.tileGrid = tileGrid;
    }

    public void draw(Graphics2D g2d) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                getTile(x, y).draw(g2d);
            }
        }
    }

    public Tile getTile(int x, int y) {
        return tileGrid[x][y];
    }
}

package core.level.room.contents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import core.level.room.objects.TileToScreen;
import core.level.room.objects.tiles.Tile;

public class TileGrid {
    private int N;
    private int M;
    private int width;
    private int height;
    private Tile tileGrid[][];
    private BufferedImage image;

    public TileGrid(int N, int M, Tile tileGrid[][]) {
        this.N = N;
        this.M = M;
        this.tileGrid = tileGrid;
        width = TileToScreen.tileSize * N + 1;
        height = TileToScreen.tileSize * M + 1;
        build();
    }

    public void build() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        // will cause lag
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setBackground(Color.darkGray);
        g2d.clearRect(0, 0, width, height);
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                getTile(x, y).draw(g2d);
            }
        }
        g2d.dispose();
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, TileToScreen.adjustX, TileToScreen.adjustY, null);
    }

    public Tile getTile(int x, int y) {
        return tileGrid[x][y];
    }
}
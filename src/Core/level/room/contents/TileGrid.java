package core.level.room.contents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.Panel;
import core.level.room.objects.Edge;
import core.level.room.objects.Tile;
import core.level.room.objects.TileToScreen;

public class TileGrid {
    private int N;
    private int M;
    private int width;
    private int height;
    private Tile tileGrid[][];
    private ArrayList<Edge> edges;
    private BufferedImage image;

    public TileGrid(int N, int M, Tile tileGrid[][]) {
        this.N = N;
        this.M = M;
        this.tileGrid = tileGrid;
        width = TileToScreen.toScreen(N) + 1;
        height = TileToScreen.toScreen(M) + 1;
        edges = new ArrayList<>();
        build();
    }

    public void build() {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        Panel.setHints(g2d);
        g2d.setBackground(Color.darkGray);
        g2d.clearRect(0, 0, width, height);
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                getTile(x, y).draw(g2d);
            }
        }
        g2d.dispose();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (x == 0 || y == 0) {
                    if (getTile(x, y).getEdge(0).getCollidable()) {
                        edges.add(getTile(x, y).getEdge(0));
                    }
                    if (getTile(x, y).getEdge(3).getCollidable()) {
                        edges.add(getTile(x, y).getEdge(3));
                    }
                }
                if (getTile(x, y).getEdge(1).getCollidable()) {
                    edges.add(getTile(x, y).getEdge(1));
                }
                if (getTile(x, y).getEdge(2).getCollidable()) {
                    edges.add(getTile(x, y).getEdge(2));
                }
            }
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(image, 0, 0, null);
    }

    public Tile getTile(int x, int y) {
        return tileGrid[x][y];
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
package core.level.room.contents;

import java.awt.Graphics2D;

import core.level.room.contents.lighting.LightingEngine;

public class ContentsManager {
    private TileGrid tileGrid;
    private CollisionDetection collision;
    private Graph graph;
    private LightingEngine lightingEngine;

    public ContentsManager(int N, int M, TileGrid tileGrid) {
        this.tileGrid = tileGrid;
        lightingEngine = new LightingEngine(N, M, tileGrid);
        collision = new CollisionDetection(N, M, tileGrid);
        graph = new Graph(N, M, tileGrid);
    }

    public void draw(Graphics2D g2d) {
        tileGrid.draw(g2d);
        lightingEngine.draw(g2d);
    }

    public void process() {
        lightingEngine.process();
    }

    public TileGrid tileGrid() {
        return tileGrid;
    }

    public CollisionDetection collision() {
        return collision;
    }

    public Graph graph() {
        return graph;
    }

    public LightingEngine lightingEngine() {
        return lightingEngine;
    }

}

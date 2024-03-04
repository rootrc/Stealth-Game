package core.level.room;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;

import core.Screen;
import core.level.room.contents.*;
import core.level.room.objects.entities.Player;
import core.level.room.objects.entities.TestEntity;

public class Room extends Screen {
    private int N;
    private int M;
    private TileGrid tileGrid;
    private LightManager lightManager;
    private CollisionDetection collision;
    private GraphManager graph;
    private PlayerManager player;
    private EnemiesManager enemies;

    public Room(int N, int M, TileGrid tileGrid, Player player) {
        this.N = N;
        this.M = M;
        this.tileGrid = tileGrid;
        lightManager = new LightManager(N, M);
        collision = new CollisionDetection(N, M, tileGrid);
        graph = new GraphManager(N, M, tileGrid);
        this.player = new PlayerManager(player, collision);
        enemies = new EnemiesManager(collision, graph);
        // test
        TestEntity entity = new TestEntity(7, 4);
        enemies.addEnemy(entity);
        lightManager.addLight(new Light(player.getLocation(), 200, 1.5f));
        lightManager.addLight(new Light(entity.getLocation(), 100, 1f));
    }

    public void render(Graphics2D g2d) {
        drawFPS(g2d);
        tileGrid.draw(g2d);
        player.draw(g2d);
        enemies.draw(g2d);
        lightManager.draw(g2d);
    }

    private long lastTime;
    private double fps;

    private void drawFPS(Graphics2D g2d) {
        fps = -Math.round(1000000000.0 / (lastTime - (lastTime = System.nanoTime())));
        g2d.drawString(String.valueOf(fps), 760, 10);
    }

    public void process() {
        player.process();
        enemies.process();
        lightManager.process();
    }

    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    public void mouseClicked(MouseEvent e) {

    }

}
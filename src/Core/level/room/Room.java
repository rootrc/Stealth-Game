package core.level.room;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

import core.Panel;
import core.Screen;
import core.level.room.contents.*;
import core.level.room.lighting.lights.FlashLight;
import core.level.room.lighting.lights.RadialLight;
import core.level.room.managers.EnemiesManager;
import core.level.room.managers.GraphManager;
import core.level.room.managers.LightManager;
import core.level.room.managers.PlayerManager;
import core.level.room.objects.TileToScreen;
import core.level.room.objects.entities.Player;
import core.level.room.objects.entities.TestEntity;

public class Room extends Screen {
    private int width;
    private int height;
    private TileGrid tileGrid;
    private LightManager lightManager;
    private CollisionDetection collision;
    private GraphManager graph;
    private PlayerManager player;
    private EnemiesManager enemies;

    public Room(int N, int M, TileGrid tileGrid, Player player) {
        width = TileToScreen.toScreen(N) + 1;
        height = TileToScreen.toScreen(M) + 1;
        this.tileGrid = tileGrid;
        lightManager = new LightManager(N, M, tileGrid);
        collision = new CollisionDetection(N, M, tileGrid);
        graph = new GraphManager(N, M, tileGrid);
        this.player = new PlayerManager(player, collision);
        enemies = new EnemiesManager(collision, graph);
        // test
        // TestEntity entity = new TestEntity(7, 4);
        // enemies.addEnemy(entity);
        lightManager.addLight(new RadialLight(player.getLocation(), 200, 3f));
        // lightManager.addLight(new RadialLight(entity.getLocation(), 50, 1.5f));
        // FlashLight flashLight = new FlashLight(player.getLocation(), 200, 1, 0.5, 2f);
        // lightManager.addLight(flashLight);
    }

    public void render(Graphics2D g2d) {
        drawFPS(g2d);
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        Panel.setHints(g);
        tileGrid.draw(g);
        player.draw(g);
        enemies.draw(g);
        lightManager.draw(g);
        g2d.drawImage(image, TileToScreen.adjustX, TileToScreen.adjustY, null);
        g2d.dispose();
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
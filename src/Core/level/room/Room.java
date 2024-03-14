package core.level.room;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

import core.Panel;
import core.level.room.contents.ContentsManager;
import core.level.room.contents.TileGrid;
import core.level.room.objects.TileToScreen;
import core.level.room.objects.entities.EntityManager;
import core.level.room.objects.entities.Player;
import core.level.room.objects.entities.PlayerManager;

public class Room {
    private int width;
    private int height;
    private ContentsManager contents;
    private EntityManager entities;
    private PlayerManager player;

    public Room(int N, int M, TileGrid tileGrid, Player player) {
        width = TileToScreen.toScreen(N) + 1;
        height = TileToScreen.toScreen(M) + 1;
        contents = new ContentsManager(N, M, tileGrid);
        entities = new EntityManager(contents);
        this.player = new PlayerManager(player, contents);
        
        // test
        // TestEnemy entity = new TestEnemy(7, 4);
        // enemies.addEnemy(entity);
        // contents.lightingEngine().addLight(new RadialLight(player.getLocation(), 200, 3f));
        // lightingEngine.addLight(new RadialLight(entity.getLocation(), 50, 1.5f));
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        Panel.setHints(g);

        contents.draw(g);
        entities.draw(g);
        player.draw(g);

        g2d.drawImage(image, 512 - player.getX(), 300 - player.getY(), null);
        g2d.setColor(Color.white);
        drawFPS(g2d);
    }

    private long lastTime;
    private double fps;

    private void drawFPS(Graphics2D g2d) {
        fps = -Math.round(1000000000.0 / (lastTime - (lastTime = System.nanoTime())));
        g2d.drawString(String.valueOf(fps), 1024-40, 10);
    }

    public void process() {
        contents.process();
        entities.process();
        player.process();
    }

    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

}
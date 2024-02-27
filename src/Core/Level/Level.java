package core.level;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Graphics2D;

import core.Screen;
import core.level.objects.TileToScreen;
import core.level.objects.entities.Entity;
import core.level.objects.entities.Player;
import core.level.objects.entities.TestEntity;
import core.level.objects.tiles.Tile;

public class Level extends Screen {
    private int N;
    private int M;
    private Player player;
    ArrayList<Entity> entities;
    private Tile tileGrid[][];
    private CollisionDetection collision;
    private NetworkManager network;
    private AI ai;

    public Level(int N, int M, Tile tileGrid[][], Player player) {
        this.N = N;
        this.M = M;
        this.tileGrid = tileGrid;
        this.player = player;
        entities = new ArrayList<>();
        entities.add(player);
        // test
        entities.add(new TestEntity(7, 4));
        build();
    }

    private void build() {
        collision = new CollisionDetection(N, M, tileGrid);
        network = new NetworkManager(N, M, tileGrid);
        ai = new AI(player, collision, network);
    }

    public void render(Graphics2D g2d) {
        drawTiles(g2d);
        player.draw(g2d);
        for (Entity entity : entities) {
            entity.draw(g2d);
        }
    }

    private void drawTiles(Graphics2D g2d) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                tileGrid[x][y].draw(g2d);
            }
        }
    }

    private final int direct[][] = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

    public void process() {
        for (Entity entity : entities) {
            if (entity.getClass() != Player.class) {
                ai.follow(entity, player.getX(), player.getY());
            }
            entity.process();
            for (int d = 0; d < 4; d++) {
                if (entity.getMovement(d)) {
                    if (collision.canMove(entity, d)) {
                        entity.move(d);
                    }
                }
            }
        }
    }

    public CollisionDetection getCollision() {
        return collision;
    }

    public NetworkManager getNetwork() {
        return network;
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
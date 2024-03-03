package core.level.room;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Graphics2D;

import core.Screen;
import core.level.room.contents.*;
import core.level.room.contents.managers.*;
import core.level.room.objects.entities.Player;
import core.level.room.objects.entities.TestEntity;
import core.level.room.objects.tiles.Tile;

public class Room extends Screen {
    private int N;
    private int M;
    private TileGrid tileGrid;
    private CollisionDetection collision;
    private GraphManager graph;
    private PlayerManager player;
    private EnemiesManager enemies;

    public Room(int N, int M, Tile tileGrid[][], Player player) {
        this.N = N;
        this.M = M;
        this.tileGrid = new TileGrid(N, M, tileGrid);
        collision = new CollisionDetection(N, M, this.tileGrid);
        graph = new GraphManager(N, M, this.tileGrid);
        this.player = new PlayerManager(player, collision);
        enemies = new EnemiesManager(collision, graph);
        // test
        enemies.addEnemy(new TestEntity(7, 4));
    }

    public void render(Graphics2D g2d) {
        tileGrid.draw(g2d);
        player.draw(g2d);
        enemies.draw(g2d);
    }

    public void process() {
        player.process();
        enemies.process();
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
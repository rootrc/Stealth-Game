package core.level;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Graphics2D;

import core.Screen;
import core.level.objects.entities.Player;
import core.level.objects.tiles.Tile;

public class Level extends Screen {
    private int N;
    private int M;
    private Player player;
    private Tile tileGrid[][];
    private NetworkManager network;

    public Level(int N, int M, Tile tileGrid[][], Player player) {
        this.N = N;
        this.M = M;
        this.tileGrid = tileGrid;
        this.player = player;
        build();
    }

    private void build() {
        network = new NetworkManager(N, M, tileGrid);
    }

    public void render(Graphics2D g2d) {
        player.draw(g2d);
        drawTiles(g2d);
    }

    public void process() {
        player.process();
    }

    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }

    public void mouseClicked(MouseEvent e) {

    }

    private void drawTiles(Graphics2D g2d) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                tileGrid[x][y].draw(g2d);
            }
        }
    }
}
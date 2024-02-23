package Core.Level.Edge;
import java.awt.Graphics2D;

import Core.Level.LevelElement;

public abstract class Edge extends LevelElement {
    int x1, y1;
    int x2, y2;
    public Edge(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public abstract void draw(Graphics2D g2d);
}

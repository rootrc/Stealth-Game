package core.level.room;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveX(int x) {
        this.x += x;
    }

    public void moveY(int y) {
        this.y += y;
    }
}

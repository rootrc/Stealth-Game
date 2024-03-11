package core.level.room;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void moveX(double x) {
        this.x += x;
    }

    public void moveY(double y) {
        this.y += y;
    }
}

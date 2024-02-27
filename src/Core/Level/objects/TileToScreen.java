package core.level.objects;

public class TileToScreen {
    public final static int tileSize = 32;
    public final static int adjustX = 64;
    public final static int adjustY = 32;

    public static int xToScreenX(int x) {
        return tileSize * x + adjustX;
    }

    public static int yToScreenY(int y) {
        return tileSize * y + adjustY;
    }

    public static int screenXToX(int screenX) {
        return (screenX - adjustX) / tileSize;
    }

    public static int screenYToY(int screenY) {
        return (screenY - adjustY) / tileSize;
    }
}

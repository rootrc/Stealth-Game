package core.level.room.objects;

public class TileToScreen {
    public final static int tileSize = 64;
    public final static int tileRadius = tileSize / 2;
    public final static int adjustX = 0;
    public final static int adjustY = 0;

    public static double toScreen(double a) {
        return tileSize * a;
    }

    public static int toScreen(int a) {
        return tileSize * a;
    }
}

package core.level.objects.entities;

public class TileToScreen {
    final static int tileSize = 32;
    public static int xToScreenX(int x) {
        return (tileSize * (x + 1));
    }
}

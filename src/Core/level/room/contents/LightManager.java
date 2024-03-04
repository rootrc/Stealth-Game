package core.level.room.contents;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.level.room.objects.TileToScreen;

public class LightManager {
	// public final static float fraction[] = { 0f, 0.4f, 0.5f, 0.6f, 0.65f, 0.7f, 0.75f, 0.8f, 0.85f, 0.9f, 0.95f, 1f };
	// public final static Color color[] = { new Color(0, 0, 0, 0.1f), new Color(0, 0, 0, 0.42f),
	// 		new Color(0, 0, 0, 0.52f), new Color(0, 0, 0, 0.61f), new Color(0, 0, 0, 0.69f), new Color(0, 0, 0, 0.76f),
	// 		new Color(0, 0, 0, 0.82f), new Color(0, 0, 0, 0.87f), new Color(0, 0, 0, 0.91f), new Color(0, 0, 0, 0.94f),
	// 		new Color(0, 0, 0, 0.96f), new Color(0, 0, 0, 0.98f) };
	private int height;
	private int length;
    private BufferedImage darknessFilter;
	private ArrayList <Light> lights;

    public LightManager(int N, int M) {
		height = TileToScreen.tileSize * N;
		length = TileToScreen.tileSize * M;
		lights = new ArrayList<>();
	}

	public void process() {
		darknessFilter = new BufferedImage(height, length, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D)darknessFilter.getGraphics();
		Area screenArea = new Area(new Rectangle2D.Double(0, 0, height, length));
		for (Light light: lights) {
			screenArea.subtract(light.getArea());
		}
		g2d.setColor(new Color(0, 0, 0, 0.9f));
		g2d.fill(screenArea);
		g2d.dispose();
	}

    public void draw(Graphics2D g2d) {
		g2d.drawImage(darknessFilter, 0, 0, null);
    }

	public void addLight(Light light) {
		lights.add(light);
	}

}

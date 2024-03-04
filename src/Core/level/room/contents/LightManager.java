package core.level.room.contents;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.level.room.objects.TileToScreen;

public class LightManager {
	private int width;
	private int height;
	private BufferedImage darknessFilter;
	private ArrayList<Light> lights;

	public LightManager(int N, int M) {
		width = TileToScreen.tileSize * N;
		height = TileToScreen.tileSize * M;
		lights = new ArrayList<>();
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(darknessFilter, 0, 0, null);
	}

	public void process() {
		darknessFilter = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = darknessFilter.createGraphics();
		g2d.setColor(new Color(0, 0, 0, 200));
		g2d.fillRect(0, 0, width, height);
		Composite oldComp = g2d.getComposite();
		g2d.setComposite(AlphaComposite.DstOut);
		for (Light light : lights) {
			light.draw(g2d);
		}
		g2d.setComposite(oldComp);
		g2d.dispose();
	}

	public void addLight(Light light) {
		lights.add(light);
	}

}
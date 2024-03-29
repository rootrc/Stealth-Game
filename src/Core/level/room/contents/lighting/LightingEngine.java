package core.level.room.contents.lighting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.Panel;
import core.level.room.contents.TileGrid;
import core.level.room.objects.TileToScreen;

public class LightingEngine {
	private int width;
	private int height;
	private BufferedImage darknessFilter;
	private ArrayList<Light> lights;
	private RayTracer rayTracer;

	public LightingEngine(int N, int M, TileGrid tileGrid) {
		width = TileToScreen.toScreen(N);
		height = TileToScreen.toScreen(M);
		lights = new ArrayList<>();
		rayTracer = new RayTracer(tileGrid);
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(darknessFilter, 0, 0, null);
	}

	public void process() {
		darknessFilter = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = darknessFilter.createGraphics();
		Panel.setHints(g2d);
		g2d.setColor(Color.black);
		g2d.fillRect(0, 0, width, height);
		Composite oldComp = g2d.getComposite();
		g2d.setComposite(AlphaComposite.DstOut);
		for (Light light : lights) {
			g2d.setClip(rayTracer.rayTrace(light));
			light.draw(g2d);
		}
		g2d.setComposite(oldComp);
		g2d.dispose();
	}

	public void addLight(Light light) {
		lights.add(light);
	}
}
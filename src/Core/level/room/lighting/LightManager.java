package core.level.room.lighting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.level.room.contents.TileGrid;
import core.level.room.objects.TileToScreen;

public class LightManager {
	private int width;
	private int height;
	private TileGrid tileGrid;
	private BufferedImage darknessFilter;
	private ArrayList<Light> lights;
	private RayTracer rayTracer;

	public LightManager(int N, int M, TileGrid tileGrid) {
		width = TileToScreen.tileSize * N;
		height = TileToScreen.tileSize * M;
		this.tileGrid = tileGrid;
		lights = new ArrayList<>();
		rayTracer = new RayTracer(tileGrid);
	}

	public void draw(Graphics2D g2d) {
		g2d.drawImage(darknessFilter, TileToScreen.adjustX, TileToScreen.adjustY, null);
	}

	public void process() {
		darknessFilter = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = darknessFilter.createGraphics();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		// will cause lag
		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setColor(new Color(0, 0, 0, 220));
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
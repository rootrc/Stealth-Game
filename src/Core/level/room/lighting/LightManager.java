package core.level.room.lighting;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import core.level.room.Point;
import core.level.room.contents.TileGrid;
import core.level.room.objects.TileToScreen;
import core.level.room.objects.edges.Edge;

public class LightManager {
	private int N;
	private int M;
	private int width;
	private int height;
	private TileGrid tileGrid;
	private BufferedImage darknessFilter;
	private ArrayList<Light> lights;

	public LightManager(int N, int M, TileGrid tileGrid) {
		this.N = N;
		this.M = M;
		width = TileToScreen.tileSize * N;
		height = TileToScreen.tileSize * M;
		this.tileGrid = tileGrid;
		lights = new ArrayList<>();
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
			f(light, g2d);
			Collections.sort(rayTracingPoints, new Comparator<Point>() {
				@Override
				public int compare(Point p1, Point p2) {
					if (Math.atan2(p1.getX() - light.getX(), p1.getY() - light.getY()) < Math
							.atan2(p2.getX() - light.getX(), p2.getY() - light.getY())) {
						return -1;
					} else if (Math.atan2(p1.getX() - light.getX(), p1.getY() - light.getY()) > Math
							.atan2(p2.getX() - light.getX(), p2.getY() - light.getY())) {
						return 1;
					} else if (Math.sqrt((p1.getX() - light.getX()) * (p1.getX() - light.getX())
							+ (p1.getY() - light.getY()) * (p1.getY() - light.getY())) > Math
									.sqrt((p2.getX() - light.getX()) * (p2.getX() - light.getX())
											+ (p2.getY() - light.getY()) * (p2.getY() - light.getY()))) {
						return -1;
					} else if (Math.sqrt((p1.getX() - light.getX()) * (p1.getX() - light.getX())
							+ (p1.getY() - light.getY()) * (p1.getY() - light.getY())) < Math
									.sqrt((p2.getX() - light.getX()) * (p2.getX() - light.getX())
											+ (p2.getY() - light.getY()) * (p2.getY() - light.getY()))) {
						return 1;
					}
					return 0;
				}
			});
			ArrayList<Point> filtered = new ArrayList<>();
			for (int i = 1; i < rayTracingPoints.size() + 1; i++) {
				Point p1 = rayTracingPoints.get((i - 1) % rayTracingPoints.size());
				Point p2 = rayTracingPoints.get(i % rayTracingPoints.size());
				if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
					continue;
				}
				filtered.add(p2);
			}
			ArrayList<Point> filtered2 = new ArrayList<>();
			for (int i = 1; i <= filtered.size(); i++) {
				Point p1 = filtered.get((i - 1) % filtered.size());
				Point p2 = filtered.get(i % filtered.size());
				Point p3 = filtered.get((i + 1) % filtered.size());
				// if (p2.getX() % 64 != 0 || p2.getY() % 64 != 0) {
					if (Math.abs(p1.getX() - p2.getX()) <= 1 && Math.abs(p2.getX() - p3.getX()) <= 1) {
						continue;
					}
					if (Math.abs(p1.getY() - p2.getY()) <= 1 && Math.abs(p2.getY() - p3.getY()) <= 1) {
						continue;
					}
				// }

				filtered2.add(p2);
			}
			Polygon polygon = new Polygon();
			for (Point point : filtered2) {
				// g2d.drawLine(light.getX(), light.getY(), point.getX(), point.getY());
				polygon.addPoint(point.getX(), point.getY());
				// System.out.println(point.getX() + " " + point.getY());
			}
			System.out.println();
			g2d.setClip(polygon);
			light.draw(g2d);
		}
		g2d.setComposite(oldComp);
		g2d.dispose();
	}

	public void addLight(Light light) {
		lights.add(light);
	}

	private void f(Light light, Graphics2D g2d) {
		rayTracingPoints = new ArrayList<>();
		for (Edge edge : tileGrid.getEdges()) {
			double a = 2;
			rayTrace(g2d, light, light.getX(), 64 * edge.getX1() - light.getX(), light.getY(),
					64 * edge.getY1() - light.getY());
			rayTrace(g2d, light, light.getX(), 64 * edge.getX1() - light.getX() + a, light.getY(),
					64 * edge.getY1() - light.getY());
			rayTrace(g2d, light, light.getX(), 64 * edge.getX1() - light.getX() - a, light.getY(),
					64 * edge.getY1() - light.getY());
			rayTrace(g2d, light, light.getX(), 64 * edge.getX1() - light.getX(), light.getY(),
					64 * edge.getY1() - light.getY() + a);
			rayTrace(g2d, light, light.getX(), 64 * edge.getX1() - light.getX(), light.getY(),
					64 * edge.getY1() - light.getY() - a);
			rayTrace(g2d, light, light.getX(), 64 * edge.getX2() - light.getX(), light.getY(),
					64 * edge.getY2() - light.getY());
			rayTrace(g2d, light, light.getX(), 64 * edge.getX2() - light.getX() + a, light.getY(),
					64 * edge.getY2() - light.getY());
			rayTrace(g2d, light, light.getX(), 64 * edge.getX2() - light.getX() - a, light.getY(),
					64 * edge.getY2() - light.getY());
			rayTrace(g2d, light, light.getX(), 64 * edge.getX2() - light.getX(), light.getY(),
					64 * edge.getY2() - light.getY() + a);
			rayTrace(g2d, light, light.getX(), 64 * edge.getX2() - light.getX(), light.getY(),
					64 * edge.getY2() - light.getY() - a);
		}
	}

	ArrayList<Point> rayTracingPoints;

	private void rayTrace(Graphics2D g2d, Light light, double r_px, double r_dx, double r_py, double r_dy) {
		if (r_dy == 0) {

		}
		double min = Double.MAX_VALUE;
		for (Edge edge : tileGrid.getEdges()) {
			int s_px = edge.getX1() * 64;
			int s_py = edge.getY1() * 64;
			int s_dx;
			int s_dy;
			if (edge.getX1() == edge.getX2()) {
				s_dx = 0;
				s_dy = 64;
			} else {
				s_dx = 64;
				s_dy = 0;
			}
			double T2 = (r_dx * (s_py - r_py) + r_dy * (r_px - s_px)) / (s_dx * r_dy - s_dy * r_dx);
			double T1 = (s_px + s_dx * T2 - r_px) / r_dx;
			if (T1 + 0.000000001 > 0 && T2 + 0.000000001 > 0 && T2 - 0.000000001 < 1) {
				min = Math.min(min, T1);
			}
		}
		if (min != Double.MAX_VALUE) {
			int x = (int) (light.getX() + min * r_dx);
			int y = (int) (light.getY() + min * r_dy);
			if (x % 64 == 1) {
				x--;
			} else if (x % 64 == 63) {
				x++;
			}
			if (y % 64 == 1) {
				y--;
			} else if (y % 64 == 63) {
				y++;
			}
			rayTracingPoints.add(new Point(x, y));
		}
	}

	private int f(int x, int y) {
		int cnt = 0;
		if (x < N && y < M) {
			if (tileGrid.getTile(x, y).getEdge(0).getCollidable()) {
				cnt++;
			}
			if (tileGrid.getTile(x, y).getEdge(3).getCollidable()) {
				cnt++;
			}
		}
		if (x - 1 >= 0 && y - 1 >= 0) {
			if (tileGrid.getTile(x - 1, y - 1).getEdge(1).getCollidable()) {
				cnt++;
			}
			if (tileGrid.getTile(x - 1, y - 1).getEdge(2).getCollidable()) {
				cnt++;
			}
		}
		return cnt;
	}
}
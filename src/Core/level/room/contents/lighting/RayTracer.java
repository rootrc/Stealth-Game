package core.level.room.contents.lighting;

import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import core.level.room.Point;
import core.level.room.contents.TileGrid;
import core.level.room.objects.TileToScreen;
import core.level.room.objects.edges.Edge;

class RayTracer {
    private TileGrid tileGrid;
    private Light light;
    private ArrayList<Point> rayTracingPoints;

    public RayTracer(TileGrid tileGrid) {
        this.tileGrid = tileGrid;
    }

    public Polygon rayTrace(Light light) {
        this.light = light;
        rayTracingPoints = new ArrayList<>();
        generatePoints();
        sortPoints();
        filterPoints();
        Polygon polygon = new Polygon();
        for (Point point : rayTracingPoints) {
            polygon.addPoint((int) point.getX(), (int) point.getY());
        }
        return polygon;
    }

    private final double[][] adjust = { { 0, 0 }, { 1.5, 0 }, { -1.5, 0 }, { 0, 1.5 }, { 0, -1.5 } };

    private void generatePoints() {
        for (Edge edge : tileGrid.getEdges()) {
            for (double[] p : adjust) {
                rayTrace(light.getX(), TileToScreen.toScreen(edge.getX1()) - light.getX() + p[0], light.getY(),
                        TileToScreen.toScreen(edge.getY1()) - light.getY() + p[1]);
                rayTrace(light.getX(), TileToScreen.toScreen(edge.getX2()) - light.getX() + p[0], light.getY(),
                        TileToScreen.toScreen(edge.getY2()) - light.getY() + p[1]);
            }
        }
    }

    private void rayTrace(double rayX, double rayXd, double rayY, double rayYd) {
        double min = Double.MAX_VALUE;
        for (Edge edge : tileGrid.getEdges()) {
            double edgeX = edge.getX1() * TileToScreen.tileSize;
            double edgeY = edge.getY1() * TileToScreen.tileSize;
            double edgeXd;
            double edgeYd;
            if (edge.getX1() == edge.getX2()) {
                edgeXd = 0;
                edgeYd = TileToScreen.tileSize;
            } else {
                edgeXd = TileToScreen.tileSize;
                edgeYd = 0;
            }
            double T2 = (rayXd * (edgeY - rayY) + rayYd * (rayX - edgeX)) / (edgeXd * rayYd - edgeYd * rayXd);
            double T1 = (edgeX + edgeXd * T2 - rayX) / rayXd;
            if (T1 + 0.000000001 > 0 && T2 + 0.000000001 > 0 && T2 - 0.000000001 < 1) {
                min = Math.min(min, T1);
            }
        }
        if (min != Double.MAX_VALUE) {
            double x = light.getX() + min * rayXd;
            double y = light.getY() + min * rayYd;
            if (x % TileToScreen.tileSize <= 1) {
                x -= x % TileToScreen.tileSize;
            } else if (x % TileToScreen.tileSize >= TileToScreen.tileSize - 1) {
                x += TileToScreen.tileSize - x % TileToScreen.tileSize;
            }
            if (y % TileToScreen.tileSize <= 1) {
                y -= y % TileToScreen.tileSize;
            } else if (y % TileToScreen.tileSize >= TileToScreen.tileSize - 1) {
                y += TileToScreen.tileSize - y % TileToScreen.tileSize;
            }
            rayTracingPoints.add(new Point(x, y));
        }
    }

    private void sortPoints() {
        Collections.sort(rayTracingPoints, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                if (Math.atan2(p1.getX() - light.getX(), p1.getY() - light.getY()) < Math
                        .atan2(p2.getX() - light.getX(), p2.getY() - light.getY())) {
                    return -1;
                } else if (Math.atan2(p1.getX() - light.getX(), p1.getY() - light.getY()) > Math
                        .atan2(p2.getX() - light.getX(), p2.getY() - light.getY())) {
                    return 1;
                }
                return 0;
            }
        });
    }

    private void filterPoints() {
        ArrayList<Point> filtered = new ArrayList<>();
        for (int i = 0; i < rayTracingPoints.size(); i++) {
            Point p1 = rayTracingPoints.get((i + 1) % rayTracingPoints.size());
            Point p2 = rayTracingPoints.get(i);
            if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
                continue;
            }
            filtered.add(p2);
        }
        ArrayList<Point> filtered2 = new ArrayList<>();
        for (int i = 0; i < filtered.size(); i++) {
            Point p1 = filtered.get(i);
            Point p2 = filtered.get((i + 1) % filtered.size());
            Point p3 = filtered.get((i + 2) % filtered.size());
            if (Math.abs(p1.getX() - p2.getX()) <= 1 && Math.abs(p2.getX() - p3.getX()) <= 1) {
                continue;
            }
            if (Math.abs(p1.getY() - p2.getY()) <= 1 && Math.abs(p2.getY() - p3.getY()) <= 1) {
                continue;
            }
            filtered2.add(p2);
        }
        rayTracingPoints = filtered2;
    }
}
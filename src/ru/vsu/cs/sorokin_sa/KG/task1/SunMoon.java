package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.awt.geom.Point2D;

public class SunMoon implements SceneObject {
    private final double cx = Config.WIDTH * 0.5;
    private final double cy = Config.HEIGHT * 0.82;
    private final double radius = Config.WIDTH * 0.55;

    private double lastElapsed = 0;
    private double lastT = 0;
    private boolean lastIsDay = true;

    @Override
    public void update(double elapsed, double t, boolean isDay) {
        this.lastElapsed = elapsed;
        this.lastT = t;
        this.lastIsDay = isDay;
    }

    @Override
    public void draw(Graphics2D g) {

    }

    private Color lerpColor(Color a, Color b, float t) {
        t = Math.min(1f, Math.max(0f, t));
        int r = (int) (a.getRed() * (1 - t) + b.getRed() * t);
        int g = (int) (a.getGreen() * (1 - t) + b.getGreen() * t);
        int bl = (int) (a.getBlue() * (1 - t) + b.getBlue() * t);
        return new Color(r, g, bl);
    }

    public void drawSky(Graphics2D g, double t, boolean isDay) {
        float p;
        if (t<=0.5){
            p = (float) (Math.abs(0.25 - t) / 0.25);
        } else {
            p = (float) (Math.abs(0.25 - (t - 0.5)) / 0.25);
        }

        Color top = isDay ? lerpColor(new Color(10, 90, 180), new Color(255, 180, 100), p)
                : lerpColor(new Color(5, 10, 40), new Color(40, 20, 60), p);
        Color bottom = isDay ? lerpColor(new Color(120, 200, 255), new Color(255, 200, 150), p)
                : lerpColor(new Color(10, 10, 30), new Color(30, 25, 50), p);

        GradientPaint gp = new GradientPaint(0, 0, top, 0, Config.HEIGHT, bottom);
        g.setPaint(gp);
        g.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);
    }

    private Point2D arcPos(double normalizedT) {
        double start = -Math.PI * 0.9 / 2;
        double sweep = Math.PI * 0.9;
        double angle = start + normalizedT * sweep;
        double x = cx + radius * Math.cos(angle);
        double y = cy + radius * Math.sin(angle);
        return new Point2D.Double(x, y);
    }
}

package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class SunMoon implements SceneObject {
    private final double cx = Config.WEIGHT * 0.5;
    private final double cy = Config.HEIGHT * 0.82;
    private final double radius = Config.WEIGHT * 0.55;

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



    }
}

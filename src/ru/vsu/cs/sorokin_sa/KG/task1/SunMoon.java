package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class SunMoon implements SceneObject {
    private double lastElapsed = 0;
    private double lastT = 0;
    private boolean lastIsDay = true;

    private int nightCount = 0;
    private MoonPhase currentPhase = MoonPhase.FULL;
    private boolean prevIsDay = true;

    @Override
    public void update(double elapsed, double t, boolean isDay) {
        this.lastElapsed = elapsed;
        this.lastT = t;

        if (prevIsDay && !isDay) {
            nightCount++;
            currentPhase = MoonPhase.values()[nightCount % MoonPhase.values().length];
        }
        prevIsDay = isDay;
        this.lastIsDay = isDay;
    }

    @Override
    public void draw(Graphics2D g) {
        Point2D pos = arcPos(lastT);
        if (lastIsDay) {
            drawSun(g, (int) pos.getX(), (int) pos.getY());
        } else {
            drawMoon(g, (int) pos.getX(), (int) pos.getY());
        }
    }

    private Color lerpColor(Color a, Color b, float t) {
        t = Math.min(1f, Math.max(0f, t));
        int r = (int) (a.getRed() * (1 - t) + b.getRed() * t);
        int g = (int) (a.getGreen() * (1 - t) + b.getGreen() * t);
        int bl = (int) (a.getBlue() * (1 - t) + b.getBlue() * t);
        return new Color(r, g, bl);
    }

    public void drawSky(Graphics2D g, double t, boolean isDay) {
        Color topColor, bottomColor;

        if (isDay) {
            // ДЕНЬ: плавный переход от ночи через рассвет/закат к дню и обратно
            if (t < 0.25) {
                // Ночь -> Рассвет (0.0 - 0.25)
                float progress = (float) (t / 0.25);
                topColor = lerpColor(Config.SKY_NIGHT, Config.SKY_SUNRISE_SUNSET, progress);
                bottomColor = lerpColor(Config.SKY_NIGHT, Config.SKY_DAWN_DUSK, progress);
            } else if (t < 0.375) {
                float progress = (float) ((t - 0.25) / 0.125);
                topColor = lerpColor(Config.SKY_SUNRISE_SUNSET, Config.SKY_DAY, progress);
                bottomColor = lerpColor(Config.SKY_DAWN_DUSK, Config.SKY_DAY, progress);
            } else if (t < 0.625) {
                topColor = Config.SKY_DAY;
                bottomColor = Config.SKY_DAY;
            } else if (t < 0.75) {
                float progress = (float) ((t - 0.625) / 0.125);
                topColor = lerpColor(Config.SKY_DAY, Config.SKY_SUNRISE_SUNSET, progress);
                bottomColor = lerpColor(Config.SKY_DAY, Config.SKY_DAWN_DUSK, progress);
            } else {
                float progress = (float) ((t - 0.75) / 0.25);
                topColor = lerpColor(Config.SKY_SUNRISE_SUNSET, Config.SKY_NIGHT, progress);
                bottomColor = lerpColor(Config.SKY_DAWN_DUSK, Config.SKY_NIGHT, progress);
            }
        } else {
            if (t < 0.5) {
                float progress = (float) (t / 0.5);
                Color midnight = new Color(5, 10, 35);
                topColor = lerpColor(Config.SKY_NIGHT, midnight, progress);
                bottomColor = lerpColor(new Color(15, 20, 55), midnight, progress);
            } else {
                float progress = (float) ((t - 0.5) / 0.5);
                Color midnight = new Color(5, 10, 35);
                topColor = lerpColor(midnight, Config.SKY_NIGHT, progress);
                bottomColor = lerpColor(midnight, new Color(15, 20, 55), progress);
            }
        }

        GradientPaint gp = new GradientPaint(0, 0, topColor, 0, Config.HEIGHT, bottomColor);
        g.setPaint(gp);
        g.fillRect(0, 0, Config.WIDTH, Config.HEIGHT);
    }

    private Point2D arcPos(double normalizedT) {
        // Движение по дуге от левого края к правому
        double startAngle = Math.toRadians(Config.ARC_START_ANGLE);
        double endAngle = Math.toRadians(Config.ARC_END_ANGLE);
        double angle = startAngle + normalizedT * (endAngle - startAngle);

        double x = Config.ARC_CENTER_X + Config.ARC_RADIUS * Math.cos(angle);
        double y = Config.ARC_CENTER_Y + Config.ARC_RADIUS * Math.sin(angle);

        return new Point2D.Double(x, y);
    }

    public void drawSun(Graphics2D g, int x, int y) {
        int r = Config.SUN_RADIUS;

        float glowR = r * Config.SUN_GLOW_RADIUS_MULTIPLIER;
        RadialGradientPaint rg = new RadialGradientPaint(
                new Point2D.Float(x, y),
                glowR,
                new float[]{0f, 1f},
                new Color[]{Config.SUN_GLOW_CENTER, Config.SUN_GLOW_EDGE}
        );
        Paint old = g.getPaint();
        g.setPaint(rg);
        g.fillOval((int) (x - glowR), (int) (y - glowR), (int) (2 * glowR), (int) (2 * glowR));
        g.setPaint(old);

        g.setColor(Config.SUN_COLOR);
        g.fillOval(x - r, y - r, 2 * r, 2 * r);

        g.setColor(Config.SUN_OUTLINE);
        g.drawOval(x - r, y - r, 2 * r, 2 * r);
    }

    public void drawMoon(Graphics2D g, int x, int y) {
        int r = Config.MOON_RADIUS;

        float glowR = r * Config.MOON_GLOW_RADIUS_MULTIPLIER;
        RadialGradientPaint rg = new RadialGradientPaint(
                new Point2D.Float(x, y),
                glowR,
                new float[]{0f, 1f},
                new Color[]{Config.MOON_GLOW_CENTER, Config.MOON_GLOW_EDGE}
        );
        Paint old = g.getPaint();
        g.setPaint(rg);
        g.fillOval((int) (x - glowR), (int) (y - glowR), (int) (2 * glowR), (int) (2 * glowR));
        g.setPaint(old);

        Ellipse2D.Double base = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
        Area moonArea = new Area(base);

        switch (currentPhase) {
            case NEW:
                moonArea.subtract(new Area(base));
                break;
            case WAXING_CRESCENT:
                moonArea.subtract(new Area(new Ellipse2D.Double(x - r - 0.65*r, y - r, 2*r, 2*r)));
                break;
            case FIRST_QUARTER:
                moonArea.subtract(new Area(new Ellipse2D.Double(x - 2*r, y - r, 2*r, 2*r)));
                break;
            case WAXING_GIBBOUS:
                moonArea.subtract(new Area(new Ellipse2D.Double(x - 2.6*r, y - r, 2*r, 2*r)));
                break;
            case FULL:
                break;
            case WANING_GIBBOUS:
                moonArea.subtract(new Area(new Ellipse2D.Double(x + 1.6*r, y - r, 2*r, 2*r)));
                break;
            case LAST_QUARTER:
                moonArea.subtract(new Area(new Ellipse2D.Double(x + r, y - r, 2*r, 2*r)));
                break;
            case WANING_CRESCENT:
                moonArea.subtract(new Area(new Ellipse2D.Double(x + 0.65*r, y - r, 2*r, 2*r)));
                break;
        }
        g.setColor(Config.MOON_COLOR);
        g.fill(moonArea);
        g.setColor(Config.MOON_OUTLINE);
        g.draw(moonArea);
    }
}

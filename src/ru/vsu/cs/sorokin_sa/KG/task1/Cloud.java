package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.util.Random;

import static java.awt.AlphaComposite.SRC_OVER;

public class Cloud implements Animatable, Drawable {

    private static final Random R = new Random();
    private final int size = 60 + R.nextInt(60);
    private final int y   = R.nextInt(Config.HEIGHT / 3);
    private final int speed = 1 + R.nextInt(3);

    private int x = -size;
    private float alpha = 1f;

    /* границы переходов (в доле дня) */
    private static final double START_FADE = 0.60;   // начало заката
    private static final double END_FADE   = 0.85;   // конец заката → 0 (увеличили для более плавного исчезновения)

    @Override
    public void update(double elapsed, double dayPhase, boolean isDay) {
        x += speed;
        if (x > Config.WIDTH + size) x = -size;

        /* рассчитываем alpha вокруг заката */
        if (!isDay) {
            alpha = 0f;  // ночь - полностью невидимы
        } else if (dayPhase >= START_FADE) {
            double t = Math.min(1.0, Math.max(0.0, (dayPhase - START_FADE) / (END_FADE - START_FADE)));
            alpha = (float) (1.0 - t);   // 1 → 0 за весь закат
        } else {
            alpha = 1f;                  // день – полностью видны
        }
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        if (alpha < 0.01f) return;
        Composite old = g.getComposite();
        g.setComposite(AlphaComposite.getInstance(SRC_OVER, alpha));
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size / 2);
        g.fillOval(x + size / 2, y - 10, size, size / 2);
        g.setComposite(old);
    }
}
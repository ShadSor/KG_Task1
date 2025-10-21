package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class Pond implements Drawable {
    private final int x = Config.WIDTH - 100;
    private final int y = Config.HEIGHT - 70;
    private final int width = 80;
    private final int height = 50;

    public Pond() {
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        Color waterColor = new Color(30, 140, 220);
        g.setColor(waterColor);
        g.fillOval(x, y, width, height);

        g.setColor(new Color(10, 60, 120, 80));
        g.fillOval(x + 5, y + 5, width - 10, height - 10);

        g.setColor(Color.WHITE);
        g.fillOval(x + 20, y + 10, 8, 8); // Можно изменить позицию и размер
    }
}
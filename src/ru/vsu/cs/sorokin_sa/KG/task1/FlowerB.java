package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class FlowerB implements Drawable {
    private final int x, y;

    public FlowerB(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        // Стебель
        g.setColor(new Color(100, 160, 100));
        g.fillRect(x - 1, y, 2, 14);

        // Внешние лепестки
        g.setColor(new Color(255, 150, 150));
        g.fillOval(x - 8, y - 12, 16, 16);

        // Внутренний круг
        g.setColor(Color.ORANGE);
        g.fillOval(x - 5, y - 9, 10, 10);

        // Детали в центре
        g.setColor(Color.YELLOW);
        g.fillOval(x - 2, y - 6, 4, 4);
    }
}


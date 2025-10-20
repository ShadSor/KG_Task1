package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class FlowerC implements Drawable {
    private final int x, y;

    public FlowerC(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        // Стебель
        g.setColor(new Color(120, 200, 120));
        g.fillRect(x - 1, y, 2, 10);


        // Лепестки
        g.setColor(new Color(180, 220, 255));
        // Верхний лепесток
        g.fillOval(x - 4, y - 16, 8, 8);
        // Верхний правый
        g.fillOval(x + 2, y - 12, 8, 8);
        // Нижний правый
        g.fillOval(x, y - 5, 8, 8);
        // Нижний левый
        g.fillOval(x - 8, y - 5, 8, 8);
        // Верхний левый
        g.fillOval(x - 10, y - 12, 8, 8);

        // Центр цветка
        g.setColor(new Color(255, 255, 200));
        g.fillOval(x - 5, y - 11, 10, 10);

        // Детали центра
        g.setColor(new Color(200, 200, 100));
        g.fillOval(x - 2, y - 8, 4, 4);
    }
}


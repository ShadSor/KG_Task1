package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class FlowerA implements Drawable {
    private final int x, y;

    public FlowerA(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        // Стебель
        g.setColor(new Color(100, 180, 100));
        g.fillRect(x - 1, y, 2, 12);


        // Лепестки
        g.setColor(Color.PINK);
        // Верхний лепесток
        g.fillOval(x - 4, y - 14, 8, 8);
        // Правый лепесток
        g.fillOval(x + 2, y - 9, 8, 8);
        // Нижний лепесток
        g.fillOval(x - 4, y - 4, 8, 8);
        // Левый лепесток
        g.fillOval(x - 10, y - 9, 8, 8);

        // Центр цветка
        g.setColor(Color.YELLOW);
        g.fillOval(x - 4, y - 9, 8, 8);
    }
}

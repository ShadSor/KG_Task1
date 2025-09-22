package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class LogWood implements SceneObject {
    private int x = Config.WIDTH / 2 + 100;
    private int y = Config.HEIGHT - 60;

    @Override
    public void update(double elapsed, double dt, boolean isDay) {}

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        g.setColor(new Color(90,50,20));
        g.fillRect(x, y, 110, 26);
        g.setColor(new Color(120,80,40));
        g.fillOval(x + 105, y, 10, 26);
        g.fillOval(x - 5, y, 10, 26);
    }
}


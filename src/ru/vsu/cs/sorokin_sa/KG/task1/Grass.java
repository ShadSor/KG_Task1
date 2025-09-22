package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class Grass implements SceneObject {
    @Override
    public void update(double elapsed, double dt, boolean isDay) {}

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        g.setColor(Config.GRASS_COLOR);
        g.fillRect(0, Config.HEIGHT - 50, Config.WIDTH, 50);
    }
}


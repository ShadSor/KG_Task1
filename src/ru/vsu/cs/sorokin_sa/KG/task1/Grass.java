package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class Grass implements Drawable {

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        g.setColor(Config.GRASS_COLOR);
        g.fillRect(0, Config.HEIGHT - 100, Config.WIDTH, Config.GRASS_HEIGHT);
    }
}


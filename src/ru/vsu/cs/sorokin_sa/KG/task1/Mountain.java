package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.util.Random;

public class Mountain implements SceneObject {
    private int x, height;
    private static final Random rand = new Random();

    public Mountain() {
        x = -150 + rand.nextInt(Config.WIDTH + 300);
        height = 200 + rand.nextInt(150);
    }

    @Override
    public void update(double elapsed, double dt, boolean isDay) {}

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        g.setColor(new Color(100, 100, 100));
        int[] xs = {x, x + 150, x - 150};
        int[] ys = {Config.HEIGHT - height, Config.HEIGHT - 100, Config.HEIGHT - 100};
        g.fillPolygon(xs, ys, 3);
    }


}


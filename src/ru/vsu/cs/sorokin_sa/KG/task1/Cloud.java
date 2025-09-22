package ru.vsu.cs.sorokin_sa.KG.task1;

import ru.vsu.cs.sorokin_sa.KG.task1.Config;
import ru.vsu.cs.sorokin_sa.KG.task1.SceneObject;

import java.awt.*;
import java.util.Random;

public class Cloud implements SceneObject {
    private int x, y, speed, size;
    private static final Random rand = new Random();

    public Cloud() {
        x = rand.nextInt(Config.WIDTH);
        y = rand.nextInt(Config.HEIGHT / 3);
        speed = 1 + rand.nextInt(3);
        size = 40 + rand.nextInt(40);
    }

    @Override
    public void update(double elapsed, double dt, boolean isDay) {
        if (isDay) {
            x += speed;
            if (x > Config.WIDTH) x = -size;
        }
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        if (!isDay) return;
        g.setColor(Color.WHITE);
        g.fillOval(x, y, size, size / 2);
        g.fillOval(x + size / 2, y - 10, size, size / 2);
    }
}

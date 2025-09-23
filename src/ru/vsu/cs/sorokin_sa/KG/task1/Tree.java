package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.util.Random;

public class Tree implements SceneObject {
    private int x, y;
    private static final Random rand = new Random();

    public Tree() {
        x = rand.nextInt(Config.WIDTH / 2);
        y = Config.HEIGHT - 30;
    }

    @Override
    public void update(double elapsed, double dt, boolean isDay) {}

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        g.setColor(new Color(139, 69, 19));
        g.fillRect(x, y - 50, 20, 50);

        g.setColor(Color.GREEN);
        g.fillOval(x - 25, y - 130, 70, 90);
    }
}


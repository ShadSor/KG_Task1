package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.util.*;

public class Tree implements Drawable {

    private static final Set<Integer> BUSY = new HashSet<>();
    private final int x, y;

    public Tree() {
        Random r = new Random();
        int tx;
        int minDistance = 80;
        int attempts = 0;

        int leftThird = Config.WIDTH / 3;

        do {
            tx = r.nextInt(leftThird - 100) + 50;
            attempts++;
        } while (isTooClose(tx, minDistance) && attempts < 100);

        BUSY.add(tx);
        x = tx;
        y = Config.HEIGHT - 100 + r.nextInt(90);
    }

    private boolean isTooClose(int tx, int minDistance) {
        for (int busyX : BUSY) {
            if (Math.abs(busyX - tx) < minDistance) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        g.setColor(new Color(101, 67, 33));
        g.fillRect(x - 10, y - 45, 20, 45);

        g.setColor(new Color(0, 100, 0));
        for (int i = 0; i < 3; i++) {
            int h = 40;
            int w = 70 - i * 12;
            int baseY = y - 45 - i * (h - 8);
            int[] px = {x - w/2, x, x + w/2};
            int[] py = {baseY, baseY - h, baseY};
            g.fillPolygon(px, py, 3);
        }
    }
}
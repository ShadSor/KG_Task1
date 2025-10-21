package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrassClump implements Drawable {
    private final int x;
    private final int y;
    private static final Random random = new Random();
    private static final List<GrassClump> grassClumps = new ArrayList<>();
    private static final int MIN_DISTANCE = 20;

    public GrassClump(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void addRandomNonOverlapping() {
        int maxAttempts = 50;
        for (int i = 0; i < maxAttempts; i++) {
            int randX = random.nextInt(Config.WIDTH - 40) + 20;
            int minY = Config.HEIGHT - 100;
            int maxY = Config.HEIGHT - 10;
            int randY = random.nextInt(maxY - minY + 1) + minY;

            if (!isInsidePond(randX, randY)) {
                boolean overlaps = false;
                for (GrassClump clump : grassClumps) {
                    double distance = Math.hypot(clump.x - randX, clump.y - randY);
                    if (distance < MIN_DISTANCE) {
                        overlaps = true;
                        break;
                    }
                }

                if (!overlaps) {
                    GrassClump clump = new GrassClump(randX, randY);
                    grassClumps.add(clump);
                    return;
                }
            }
        }
    }

    private static boolean isInsidePond(int x, int y) {
        int pondX = Config.WIDTH - 100;
        int pondY = Config.HEIGHT - 70;
        int pondWidth = 80;
        int pondHeight = 50;

        int margin = 5;
        return x >= pondX - margin && x <= pondX + pondWidth + margin &&
                y >= pondY - margin && y <= pondY + pondHeight + margin;
    }

    public static List<Drawable> getAllDrawables() {
        List<Drawable> result = new ArrayList<>();
        for (GrassClump clump : grassClumps) {
            result.add(clump);
        }
        return result;
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        g.setColor(Color.GREEN);

        for (int i = 0; i < 3; i++) {
            int bladeBaseX = x + i * 6;
            int bladeTipX = bladeBaseX + 3;
            int bladeTipY = y - 8;

            int[] xPoints = {bladeBaseX, bladeTipX, bladeBaseX + 6};
            int[] yPoints = {y, bladeTipY, y};

            g.fillPolygon(xPoints, yPoints, 3);
        }
    }
}
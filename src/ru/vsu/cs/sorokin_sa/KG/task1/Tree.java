package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Tree implements Drawable {

    private static final List<Tree> trees = new ArrayList<>();
    private static final Random rand = new Random();

    private static final int MIN_DISTANCE_BETWEEN_TREES = 100;
    private static final int STUMP_HEIGHT = 50; // Высота ствола
    private static final int GROUND_LEVEL = Config.HEIGHT - 30; // Уровень земли

    private final int x, y;
    private final int crownHeight;
    private final int crownWidth;

    private Tree(int x, int y) {
        crownHeight = 60 + rand.nextInt(25);
        crownWidth = 100 + rand.nextInt(30);
        this.x = x;
        this.y = y;
    }

    public static void addRandomTree() {
        int leftThird = Config.WIDTH / 3;

        int tx, ty;
        int attempts = 0;

        do {
            tx = rand.nextInt(leftThird - 80) + 40;
            // Генерируем y так, чтобы дерево было полностью видно
            ty = GROUND_LEVEL - STUMP_HEIGHT + rand.nextInt(100); // y не ниже уровня земли
            attempts++;
        } while (!canPlaceTree(tx, ty) && attempts < 500);

        if (attempts < 500) {
            trees.add(new Tree(tx, ty));
        }
    }

    private static boolean canPlaceTree(int tx, int ty) {
        for (Tree tree : trees) {
            int dx = Math.abs(tree.x - tx);
            int dy = Math.abs(tree.y - ty);

            if (dx < MIN_DISTANCE_BETWEEN_TREES && dy < MIN_DISTANCE_BETWEEN_TREES) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        g.setColor(new Color(101, 67, 33));
        g.fillRect(x - 10, y - 50, 20, 50);

        g.setColor(new Color(0, 100, 0));
        for (int i = 0; i < 3; i++) {
            int h = crownHeight;
            int w = crownWidth - i * 20;
            int baseY = y - 50 - i * (h - 10);
            int[] px = {x - w / 2, x, x + w / 2};
            int[] py = {baseY, baseY - h, baseY};
            g.fillPolygon(px, py, 3);
        }
    }

    public static List<Drawable> getDrawables() {
        return new ArrayList<>(trees);
    }

}
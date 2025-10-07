package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.util.*;

public class Tree implements Drawable {

    private static final Set<Integer> BUSY = new HashSet<>();
    private final int x, y;

    public Tree() {
        Random r = new Random();
        int tx;
        int minDistance = 80; // минимальное расстояние между деревьями
        int attempts = 0;

        // Деревья только в левой трети экрана
        int leftThird = Config.WIDTH / 3;

        do {
            tx = r.nextInt(leftThird - 100) + 50; // отступ от краев в левой трети
            attempts++;
        } while (isTooClose(tx, minDistance) && attempts < 100);

        BUSY.add(tx);
        x = tx;
        /* вся высота травы (100 px) – доступна для основания */
        y = Config.HEIGHT - 100 + r.nextInt(90);   // 0-90 px вглубь травы
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
        /* ствол */
        g.setColor(new Color(101, 67, 33));
        g.fillRect(x - 10, y - 45, 20, 45);   // чуть короче

        /* 3 яруса, смещённые вниз и перекрывающиеся - более тёмный оттенок */
        g.setColor(new Color(0, 100, 0)); // Тёмно-зелёный
        for (int i = 0; i < 3; i++) {
            int h = 40;                          // высота треугольника
            int w = 70 - i * 12;                 // уменьшаем кверху
            int baseY = y - 45 - i * (h - 8);    // «-8» даёт перекрытие
            int[] px = {x - w/2, x, x + w/2};
            int[] py = {baseY, baseY - h, baseY};
            g.fillPolygon(px, py, 3);
        }
    }
}
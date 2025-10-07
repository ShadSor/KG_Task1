package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Mountain implements Drawable {

    private final int[] xs, ys;
    private final Color body;
    private final int snowLine;

    /* статический пул, чтобы горы «знали» о соседях */
    private static final List<Integer> OCCUPIED = new ArrayList<>();

    public Mountain() {
        Random r = new Random();
        int peaks = 1;
        xs = new int[peaks + 2];
        ys = new int[peaks + 2];

        xs[0] = -150;
        ys[0] = Config.HEIGHT;
        xs[xs.length - 1] = Config.WIDTH + 150;
        ys[ys.length - 1] = Config.HEIGHT;

        int minGap = Config.WIDTH / (Config.MOUNTAIN_COUNT + 2);   // мин. расстояние между горами
        for (int i = 1; i <= peaks; i++) {
            int attempts = 0;
            int cx;
            do {
                cx = minGap/2 + r.nextInt(Config.WIDTH - minGap);
                attempts++;
            } while (tooClose(cx, minGap) && attempts < 50);
            xs[i] = cx;
            ys[i] = Config.HEIGHT - 200 - r.nextInt(180);
            OCCUPIED.add(cx);
        }
        Collections.sort(OCCUPIED);

        /* сортируем по X, чтобы полигон не перекручивался */
        for (int i = 1; i <= peaks; i++) {
            for (int j = i + 1; j <= peaks; j++) {
                if (xs[i] > xs[j]) {
                    int tx = xs[i]; xs[i] = xs[j]; xs[j] = tx;
                    int ty = ys[i]; ys[i] = ys[j]; ys[j] = ty;
                }
            }
        }

        body   = new Color(80+r.nextInt(60),80+r.nextInt(60),80+r.nextInt(60));
        snowLine = Arrays.stream(ys).min().orElse(Config.HEIGHT) + 35;
    }

    private boolean tooClose(int cx, int minGap) {
        for (int ox : OCCUPIED) if (Math.abs(ox - cx) < minGap) return true;
        return false;
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        /* тело горы */
        g.setColor(body);
        g.fillPolygon(xs, ys, xs.length);

        /* ----- снежная шапка, точно повторяющая форму вершины ----- */
        for (int i = 1; i <= xs.length - 2; i++) {
            if (ys[i] >= snowLine) continue;

            // Определяем точки пересечения снега со склонами
            int snowHeight = 40; // Фиксированная высота снега

            // Находим точки на левом склоне на высоте снега
            double leftSlope = (double)(ys[i] - ys[i-1]) / (xs[i] - xs[i-1]);
            int leftSnowX = xs[i] - (int)(snowHeight / Math.abs(leftSlope));
            int leftSnowY = ys[i] + snowHeight;

            // Находим точки на правом склоне на высоте снега
            double rightSlope = (double)(ys[i] - ys[i+1]) / (xs[i] - xs[i+1]);
            int rightSnowX = xs[i] + (int)(snowHeight / Math.abs(rightSlope));
            int rightSnowY = ys[i] + snowHeight;

            // Создаем полигон снега, который точно повторяет форму вершины
            int[] snowXs = {leftSnowX, xs[i], rightSnowX};
            int[] snowYs = {leftSnowY, ys[i], rightSnowY};

            /* вертикальный градиент белого-серого */
            GradientPaint gp = new GradientPaint(
                    0, ys[i], Color.WHITE,
                    0, leftSnowY, new Color(230,230,250));
            Paint old = g.getPaint();
            g.setPaint(gp);

            g.fillPolygon(snowXs, snowYs, 3);
            g.setPaint(old);
        }
    }
}
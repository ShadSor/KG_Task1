package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class Cow implements Animatable, Drawable {
    public final int baseX, baseY;
    public boolean present = true;
    public double lift = 0.0;

    public Cow(int baseX, int baseY) {
        this.baseX = baseX;
        this.baseY = baseY;
    }

    @Override
    public void update(double elapsed, double dayPhase, boolean isDay) {
        if (!present) lift = Math.min(1.0, lift + 0.04);
        else lift = Math.max(0.0, lift - 0.04);
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        if (present && lift < 0.5) {
            drawCow(g, baseX, baseY);
        } else if (!present) {
            int cy = (int)(baseY - lift * 80);
            Graphics2D g2 = (Graphics2D) g.create();
            Composite old = g2.getComposite();
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float)Math.max(0, 1.0 - lift)));
            drawCow(g2, baseX, cy);
            g2.setComposite(old);
            g2.dispose();
        } else {
            drawCow(g, baseX, baseY);
        }
    }

    private void drawCow(Graphics2D g, int x, int y) {
        Stroke originalStroke = g.getStroke();

        // НОГИ
        g.setColor(Color.BLACK);
        // Передние ноги
        g.fillRect(x - 19, y + 18, 8, 20);
        g.fillRect(x - 8, y + 18, 8, 20);
        // Задние ноги
        g.fillRect(x + 27, y + 18, 8, 20);
        g.fillRect(x + 37, y + 16, 8, 24);

        g.setColor(Color.WHITE);
        g.fillOval(x - 35, y - 25, 70, 45);

        // ЗАДНИЕ БЕДРА
        g.fillOval(x + 15, y - 22, 40, 40);

        // ЧЕРНЫЕ ПЯТНА
        g.setColor(Color.BLACK);
        // Пятно на спине
        g.fillOval(x - 10, y - 20, 40, 25);
        // Боковые пятна
        g.fillOval(x - 30, y - 10, 20, 15);
        g.fillOval(x + 30, y - 5, 18, 12);

        // ГОЛОВА
        g.setColor(Color.WHITE);
        g.fillOval(x - 55, y - 20, 35, 28);

        // МОРДА
        g.setColor(new Color(255, 200, 200));
        g.fillOval(x - 50, y - 8, 25, 15);

        // УШИ
        g.setColor(Color.WHITE);
        g.fillOval(x - 64, y - 21, 15, 13);
        g.fillOval(x - 28, y - 21, 15, 13);
        g.setColor(new Color(255, 180, 180));
        g.fillOval(x - 62, y - 20, 12, 10);
        g.fillOval(x - 26, y - 20, 12, 10);

        // ГЛАЗ
        g.setColor(Color.BLACK);
        g.fillOval(x - 47, y - 15, 6, 6);
        g.fillOval(x - 35, y - 15, 6, 6);

        // НОЗДРИ
        g.fillOval(x - 42, y - 5, 4, 4);
        g.fillOval(x - 37, y - 5, 4, 4);

        // РОГА
        g.setColor(new Color(150, 100, 50));
        g.fillOval(x - 48, y - 25, 6, 8);
        g.fillOval(x - 35, y - 25, 6, 8);

        // КОПЫТА
        g.setColor(new Color(80, 60, 40));
        g.fillRect(x - 22, y + 36, 12, 6);
        g.fillRect(x - 10, y + 36, 12, 6);
        g.fillRect(x + 24, y + 36, 12, 6);
        g.fillRect(x + 35, y + 36, 12, 6);

        // ХВОСТ (короткий и аккуратный)
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.drawLine(x + 52, y - 15, x + 90, y);
        // Кисточка на хвосте
        g.fillOval(x + 88, y - 2, 6, 6);

        // Восстанавливаем оригинальный stroke
        g.setStroke(originalStroke);
    }
}
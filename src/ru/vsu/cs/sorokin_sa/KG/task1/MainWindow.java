package ru.vsu.cs.sorokin_sa.KG.task1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindow extends JPanel {
    private double elapsed = 0;

    private SunMoon sunMoon;
    private List<SceneObject> objects = new ArrayList<>();

    public MainWindow() {
        setPreferredSize(new Dimension(Config.WIDTH, Config.HEIGHT));
        setBackground(Config.SKY_NIGHT);

        sunMoon = new SunMoon();
        objects.add(sunMoon);

        Timer timer = new Timer(Config.FRAME_DELAY, e -> {
            elapsed += Config.FRAME_DELAY / 1000.0;

            // Полный цикл: 60 секунд (30 день + 30 ночь)
            double fullCycleTime = elapsed % (Config.DAY_DURATION * 2);
            boolean isDay = (fullCycleTime < Config.DAY_DURATION);

            // Время внутри текущей фазы (0.0 - 1.0)
            double phaseTime = isDay ?
                    (fullCycleTime / Config.DAY_DURATION) :
                    ((fullCycleTime - Config.DAY_DURATION) / Config.DAY_DURATION);

            for (SceneObject obj : objects) {
                obj.update(elapsed, phaseTime, isDay);
            }
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double fullCycleTime = elapsed % (Config.DAY_DURATION * 2);
        boolean isDay = (fullCycleTime < Config.DAY_DURATION);
        double phaseTime = isDay ?
                (fullCycleTime / Config.DAY_DURATION) :
                ((fullCycleTime - Config.DAY_DURATION) / Config.DAY_DURATION);

        // Рисуем небо
        sunMoon.drawSky(g, phaseTime, isDay);

        // Рисуем все объекты
        for (SceneObject obj : objects) {
            obj.draw(g);
        }

        g.dispose();
    }
}

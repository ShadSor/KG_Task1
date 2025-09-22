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


        for (int i = 0; i < Config.CLOUD_COUNT; i++) objects.add(new Cloud());
        for (int i = 0; i < Config.MOUNTAIN_COUNT; i++) objects.add(new Mountain());

        objects.add(new Grass());
        objects.add(new LogWood());

        for (int i = 0; i < Config.TREE_COUNT; i++) objects.add(new Tree());


        Timer timer = new Timer(Config.FRAME_DELAY, e -> {
            elapsed += Config.FRAME_DELAY / 1000.0;

            double fullCycleTime = elapsed % (Config.DAY_DURATION * 2);
            boolean isDay = (fullCycleTime < Config.DAY_DURATION);

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

        sunMoon.drawSky(g, phaseTime, isDay);

        for (SceneObject obj : objects) {
            obj.draw(g, isDay);
        }

        g.dispose();
    }
}

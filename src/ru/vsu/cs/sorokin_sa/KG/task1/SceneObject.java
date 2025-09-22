package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public interface SceneObject {
    void update(double elapsed, double t, boolean isDay);
    void draw(Graphics2D g, boolean isDay);
}

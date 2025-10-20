package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;

public class Config {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public static final int FRAME_DELAY = 16; // мс
    public static final double DAY_DURATION = 5.0;

    // Солнце

    public static final int SUN_RADIUS = 36;

    public static final Color SUN_COLOR = Color.YELLOW;
    public static final Color SUN_OUTLINE = Color.ORANGE;

    public static final float SUN_GLOW_RADIUS_MULTIPLIER = 1.8f;
    public static final Color SUN_GLOW_CENTER = new Color(255, 255, 200, 255);
    public static final Color SUN_GLOW_EDGE = new Color(255, 200, 50, 0);

    // Луна

    public static final int MOON_RADIUS = 30;

    public static final Color MOON_COLOR = Color.LIGHT_GRAY;
    public static final Color MOON_OUTLINE = Color.GRAY;

    public static final float MOON_GLOW_RADIUS_MULTIPLIER = 1.6f;
    public static final Color MOON_GLOW_CENTER = new Color(220, 220, 220, 200);
    public static final Color MOON_GLOW_EDGE = new Color(200, 200, 255, 0);

    public static final Color SKY_NIGHT = new Color(10, 15, 50);
    public static final Color SKY_DAWN_DUSK = new Color(100, 60, 150);
    public static final Color SKY_SUNRISE_SUNSET = new Color(255, 150, 50);
    public static final Color SKY_DAY = new Color(135, 206, 250);

    public static final double ARC_CENTER_X = Config.WIDTH * 0.5;
    public static final double ARC_CENTER_Y = Config.HEIGHT * 0.61;
    public static final double ARC_RADIUS_X = Config.WIDTH * 0.62;
    public static final double ARC_RADIUS_Y = Config.WIDTH * 0.3;
    public static final double ARC_START_ANGLE = Math.toRadians(200);
    public static final double ARC_END_ANGLE = Math.toRadians(340);

    public static final int CLOUD_COUNT = 5;

    public static final int MOUNTAIN_COUNT = 3;

    public static final int TREE_COUNT = 3;

    public static final Color GRASS_COLOR = new Color(34, 139, 34);
    public static final int GRASS_HEIGHT = 100;


    public static final int COW_X = WIDTH / 2 - 30;
    public static final int COW_Y = HEIGHT - 90;

    public static final int FLOWER_COUNT = 8;
    public static final int FLOWER_MIN_DISTANCE = 30;
}

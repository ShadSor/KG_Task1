package ru.vsu.cs.sorokin_sa.KG.task1;

import java.awt.*;
import java.util.*;
import java.util.List;

public class FlowerManager implements Drawable {
    private final List<Drawable> flowers = new ArrayList<>();
    private final Set<Integer> occupiedPositions = new HashSet<>();

    public FlowerManager() {
        Random random = new Random();
        int leftThird = Config.WIDTH / 3;
        int grassTop = Config.HEIGHT - 100;
        int grassBottom = Config.HEIGHT - 10;

        for (int i = 0; i < Config.FLOWER_COUNT; i++) {
            int x, y;
            int attempts = 0;
            boolean positionFound;

            do {
                x = 20 + random.nextInt(leftThird - 40); // Отступ от краев
                y = grassTop + random.nextInt(grassBottom - grassTop);
                positionFound = !isTooClose(x);
                attempts++;
            } while (!positionFound && attempts < 100);

            if (positionFound) {
                occupiedPositions.add(x);
                int flowerType = random.nextInt(3);
                switch (flowerType) {
                    case 0:
                        flowers.add(new FlowerA(x, y));
                        break;
                    case 1:
                        flowers.add(new FlowerB(x, y));
                        break;
                    case 2:
                        flowers.add(new FlowerC(x, y));
                        break;
                }
            }
        }
    }

    private boolean isTooClose(int x) {
        for (int occupiedX : occupiedPositions) {
            if (Math.abs(occupiedX - x) < Config.FLOWER_MIN_DISTANCE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void draw(Graphics2D g, boolean isDay) {
        for (Drawable flower : flowers) {
            flower.draw(g, isDay);
        }
    }
}

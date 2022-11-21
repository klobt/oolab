package agh.ics.oop;

import java.util.Random;

public class GrassField extends AbstractWorldMap {
    private Grass[] grassArray;

    private Vector2d nextPosition(Random random, int n, int i) {
        Vector2d proposed = new Vector2d(
            random.nextInt((int) Math.sqrt(n * 10)),
            random.nextInt((int) Math.sqrt(n * 10))
        );
        for (int j = 0; j < i; j++) {
            if (grassArray[i] != null && proposed.equals(grassArray[i].getPosition())) {
                return nextPosition(random, n, i);
            }
        }
        return proposed;
    }

    public GrassField(int n) {
        super();
        Random random = new Random();
        this.grassArray = new Grass[n];
        for (int i = 0; i < n; i++) {
            this.grassArray[i] = new Grass(nextPosition(random, n, i));
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if (object != null) {
            return object;
        }
        for (Grass grass : grassArray) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public Vector2d lowerLeft() {
        int x = Integer.MAX_VALUE;
        int y = Integer.MAX_VALUE;
        for (Animal animal : animals) {
            x = Math.min(x, animal.getPosition().x);
            y = Math.min(y, animal.getPosition().y);
        }
        for (Grass grass : grassArray) {
            x = Math.min(x, grass.getPosition().x);
            y = Math.min(y, grass.getPosition().y);
        }
        return new Vector2d(x, y);
    }

    @Override
    public Vector2d upperRight() {
        int x = Integer.MIN_VALUE;
        int y = Integer.MIN_VALUE;
        for (Animal animal : animals) {
            x = Math.max(x, animal.getPosition().x);
            y = Math.max(y, animal.getPosition().y);
        }
        for (Grass grass : grassArray) {
            x = Math.max(x, grass.getPosition().x);
            y = Math.max(y, grass.getPosition().y);
        }
        return new Vector2d(x, y);
    }
}

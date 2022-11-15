package agh.ics.oop;

import java.util.Random;
import java.util.Vector;

public class GrassField implements IWorldMap {
    private Grass[] grassArray;
    private Vector<Animal> animals;
    private MapVisualizer mapVisualizer;

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
        Random random = new Random();
        this.grassArray = new Grass[n];
        for (int i = 0; i < n; i++) {
            this.grassArray[i] = new Grass(nextPosition(random, n, i));
        }
        this.animals = new Vector<Animal>();
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return objectAt(position) == null;
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animals.add(animal);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        for (Grass grass : grassArray) {
            if (grass.getPosition().equals(position)) {
                return grass;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        int llX = Integer.MAX_VALUE;
        int llY = Integer.MAX_VALUE;
        int urX = Integer.MIN_VALUE;
        int urY = Integer.MIN_VALUE;
        for (Animal animal : animals) {
            llX = Math.min(llX, animal.getPosition().x);
            llY = Math.min(llY, animal.getPosition().y);
            urX = Math.max(urX, animal.getPosition().x);
            urY = Math.max(urY, animal.getPosition().y);
        }
        for (Grass grass : grassArray) {
            llX = Math.min(llX, grass.getPosition().x);
            llY = Math.min(llY, grass.getPosition().y);
            urX = Math.max(urX, grass.getPosition().x);
            urY = Math.max(urY, grass.getPosition().y);
        }
        return mapVisualizer.draw(new Vector2d(llX, llY), new Vector2d(urX, urY));
    }
}

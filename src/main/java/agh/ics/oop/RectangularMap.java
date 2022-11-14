package agh.ics.oop;

import java.util.Vector;

public class RectangularMap implements IWorldMap {
    private Vector<Animal> animals;
    private final int width, height;
    private MapVisualizer mapVisualizer;

    public RectangularMap(int width, int height) {
        this.animals = new Vector<Animal>();
        this.width = width;
        this.height = height;
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x >= 0 && position.x < width &&
                position.y >= 0 && position.y < height &&
                objectAt(position) == null;
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
        return position.x >= 0 && position.x < width &&
                position.y >= 0 && position.y < height &&
                objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (animal.isAt(position)) {
                return animal;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return mapVisualizer.draw(new Vector2d(0, 0), new Vector2d(width-1, height-1));
    }
}

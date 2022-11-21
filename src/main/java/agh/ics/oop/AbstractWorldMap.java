package agh.ics.oop;

import java.util.Vector;

public abstract class AbstractWorldMap implements IWorldMap {
    protected Vector<Animal> animals;
    protected MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
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
        return null;
    }

    protected abstract Vector2d lowerLeft();
    protected abstract Vector2d upperRight();

    @Override
    public String toString() {
        return mapVisualizer.draw(lowerLeft(), upperRight());
    }
}

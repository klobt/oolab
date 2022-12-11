package agh.ics.oop;

import java.util.Map;
import java.util.HashMap;

public abstract class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Animal> animals;
    protected MapVisualizer mapVisualizer;

    public AbstractWorldMap() {
        this.animals = new HashMap<Vector2d, Animal>();
        this.mapVisualizer = new MapVisualizer(this);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Object object = objectAt(position);
        return !(object instanceof Animal);
    }

    @Override
    public boolean place(Animal animal) {
        if (canMoveTo(animal.getPosition())) {
            animal.addPositionChangeObserver(this);
            animals.put(animal.getPosition(), animal);
            return true;
        } else {
            throw new IllegalArgumentException("cannot move to " + animal.getPosition().toString());
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return animals.get(position);
    }

    public abstract Vector2d lowerLeft();
    public abstract Vector2d upperRight();

    @Override
    public String toString() {
        return mapVisualizer.draw(lowerLeft(), upperRight());
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, int zIndex) {
        Animal animal = animals.get(oldPosition);
        if (animal != null && canMoveTo(newPosition)) {
            animals.remove(oldPosition);
            animals.put(newPosition, animal);
        }
    }
}

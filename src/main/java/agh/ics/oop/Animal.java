package agh.ics.oop;

import java.util.HashSet;
import java.util.Set;

public class Animal implements IMapElement {
    private Vector2d position;
    private Vector2d oldPosition;
    private MapDirection orientation = MapDirection.NORTH;
    protected IWorldMap map;
    protected Set<IPositionChangeObserver> observers = new HashSet<>();

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }
    public MapDirection getOrientation() {
        return orientation;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> setPosition(position.add(orientation.toUnitVector()));
            case BACKWARD -> setPosition(position.subtract(orientation.toUnitVector()));
        }
    }

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        if (map != null) {
            map.place(this);
        }
    }

    public Animal(IWorldMap map) {
        this(map, new Vector2d(2, 2));
    }

    public Animal() {
        this(null);
    }

    private void setPosition(Vector2d position) {
        if (map != null && map.canMoveTo(position)) {
            this.oldPosition = this.position;
            this.position = position;
            positionChanged();
        }
    }

    private void positionChanged() {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, position, 0);
        }
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }
}
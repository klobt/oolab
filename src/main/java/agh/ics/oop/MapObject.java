package agh.ics.oop;

import java.util.ArrayList;

public abstract class MapObject {
    protected Vector2d position;
    protected Vector2d oldPosition = null;
    protected IWorldMap map;
    protected ArrayList<IPositionChangeObserver> observers = new ArrayList<IPositionChangeObserver>();

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public Vector2d getPosition() {
        return position;
    }

    protected void setPosition(Vector2d position) {
        if (map != null && map.canMoveTo(position)) {
            this.oldPosition = this.position;
            this.position = position;
            positionChanged();
        }
    }

    public MapObject(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
    }

    public void addObserver(IPositionChangeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IPositionChangeObserver observer) {
        observers.remove(observer);
    }

    public void positionChanged() {
        for (IPositionChangeObserver observer : observers) {
            observer.positionChanged(oldPosition, position);
        }
    }
}

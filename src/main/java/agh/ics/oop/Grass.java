package agh.ics.oop;

public class Grass implements IMapElement {
    private Vector2d position;

    public Grass(Vector2d initialPosition) {
        position = initialPosition;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public Vector2d getPosition() {
        return position;
    }

    @Override
    public void addObserver(IPositionChangeObserver observer) {
        // ignoruj :p
    }

    @Override
    public void removeObserver(IPositionChangeObserver observer) {
        // ignoruj :p
    }
}

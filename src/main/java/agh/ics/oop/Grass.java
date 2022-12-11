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
    public void addPositionChangeObserver(IPositionChangeObserver observer) {
        // ignoruj :p
    }

    @Override
    public void removePositionChangeObserver(IPositionChangeObserver observer) {
        // ignoruj :p
    }

    @Override
    public void addOrientationChangeObserver(IOrientationChangeObserver observer) {
        // ignoruj
    }

    @Override
    public void removeOrientationChangeObserver(IOrientationChangeObserver observer) {
        // ignoruj
    }

    @Override
    public String getImagePath() {
        return "grass.png";
    }

    @Override
    public String getLabelString() {
        return "Trawa";
    }
}

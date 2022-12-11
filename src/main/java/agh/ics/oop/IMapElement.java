package agh.ics.oop;

public interface IMapElement {
    Vector2d getPosition();

    default boolean isAt(Vector2d position) {
        return position.equals(getPosition());
    }

    void addPositionChangeObserver(IPositionChangeObserver observer);

    void removePositionChangeObserver(IPositionChangeObserver observer);

    void addOrientationChangeObserver(IOrientationChangeObserver observer);

    void removeOrientationChangeObserver(IOrientationChangeObserver observer);

    String getImagePath();

    String getLabelString();
}

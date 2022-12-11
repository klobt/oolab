package agh.ics.oop;

public interface IMapElement {
    Vector2d getPosition();

    default boolean isAt(Vector2d position) {
        return position.equals(getPosition());
    }

    void addObserver(IPositionChangeObserver observer);

    void removeObserver(IPositionChangeObserver observer);
}

package agh.ics.oop;

public interface IOrientationChangeObserver {
    void orientationChanged(Vector2d position, MapDirection oldOrientation, MapDirection newOrientation);
}

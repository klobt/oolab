package agh.ics.oop;

public interface IOrientationChangeObserver {
    void orientationChanged(MapDirection oldOrientation, MapDirection newOrientation);
}

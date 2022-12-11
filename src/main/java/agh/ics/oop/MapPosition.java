package agh.ics.oop;

public class MapPosition {
    Vector2d position;
    int zIndex;

    public static final int MIN_ZINDEX = -10;
    public static final int MAX_ZINDEX = 0;

    public MapPosition(Vector2d position, int zIndex) {
        this.position = position;
        this.zIndex = zIndex;
    }
}

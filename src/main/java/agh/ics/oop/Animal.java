package agh.ics.oop;

public class Animal extends MapObject {
    private MapDirection orientation = MapDirection.NORTH;

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
        super(map, initialPosition);
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
}
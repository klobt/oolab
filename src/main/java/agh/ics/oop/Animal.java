package agh.ics.oop;

public class Animal {
    private Vector2d position;
    private MapDirection orientation = MapDirection.NORTH;
    private IWorldMap map;

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "^";
            case EAST -> ">";
            case SOUTH -> "v";
            case WEST -> "<";
        };
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    private void setPosition(Vector2d position) {
        if (map != null && map.canMoveTo(position)) {
            this.position = position;
        }
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
}
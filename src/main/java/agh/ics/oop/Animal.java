package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
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
        if (map != null) {
            if (map.canMoveTo(position)) {
                this.position = position;
                map.place(this);
            }
        } else if (position.x >= 0 && position.x <= 4 && position.y >= 0 && position.y <= 4) {
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

    public Animal(IWorldMap map) {
        this.map = map;
    }

    /* Można ustawić wartości domyślne dla argumentów konstruktora
       `null` dla `map` i `new Vector2d(2, 2)` dla `initialPosition`.
       W ten sposób pozostałe konstruktory nie będą konieczne.
     */
    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        setPosition(initialPosition);
    }
}

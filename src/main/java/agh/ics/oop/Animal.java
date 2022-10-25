package agh.ics.oop;

public class Animal {
    private Vector2d position = new Vector2d(2, 2);
    private MapDirection orientation = MapDirection.NORTH;

    @Override
    public String toString() {
        return position.toString() + " " + orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return position.equals(this.position);
    }
}

package agh.ics.oop;

public class Grass extends MapObject {
    public Grass(IWorldMap map, Vector2d initialPosition) {
        super(map, initialPosition);
    }

    @Override
    public String toString() {
        return "*";
    }
}

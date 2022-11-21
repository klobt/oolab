package agh.ics.oop;

public class RectangularMap extends AbstractWorldMap {
    private final int width, height;

    public RectangularMap(int width, int height) {
        super();
        this.width = width;
        this.height = height;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.x >= 0 && position.x < width &&
                position.y >= 0 && position.y < height &&
                super.canMoveTo(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return position.x >= 0 && position.x < width &&
                position.y >= 0 && position.y < height &&
                super.isOccupied(position);
    }

    @Override
    public Vector2d lowerLeft() {
        return new Vector2d(0, 0);
    }

    @Override
    public Vector2d upperRight() {
        return new Vector2d(width-1, height-1);
    }
}

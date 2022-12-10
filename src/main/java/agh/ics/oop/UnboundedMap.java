package agh.ics.oop;

public class UnboundedMap extends AbstractWorldMap {
    private MapBoundary mapBoundary = new MapBoundary();

    @Override
    public boolean place(Animal animal) {
        if (super.place(animal)) {
            mapBoundary.addObject(animal);
            return true;
        } else {
            return false;
        }
    }
    @Override
    public Vector2d lowerLeft() {
        return new Vector2d(mapBoundary.xOrdered.firstKey().x, mapBoundary.yOrdered.firstKey().y);
    }

    @Override
    public Vector2d upperRight() {
        return new Vector2d(mapBoundary.xOrdered.lastKey().x, mapBoundary.yOrdered.lastKey().y);
    }
}

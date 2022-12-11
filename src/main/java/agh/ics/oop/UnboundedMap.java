package agh.ics.oop;

public class UnboundedMap extends AbstractWorldMap {
    protected MapBoundary mapBoundary = new MapBoundary();

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
        return new Vector2d(mapBoundary.xOrdered.firstKey().position.x, mapBoundary.yOrdered.firstKey().position.y);
    }

    @Override
    public Vector2d upperRight() {
        return new Vector2d(mapBoundary.xOrdered.lastKey().position.x, mapBoundary.yOrdered.lastKey().position.y);
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (int zindex = MapPosition.MAX_ZINDEX; zindex >= MapPosition.MIN_ZINDEX; zindex--) {
            Object object = mapBoundary.xOrdered.get(new MapPosition(position, zindex));
            if (object != null) {
                return object;
            }
        }
        return null;
    }
}

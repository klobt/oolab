package agh.ics.oop;

import java.util.*;

class XComparer implements Comparator<MapPosition> {
    @Override
    public int compare(MapPosition v1, MapPosition v2) {
        if (v1.position.x == v2.position.x) {
            if (v1.zIndex == v2.zIndex) {
                return v1.position.y - v2.position.y;
            } else {
                return v2.zIndex - v1.zIndex;
            }
        } else {
            return v1.position.x - v2.position.x;
        }
    }
}

class YComparer implements Comparator<MapPosition> {
    @Override
    public int compare(MapPosition v1, MapPosition v2) {
        if (v1.position.y == v2.position.y) {
            if (v1.zIndex == v2.zIndex) {
                return v1.position.x - v2.position.x;
            } else {
                return v2.zIndex - v1.zIndex;
            }
        } else {
            return v1.position.y - v2.position.y;
        }
    }
}

public class MapBoundary implements IPositionChangeObserver {
    public TreeMap<MapPosition, IMapElement> xOrdered = new TreeMap<>(new XComparer());
    public TreeMap<MapPosition, IMapElement> yOrdered = new TreeMap<>(new YComparer());

    public void addObject(IMapElement object, int zIndex) {
        if (zIndex >= MapPosition.MIN_ZINDEX && zIndex <= MapPosition.MAX_ZINDEX) {
            xOrdered.put(new MapPosition(object.getPosition(), zIndex), object);
            yOrdered.put(new MapPosition(object.getPosition(), zIndex), object);
            object.addObserver(this);
        } else {
            throw new IllegalArgumentException("zIndex outside allowed range of [" + MapPosition.MIN_ZINDEX + ", " + MapPosition.MAX_ZINDEX + "]");
        }
    }

    public void addObject(IMapElement object) {
        addObject(object, 0);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, int zIndex) {
        IMapElement objectFromX = xOrdered.remove(new MapPosition(oldPosition, zIndex));
        if (objectFromX != null) {
            xOrdered.put(new MapPosition(newPosition, zIndex), objectFromX);
        }
        IMapElement objectFromY = yOrdered.remove(new MapPosition(oldPosition, zIndex));
        if (objectFromY != null) {
            yOrdered.put(new MapPosition(newPosition, zIndex), objectFromY);
        }

    }
}

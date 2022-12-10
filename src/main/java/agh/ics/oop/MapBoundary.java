package agh.ics.oop;

import java.util.*;

class XComparer implements Comparator<Vector2d> {
    @Override
    public int compare(Vector2d v1, Vector2d v2) {
        if (v1.x == v2.x) {
            return v1.y - v2.y;
        } else {
            return v1.x - v2.x;
        }
    }
}

class YComparer implements Comparator<Vector2d> {
    @Override
    public int compare(Vector2d v1, Vector2d v2) {
        if (v1.y == v2.y) {
            return v1.x - v2.x;
        } else {
            return v1.y - v2.y;
        }
    }
}

public class MapBoundary implements IPositionChangeObserver {
    public TreeMap<Vector2d, MapObject> xOrdered = new TreeMap<Vector2d, MapObject>(new XComparer());
    public TreeMap<Vector2d, MapObject> yOrdered = new TreeMap<Vector2d, MapObject>(new YComparer());

    public void addObject(MapObject object) {
        xOrdered.put(object.getPosition(), object);
        yOrdered.put(object.getPosition(), object);
        object.addObserver(this);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        MapObject objectFromX = xOrdered.remove(oldPosition);
        if (objectFromX != null) {
            xOrdered.put(newPosition, objectFromX);
        }
        MapObject objectFromY = yOrdered.remove(oldPosition);
        if (objectFromY != null) {
            yOrdered.put(newPosition, objectFromY);
        }

    }
}

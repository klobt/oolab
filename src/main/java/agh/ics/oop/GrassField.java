package agh.ics.oop;

import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class GrassField extends UnboundedMap {
    private Map<Vector2d, Grass> grassMap;

    private Vector2d nextPosition(Random random, int n) {
        Vector2d proposed = new Vector2d(
            random.nextInt((int) Math.sqrt(n * 10)),
            random.nextInt((int) Math.sqrt(n * 10))
        );
        if (grassMap.containsKey(proposed)) {
            return nextPosition(random, n);
        }
        return proposed;
    }

    public GrassField(int n) {
        super();
        Random random = new Random();
        this.grassMap = new HashMap<Vector2d, Grass>();
        for (int i = 0; i < n; i++) {
            Vector2d position = nextPosition(random, n);
            this.grassMap.put(position, new Grass(this, position));
        }
    }

    @Override
    public Object objectAt(Vector2d position) {
        Object object = super.objectAt(position);
        if (object != null) {
            return object;
        }
        return grassMap.get(position);
    }
}

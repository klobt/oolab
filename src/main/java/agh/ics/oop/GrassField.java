package agh.ics.oop;

import java.util.Random;

public class GrassField extends UnboundedMap {
    private Vector2d nextPosition(Random random, int n) {
        Vector2d proposed = new Vector2d(
            random.nextInt((int) Math.sqrt(n * 10)),
            random.nextInt((int) Math.sqrt(n * 10))
        );
        if (mapBoundary.xOrdered.containsKey(proposed)) {
            return nextPosition(random, n);
        }
        return proposed;
    }

    public GrassField(int n) {
        super();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            Vector2d position = nextPosition(random, n);
            mapBoundary.addObject(new Grass(this, position));
        }
    }
}

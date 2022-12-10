import agh.ics.oop.Animal;
import agh.ics.oop.RectangularMap;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    @Test
    void canMoveToTest() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int mapWidth = random.nextInt(200) + 1;
            int mapHeight = random.nextInt(200) + 1;
            RectangularMap map = new RectangularMap(mapWidth, mapHeight);
            for (int j = 0; j < 100; j++) {
                Vector2d positionOutside1 = new Vector2d(-random.nextInt(), -random.nextInt());
                assertFalse(map.canMoveTo(positionOutside1));
                Vector2d positionOutside2 = new Vector2d(mapWidth + random.nextInt(), mapHeight + random.nextInt());
                assertFalse(map.canMoveTo(positionOutside2));
            }
            for (int j = 0; j < 200; j++) {
                Vector2d position = new Vector2d(random.nextInt(mapWidth), random.nextInt(mapHeight));
                try {
                    map.place(new Animal(map, position));
                    assertFalse(map.canMoveTo(position));
                } catch (IllegalArgumentException ex) {}
            }
        }
    }

    @Test
    void placeTest() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int mapWidth = random.nextInt(200) + 1;
            int mapHeight = random.nextInt(200) + 1;
            RectangularMap map = new RectangularMap(mapWidth, mapHeight);
            for (int j = 0; j < 100; j++) {
                Vector2d positionOutside1 = new Vector2d(-random.nextInt(), -random.nextInt());
                assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, positionOutside1)));
                Vector2d positionOutside2 = new Vector2d(mapWidth + random.nextInt(), mapHeight + random.nextInt());
                assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, positionOutside2)));
            }
            for (int j = 0; j < 200; j++) {
                Vector2d position = new Vector2d(random.nextInt(mapWidth), random.nextInt(mapHeight));
                try {
                    map.place(new Animal(map, position));
                    assertFalse(map.place(new Animal(map, position)));
                } catch (IllegalArgumentException ex) {}
            }
        }
    }

    @Test
    void isOccupiedTest() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int mapWidth = random.nextInt(200) + 1;
            int mapHeight = random.nextInt(200) + 1;
            RectangularMap map = new RectangularMap(mapWidth, mapHeight);
            for (int j = 0; j < 200; j++) {
                Vector2d position = new Vector2d(random.nextInt(mapWidth), random.nextInt(mapHeight));
                try {
                    map.place(new Animal(map, position));
                    assert(map.isOccupied(position));
                } catch (IllegalArgumentException ex) {}
            }
            for (int j = 0; j < 100; j++) {
                Vector2d positionOutside1 = new Vector2d(-random.nextInt(), -random.nextInt());
                assertFalse(map.isOccupied(positionOutside1));
                Vector2d positionOutside2 = new Vector2d(mapWidth + random.nextInt(), mapHeight + random.nextInt());
                assertFalse(map.isOccupied(positionOutside2));
            }
        }
    }

    @Test
    void objectAtTest() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int mapWidth = random.nextInt(200) + 1;
            int mapHeight = random.nextInt(200) + 1;
            RectangularMap map = new RectangularMap(mapWidth, mapHeight);
            for (int j = 0; j < 100; j++) {
                Vector2d positionOutside1 = new Vector2d(-random.nextInt(), -random.nextInt());
                assertEquals(map.objectAt(positionOutside1), null);
                Vector2d positionOutside2 = new Vector2d(mapWidth + random.nextInt(), mapHeight + random.nextInt());
                assertEquals(map.objectAt(positionOutside2), null);
            }
            for (int j = 0; j < 200; j++) {
                try {
                    Animal animal = new Animal(map, new Vector2d(random.nextInt(mapWidth), random.nextInt(mapHeight)));
                    map.place(animal);
                    assertEquals(map.objectAt(animal.getPosition()), animal);
                } catch (IllegalArgumentException ex) {}
            }
        }
    }
}

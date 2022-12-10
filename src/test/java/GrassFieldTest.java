import agh.ics.oop.Animal;
import agh.ics.oop.GrassField;
import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    @Test
    void canMoveToTest() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int grassN = random.nextInt(1000) + 1;
            GrassField grassField = new GrassField(grassN);
            for (int j = 0; j < 200; j++) {
                Vector2d position = new Vector2d(
                        random.nextInt((int) Math.sqrt(grassN * 10)),
                        random.nextInt((int) Math.sqrt(grassN * 10))
                );
                try {
                    grassField.place(new Animal(grassField, position));
                    assertFalse(grassField.canMoveTo(position));
                } catch (IllegalArgumentException ex) {}
            }
        }
    }

    @Test
    void placeTest() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int grassN = random.nextInt(1000) + 1;
            GrassField grassField = new GrassField(grassN);
            for (int j = 0; j < 200; j++) {
                Vector2d position = new Vector2d(
                    random.nextInt((int) Math.sqrt(grassN * 10)),
                    random.nextInt((int) Math.sqrt(grassN * 10))
                );
                try {
                    grassField.place(new Animal(grassField, position));
                    assertThrows(IllegalArgumentException.class, () -> grassField.place(new Animal(grassField, position)));
                } catch (IllegalArgumentException ex) {}
            }
        }
    }

    @Test
    void isOccupiedTest() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int grassN = random.nextInt(1000) + 1;
            GrassField grassField = new GrassField(grassN);
            for (int j = 0; j < 200; j++) {
                Vector2d position = new Vector2d(
                        random.nextInt((int) Math.sqrt(grassN * 10)),
                        random.nextInt((int) Math.sqrt(grassN * 10))
                );
                try {
                    grassField.place(new Animal(grassField, position));
                    assert(grassField.isOccupied(position));
                } catch (IllegalArgumentException ex) {}
            }
        }
    }

    @Test
    void objectAtTest() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int grassN = random.nextInt(1000) + 1;
            GrassField grassField = new GrassField(grassN);
            for (int j = 0; j < 200; j++) {
                try {
                    Animal animal = new Animal(
                        grassField,
                        new Vector2d(
                            random.nextInt((int) Math.sqrt(grassN * 10)),
                            random.nextInt((int) Math.sqrt(grassN * 10))
                        )
                    );
                    grassField.place(animal);
                    assertEquals(grassField.objectAt(animal.getPosition()), animal);
                } catch (IllegalArgumentException ex) {}
            }
        }
    }
}

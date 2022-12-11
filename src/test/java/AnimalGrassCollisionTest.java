import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalGrassCollisionTest {
    @Test
    public void test() {
        for (int i = 0; i < 100; i++) {
            GrassField grassField = new GrassField(100);
            Vector2d ll = grassField.lowerLeft(), ur = grassField.upperRight();
            Animal animal = new Animal(grassField, ll);
            animal.move(MoveDirection.RIGHT);
            for (int y = ll.y; y <= ur.y; y++) {
                for (int x = ll.x; x < ur.x; x++) {
                    Vector2d previous = animal.getPosition();
                    animal.move(MoveDirection.FORWARD);
                    assertEquals(previous.add(new Vector2d((y - ll.y) % 2 == 0 ? 1 : -1, 0)), animal.getPosition());
                }
                if (y == ur.y) {
                } else if ((y - ll.y) % 2 == 0) {
                    Vector2d previous = animal.getPosition();
                    animal.move(MoveDirection.LEFT);
                    animal.move(MoveDirection.FORWARD);
                    animal.move(MoveDirection.LEFT);
                    assertEquals(previous.add(new Vector2d(0, 1)), animal.getPosition());
                } else {
                    Vector2d previous = animal.getPosition();
                    animal.move(MoveDirection.RIGHT);
                    animal.move(MoveDirection.FORWARD);
                    animal.move(MoveDirection.RIGHT);
                    assertEquals(previous.add(new Vector2d(0, 1)), animal.getPosition());
                }
            }
            assertEquals((ur.y - ll.y) % 2 == 0 ? MapDirection.EAST : MapDirection.WEST, animal.getOrientation());
            assertEquals((ur.y - ll.y) % 2 == 0 ? ur : new Vector2d(ll.x, ur.y), animal.getPosition());
            // jeżeli zwierzę nie "zjada" trawy, obręby mapy nie powinny ulec zmianie
            assertEquals(ll, grassField.lowerLeft());
            assertEquals(ur, grassField.upperRight());
        }
    }
}

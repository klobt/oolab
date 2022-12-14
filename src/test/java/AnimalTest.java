import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnimalTest {
    @Test
    public void integrationTest() {
        assertAnimal(new String[]{}, new Vector2d(2, 2), MapDirection.NORTH);
        assertAnimal(new String[]{"r", "f", "f", "f"}, new Vector2d(4, 2), MapDirection.EAST);
        assertThrows(IllegalArgumentException.class, () -> assertAnimal(new String[]{"R", "F", "F", "F"}, new Vector2d(2, 2), MapDirection.NORTH));
        assertThrows(IllegalArgumentException.class, () ->
                assertAnimal(
                        new String[]{"r", "x", "f", "co", "ja", "robię", "?", "f", "aaaaaaaaaa", "f"},
                        new Vector2d(4, 2),
                        MapDirection.EAST
                )
        );
        assertAnimal(
                new String[]{"f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "f", "b", "b", "b", "b", "r", "r"},
                new Vector2d(2, 0),
                MapDirection.SOUTH
        );
    }

    private void assertAnimal(String[] input, Vector2d endPosition, MapDirection endOrientation) {
        IWorldMap map = new RectangularMap(5, 5);
        Animal animal = new Animal(map);
        for (MoveDirection direction : new OptionsParser().parse(input)) {
            animal.move(direction);
        }
        assertEquals(animal.getPosition(), endPosition);
        assertEquals(animal.getOrientation(), endOrientation);
    }
}

import agh.ics.oop.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Vector2dTest {
    @Test
    public void testEquals() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x1 = random.nextInt();
            int y1 = random.nextInt();
            int x2 = x1 + random.nextInt();
            int y2 = y1 + random.nextInt();
            assert (new Vector2d(x1, y1).equals(new Vector2d(x1, y1)));
            assertFalse(new Vector2d(x1, y1).equals(new Vector2d(x2, y2)));
        }
    }

    @Test
    public void testToString() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt();
            int y = random.nextInt();
            assertEquals(new Vector2d(x, y).toString(), "(" + x + "," + y + ")");
        }
    }

    @Test
    public void testPrecedes() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x1 = random.nextInt();
            int y1 = random.nextInt();
            int x2 = x1 + Math.abs(random.nextInt()) % 1000 + 1;
            int y2 = y1 + Math.abs(random.nextInt()) % 1000 + 1;
            assert(new Vector2d(x1, y1).precedes(new Vector2d(x2, y2)));
        }
    }

    @Test
    public void testFollows() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x1 = random.nextInt();
            int y1 = random.nextInt();
            int x2 = x1 - (Math.abs(random.nextInt()) % 1000) - 1;
            int y2 = y1 - (Math.abs(random.nextInt()) % 1000) - 1;
            assert(new Vector2d(x1, y1).follows(new Vector2d(x2, y2)));
        }
    }

    @Test
    public void testUpperRight() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x1 = random.nextInt();
            int y1 = random.nextInt();
            int x2 = random.nextInt();
            int y2 = random.nextInt();
            int xMax = Math.max(x1, x2);
            int yMax = Math.max(y1, y2);
            assertEquals(new Vector2d(x1, y1).upperRight(new Vector2d(x2, y2)), new Vector2d(xMax, yMax));
        }
    }

    @Test
    public void testLowerLeft() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x1 = random.nextInt();
            int y1 = random.nextInt();
            int x2 = random.nextInt();
            int y2 = random.nextInt();
            int xMin = Math.min(x1, x2);
            int yMin = Math.min(y1, y2);
            assertEquals(new Vector2d(x1, y1).lowerLeft(new Vector2d(x2, y2)), new Vector2d(xMin, yMin));
        }
    }

    @Test
    public void testAdd() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x1 = random.nextInt();
            int y1 = random.nextInt();
            int x2 = random.nextInt();
            int y2 = random.nextInt();
            assertEquals(new Vector2d(x1, y1).add(new Vector2d(x2, y2)), new Vector2d(x1 + x2, y1 + y2));
        }
    }

    @Test
    public void testSubtract() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x1 = random.nextInt();
            int y1 = random.nextInt();
            int x2 = random.nextInt();
            int y2 = random.nextInt();
            assertEquals(new Vector2d(x1, y1).substract(new Vector2d(x2, y2)), new Vector2d(x1 - x2, y1 - y2));
        }
    }

    @Test
    public void testOpposite() {
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt();
            int y = random.nextInt();
            assertEquals(new Vector2d(x, y).opposite(), new Vector2d(-x, -y));
        }
    }
}

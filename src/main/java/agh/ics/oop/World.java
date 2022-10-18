package agh.ics.oop;

import java.util.stream.Stream;

/*
 * REPOZYTORIUM GIT: https://github.com/klobt/oolab
 */

public class World {
    public static void main(String[] arguments) {
        System.out.println("system wystartował");
        Stream<Direction> directionStream = Stream.of(arguments).map(argument -> switch (argument) {
            case "f" -> Direction.FORWARD;
            case "b" -> Direction.BACKWARD;
            case "r" -> Direction.RIGHT;
            case "l" -> Direction.LEFT;
            default -> null;
        });
        run(directionStream);
        System.out.println("system zakończył działanie");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        MapDirection mapDirection = MapDirection.NORTH;
        System.out.println(mapDirection);
        System.out.println(mapDirection.next());
        System.out.println(mapDirection.previous());
        System.out.println(mapDirection.toUnitVector());
    }
    private static void run(Stream<Direction> directionStream) {
        System.out.println("Start");
        directionStream.forEach(direction -> {
            switch (direction) {
                case FORWARD:
                    System.out.println("Zwierzak idzie do przodu");
                    break;
                case BACKWARD:
                    System.out.println("Zwierzak idzie do tyłu");
                    break;
                case RIGHT:
                    System.out.println("Zwierzak skręca w prawo");
                    break;
                case LEFT:
                    System.out.println("Zwierzak skręca w lewo");
                    break;
                default:
                    // ignoruj
            }
        });
        System.out.println("Stop");
    }
}

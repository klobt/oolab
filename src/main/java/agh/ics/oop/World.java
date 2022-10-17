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

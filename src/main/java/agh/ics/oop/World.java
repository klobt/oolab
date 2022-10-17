package agh.ics.oop;

import java.util.Vector;

public class World {
    public static void main(String[] arguments) {
        System.out.println("system wystartował");
        Vector<Direction> directions = new Vector<Direction>();
        for (String argument : arguments) {
            directions.add(switch (argument) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> null;
            });
        }
        run(directions.toArray(Direction[]::new));
        System.out.println("system zakończył działanie");
    }
    private static void run(Direction[] directions) {
        System.out.println("Start");
        for (Direction direction : directions) {
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
        }
        System.out.println("Stop");
    }
}

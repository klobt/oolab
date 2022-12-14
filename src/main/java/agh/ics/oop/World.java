package agh.ics.oop;

import javafx.application.Application;

import java.util.stream.Stream;

import agh.ics.oop.gui.App;

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

        Animal animal = new Animal();
        System.out.println(animal.getPosition());
        animal.move(MoveDirection.RIGHT);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        animal.move(MoveDirection.FORWARD);
        System.out.println(animal);

        for (MoveDirection direction : new OptionsParser().parse(arguments)) {
            animal.move(direction);
        }
        System.out.println(animal);

        MoveDirection[] directions = new OptionsParser().parse(arguments);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);

        IWorldMap grassField = new GrassField(10);
        IEngine grassEngine = new SimulationEngine(directions, grassField, positions);
        grassEngine.run();
        System.out.println(grassField);

        Application.launch(App.class, arguments);
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

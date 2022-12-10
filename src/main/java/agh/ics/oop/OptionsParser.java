package agh.ics.oop;

import java.util.stream.Stream;

public class OptionsParser {
    public MoveDirection[] parse(String[] arguments) {
        return Stream
                .of(arguments)
                .map(argument -> switch (argument) {
                    case "f" -> MoveDirection.FORWARD;
                    case "b" -> MoveDirection.BACKWARD;
                    case "r" -> MoveDirection.RIGHT;
                    case "l" -> MoveDirection.LEFT;
                    default -> throw new IllegalArgumentException(argument + " is not legal move specification");
                })
                .toArray(MoveDirection[]::new);
    }
}

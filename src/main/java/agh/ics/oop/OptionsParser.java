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
                    default -> null;
                })
                .filter(direction -> direction != null)
                .toArray(MoveDirection[]::new);
    }
}

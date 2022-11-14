package agh.ics.oop;

import java.util.stream.Stream;

public class SimulationEngine implements IEngine {
    private Animal[] animals;
    private MoveDirection[] directions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this.animals = Stream.of(initialPositions)
                .map(initialPosition -> new Animal(map, initialPosition))
                .toArray(Animal[]::new);
        this.directions = directions;
    }

    @Override
    public void run() {
        for (int i = 0; i < directions.length; i++) {
            animals[i % animals.length].move(directions[i]);
        }
    }
}

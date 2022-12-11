package agh.ics.oop;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class SimulationEngine implements IEngine {
    private Animal[] animals;
    private MoveDirection[] directions;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions, List<IPositionChangeObserver> positionChangeObservers, List<IOrientationChangeObserver> orientationChangeObservers) {
        this.animals = Stream.of(initialPositions)
                .map(initialPosition -> new Animal(map, initialPosition))
                .peek(animal -> {
                    positionChangeObservers.forEach(animal::addPositionChangeObserver);
                    orientationChangeObservers.forEach(animal::addOrientationChangeObserver);
                })
                .toArray(Animal[]::new);
        this.directions = directions;
    }

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this(directions, map, initialPositions, Collections.emptyList(), Collections.emptyList());
    }

        @Override
    public void run() {
        for (int i = 0; i < directions.length; i++) {
            animals[i % animals.length].move(directions[i]);
        }
    }
}

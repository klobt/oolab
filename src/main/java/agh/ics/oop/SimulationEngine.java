package agh.ics.oop;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class SimulationEngine implements IEngine, Runnable {
    private Animal[] animals;
    private MoveDirection[] directions;
    long moveDelay;

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions, List<IPositionChangeObserver> positionChangeObservers, List<IOrientationChangeObserver> orientationChangeObservers, long moveDelay) {
        this.animals = Stream.of(initialPositions)
                .map(initialPosition -> new Animal(map, initialPosition))
                .peek(animal -> {
                    positionChangeObservers.forEach(animal::addPositionChangeObserver);
                    orientationChangeObservers.forEach(animal::addOrientationChangeObserver);
                })
                .toArray(Animal[]::new);
        this.directions = directions;
        this.moveDelay = moveDelay;
    }

    public SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this(directions, map, initialPositions, Collections.emptyList(), Collections.emptyList(), 0);
    }

    @Override
    public void run() {
        for (int i = 0; i < directions.length; i++) {
            animals[i % animals.length].move(directions[i]);
            try {
                Thread.sleep(moveDelay);
            } catch (InterruptedException ex) {
                System.out.println("error: " + ex.getMessage());
            }
        }
    }
}

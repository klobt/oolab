import agh.ics.oop.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ExpectedResult {
    public Vector2d[] endPositions;
    public MapDirection[] endOrientations;
    public int length;

    public ExpectedResult(Vector2d[] endPositions, MapDirection[] endOrientations) {
        this.endPositions = endPositions;
        this.endOrientations = endOrientations;
        this.length = Math.max(endPositions.length, endOrientations.length);
    }
}

class EngineWithMap {
    public IEngine engine;
    public IWorldMap map;

    public EngineWithMap(MoveDirection[] directions, IWorldMap map, Vector2d[] initialPositions) {
        this.engine = new SimulationEngine(directions, map, initialPositions);
        this.map = map;
    }
}

public class Lab4Test {
    @Test
    public void integrationTest() {
        assertSimulation(
                new EngineWithMap(
                        new OptionsParser().parse(new String[] {
                                "f", "b",
                                "r", "l",
                                "f", "f",
                                "r", "r",
                                "f", "f",
                                "f", "f",
                                "f", "f",
                                "f", "f"
                        }),
                        new RectangularMap(10, 5),
                        new Vector2d[] { new Vector2d(2,2), new Vector2d(3,4) }
                ),
                new ExpectedResult(
                        new Vector2d[] { new Vector2d(2, 0), new Vector2d(3, 4) },
                        new MapDirection[] { MapDirection.SOUTH, MapDirection.NORTH }
                )
        );
        assertSimulation(
                new EngineWithMap(
                        new OptionsParser().parse(new String[] {
                                "r", "l", "r",
                                "f", "f", "f",
                                "f", "f", "f",
                                "f", "f", "f",
                                "f", "f", "f",
                                "f", "f", "f",
                                "f", "f", "f",
                                "f", "f", "f",
                                "f", "f", "f",
                                "f", "f", "f",
                                "f", "f", "f",
                        }),
                        new RectangularMap(100, 1),
                        new Vector2d[] {
                                new Vector2d(25,0),
                                new Vector2d(30,0),
                                new Vector2d(95,0),
                        }
                ),
                new ExpectedResult(
                        new Vector2d[] {
                                new Vector2d(27,0),
                                new Vector2d(28,0),
                                new Vector2d(99,0),
                        },
                        new MapDirection[] {
                                MapDirection.EAST,
                                MapDirection.WEST,
                                MapDirection.EAST,
                        }
                )
        );
        assertSimulation(
                new EngineWithMap(
                        new OptionsParser().parse(new String[] {
                                "l", "l", "l", "l", "l",
                                "l", "l", "l", "l", "l",
                                "l", "l", "l", "l", "l",
                                "l", "l", "l", "r", "r",
                                "f", "f", "b", "f", "f",
                        }),
                        new RectangularMap(100, 100),
                        new Vector2d[] {
                                new Vector2d(10,10),
                                new Vector2d(20,20),
                                new Vector2d(30,30),
                                new Vector2d(40,40),
                                new Vector2d(50,50),
                        }
                ),
                new ExpectedResult(
                        new Vector2d[] {
                                new Vector2d(10,11),
                                new Vector2d(20,21),
                                new Vector2d(30,29),
                                new Vector2d(40,39),
                                new Vector2d(50,49),
                        },
                        new MapDirection[] {
                                MapDirection.NORTH,
                                MapDirection.NORTH,
                                MapDirection.NORTH,
                                MapDirection.SOUTH,
                                MapDirection.SOUTH,
                        }
                )
        );
    }

    private void assertSimulation(EngineWithMap engineWithMap, ExpectedResult expectedResult) {
        engineWithMap.engine.run();
        for (int i = 0; i < expectedResult.length; i++) {
            Animal animal = (Animal) engineWithMap.map.objectAt(expectedResult.endPositions[i]);
            assertNotEquals(animal, null);
            assertEquals(animal.getOrientation(), expectedResult.endOrientations[i]);
        }
    }
}

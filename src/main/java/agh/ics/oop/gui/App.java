package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    MoveDirection[] directions;
    AbstractWorldMap map;
    @Override
    public void init() {
        directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        gridPane.add(new Label("y/x"), 0, 0);

        for (int x = 0; x < map.upperRight().x - map.lowerLeft().x; x++) {
            gridPane.add(new Label("" + x), x + 1, 0);
        }

        for (int y = 0; y < map.upperRight().y - map.lowerLeft().y; y++) {
            gridPane.add(new Label("" + y), 0, y + 1);
        }

        for (int y = 0; y < map.upperRight().y - map.lowerLeft().y; y++) {
            for (int x = 0; x < map.upperRight().x - map.lowerLeft().x; x++) {
                Vector2d position = new Vector2d(x + map.lowerLeft().x, y + map.lowerLeft().y);
                Object object = map.objectAt(position);
                if (object != null) {
                    gridPane.add(new Label(object.toString()), x + 1, y + 1);
                }
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class App extends Application {
    MoveDirection[] directions;
    AbstractWorldMap map;
    @Override
    public void init() {
        directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
    }
    @Override
    public void start(Stage primaryStage) {
        int windowWidth = 400, windowHeight = 400;
        int columnWidth = windowWidth / (map.upperRight().x - map.lowerLeft().x + 1);
        int rowHeight = windowHeight / (map.upperRight().y - map.lowerLeft().y + 1);

        GridPane gridPane = new GridPane();

        gridPane.getColumnConstraints().add(new ColumnConstraints(columnWidth));
        gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
        {
            Label label = new Label("y/x");
            gridPane.add(label, 0, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int x = 0; x < map.upperRight().x - map.lowerLeft().x; x++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(columnWidth));
            Label label = new Label("" + x);
            gridPane.add(label, x + 1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int y = 0; y < map.upperRight().y - map.lowerLeft().y; y++) {
            gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
            Label label = new Label("" + y);
            gridPane.add(label, 0, y + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int y = 0; y < map.upperRight().y - map.lowerLeft().y; y++) {
            for (int x = 0; x < map.upperRight().x - map.lowerLeft().x; x++) {
                Vector2d position = new Vector2d(x + map.lowerLeft().x, y + map.lowerLeft().y);
                Object object = map.objectAt(position);
                if (object != null) {
                    Label label = new Label(object.toString());
                    gridPane.add(label, x + 1, y + 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

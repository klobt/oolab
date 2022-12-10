package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {
    MoveDirection[] directions;
    IWorldMap map;
    @Override
    public void init() {
        directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        map = new RectangularMap(10, 5);
    }
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();

        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++) {
                Label label = new Label("Zwierzak");
                gridPane.add(label, x, y);
            }
        }

        Scene scene = new Scene(gridPane, 400, 400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

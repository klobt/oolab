package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Collections;

public class App extends Application implements IPositionChangeObserver, IOrientationChangeObserver{
    AbstractWorldMap map;
    GridPane gridPane = new GridPane();
    SimulationEngine engine;

    @Override
    public void init() {
        map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        engine = new SimulationEngine(
                map,
                positions,
                Collections.singletonList(this),
                Collections.singletonList(this),
                300
        );
    }
    @Override
    public void start(Stage primaryStage) {
        gridPane.setGridLinesVisible(true);
        updateGrid();

        HBox hbox = new HBox();

        TextField textField = new TextField();
        textField.setMinWidth(300);
        textField.setMinHeight(50);
        Button startButton = new Button();
        startButton.setText("Start");
        startButton.setMinWidth(100);
        startButton.setMinHeight(50);
        startButton.setOnAction((ActionEvent event) -> {
            engine.setDirections(new OptionsParser().parse(textField.getText().split("\\s+")));
            hbox.setDisable(true);
            new Thread(() -> {
                engine.run();
                hbox.setDisable(false);
            }).start();
        });

        hbox.getChildren().add(textField);
        hbox.getChildren().add(startButton);

        VBox vbox = new VBox();

        vbox.getChildren().add(gridPane);
        vbox.getChildren().add(hbox);

        Scene scene = new Scene(vbox, 400, 500);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateGrid() {
        gridPane.getChildren().clear();

        int windowWidth = 400, windowHeight = 400;
        int columnWidth = windowWidth / (map.upperRight().x - map.lowerLeft().x + 1);
        int rowHeight = windowHeight / (map.upperRight().y - map.lowerLeft().y + 1);

        gridPane.getColumnConstraints().clear();
        gridPane.getColumnConstraints().add(new ColumnConstraints(columnWidth));
        gridPane.getRowConstraints().clear();
        gridPane.getRowConstraints().add(new RowConstraints(rowHeight));

        {
            Label label = new Label("y/x");
            gridPane.add(label, 0, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int x = 0; x <= map.upperRight().x - map.lowerLeft().x; x++) {
            int positionX = x + map.lowerLeft().x;
            gridPane.getColumnConstraints().add(new ColumnConstraints(columnWidth));
            Label label = new Label("" + positionX);
            gridPane.add(label, x + 1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int y = 0; y <= map.upperRight().y - map.lowerLeft().y; y++) {
            int positionY = y + map.lowerLeft().y;
            gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
            Label label = new Label("" + positionY);
            gridPane.add(label, 0, map.upperRight().y - map.lowerLeft().y - y + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int y = 0; y <= map.upperRight().y - map.lowerLeft().y; y++) {
            for (int x = 0; x <= map.upperRight().x - map.lowerLeft().x; x++) {
                Vector2d position = new Vector2d(x + map.lowerLeft().x, y + map.lowerLeft().y);
                Object object = map.objectAt(position);
                if (object != null) {
                    GuiElementBox elementBox = new GuiElementBox((IMapElement) object);
                    gridPane.add(elementBox, x + 1, map.upperRight().y - map.lowerLeft().y - y + 1);
                    GridPane.setHalignment(elementBox, HPos.CENTER);
                }
            }
        }
    }

    @Override
    public void orientationChanged(MapDirection oldOrientation, MapDirection newOrientation) {
        Platform.runLater(this::updateGrid);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, int zIndex) {
        Platform.runLater(this::updateGrid);
    }
}

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
    Vector2d ll, ur;
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

        ll = map.lowerLeft();
        ur = map.upperRight();

        int windowWidth = 400, windowHeight = 400;
        int columnWidth = windowWidth / (ur.x - ll.x + 1);
        int rowHeight = windowHeight / (ur.y - ll.y + 1);

        gridPane.getColumnConstraints().clear();
        gridPane.getColumnConstraints().add(new ColumnConstraints(columnWidth));
        gridPane.getRowConstraints().clear();
        gridPane.getRowConstraints().add(new RowConstraints(rowHeight));

        {
            Label label = new Label("y/x");
            gridPane.add(label, 0, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int x = 0; x <= ur.x - ll.x; x++) {
            int positionX = x + ll.x;
            gridPane.getColumnConstraints().add(new ColumnConstraints(columnWidth));
            Label label = new Label("" + positionX);
            gridPane.add(label, x + 1, 0);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int y = 0; y <= ur.y - ll.y; y++) {
            int positionY = y + ll.y;
            gridPane.getRowConstraints().add(new RowConstraints(rowHeight));
            Label label = new Label("" + positionY);
            gridPane.add(label, 0, ur.y - ll.y - y + 1);
            GridPane.setHalignment(label, HPos.CENTER);
        }

        for (int y = 0; y <= ur.y - ll.y; y++) {
            for (int x = 0; x <= ur.x - ll.x; x++) {
                Vector2d position = new Vector2d(x + ll.x, y + ll.y);
                Object object = map.objectAt(position);
                if (object != null) {
                    GuiElementBox elementBox = new GuiElementBox((IMapElement) object);
                    gridPane.add(elementBox, x + 1, ur.y - ll.y - y + 1);
                    GridPane.setHalignment(elementBox, HPos.CENTER);
                }
            }
        }
    }

    private void updateGridAtPoint(Vector2d position) {
        if (map.lowerLeft() != ll || map.upperRight() != ur) {
            updateGrid();
        } else {
            Object object = map.objectAt(position);
            if (object != null) {
                int x = position.x - ll.x, y = position.y - ll.y;
                GuiElementBox elementBox = new GuiElementBox((IMapElement) object);
                gridPane.add(elementBox, x + 1, ur.y - ll.y - y + 1);
                GridPane.setHalignment(elementBox, HPos.CENTER);
            }
        }
    }

    @Override
    public void orientationChanged(Vector2d position, MapDirection oldOrientation, MapDirection newOrientation) {
        Platform.runLater(() -> updateGridAtPoint(position));
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition, int zIndex) {
        Platform.runLater(() -> updateGridAtPoint(newPosition));
    }
}

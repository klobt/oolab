package agh.ics.oop.gui;

import agh.ics.oop.IMapElement;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.*;
import javafx.scene.layout.VBox;

public class GuiElementBox extends Parent {
    public GuiElementBox(IMapElement mapElement) {
        ImageView imageView = new ImageView(new Image(mapElement.getImagePath()));
        imageView.setFitWidth(20);
        imageView.setFitHeight(20);
        VBox vbox = new VBox();
        vbox.getChildren().add(imageView);
        vbox.getChildren().add(new Label(mapElement.getLabelString()));
        vbox.setAlignment(Pos.CENTER);
        this.getChildren().add(vbox);
    }
}

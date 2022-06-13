package grapher;

import GUI.CanvasTest;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane anchorPane = new AnchorPane();
        Scene scene = new Scene(anchorPane);
        anchorPane.setStyle("-fx-background-color: #fcf1e6");

        Graf graf = new Graf(10,20,0.8,0.0,10.0);
        CanvasTest c = new CanvasTest(graf,1);
        //graf.wypiszGraf();

        anchorPane.getChildren().add(c.getCanvas());

        stage.setScene(scene);
        stage.show();
    }
}

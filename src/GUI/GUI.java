package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


public class GUI {
    private Scene guiScene;
    ScrollPane widokGrafu;
    GridPane siatkaWierzcholkow;
    private final static double SCENE_WIDTH = 900;
    private final static double SCENE_HEIGHT = 640;

    public GUI(){
        initializeScene();
    }

    private void initializeScene(){
        Pane root = new Pane();
        //root.setPadding(new Insets(10, 10, 10, 10));

        guiScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        createGenerujButton(root);






    }


    private Button createGenerujButton(Pane root){
        Button generujButton = new Button("Generuj");
        generujButton.setFont(new Font(12));
        generujButton.setAlignment(Pos.CENTER);
        generujButton.setPrefWidth(140);
        generujButton.setPrefHeight(30);
        generujButton.setLayoutX(130);
        generujButton.setLayoutY(35);
        root.getChildren().add(generujButton);

        ActionEventClass actionEvent = new ActionEventClass();
        actionEvent.setOnActionGenerujButton(generujButton);

        return generujButton;
    }
    public Scene getGuiScene(){
        return guiScene;
    }
}

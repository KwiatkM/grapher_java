package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class GUI {
    private Scene guiScene;
    private TextField wymiarXTextField;
    private TextField wymiarYTextField;
    private TextField wagaOdTextField;
    private TextField wagaDoTextField;
    private TextField szansaNaKrawedzTextField;

    ScrollPane widokGrafu;          //jeszcze nie uzywane
    GridPane siatkaWierzcholkow;        //jeszcze nie uzywane

    private final static double SCENE_WIDTH = 950;
    private final static double SCENE_HEIGHT = 700;

    public GUI(){
        initializeScene();
    }

    private void initializeScene(){
        Pane root = new Pane();

        guiScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        createWymiarXLabel(root);
        createWymiarXTextField(root);

        createWymiarYLabel(root);
        createWymiarYTextField(root);

        createWagaOdLabel(root);
        createWagaOdTextField(root);

        createWagaDoLabel(root);
        createWagaDoTextField(root);

        createSzansaNaKrawedzLabel(root);
        createSzansaNaKrawedzTextField(root);


        //...



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
        actionEvent.setOnActionGenerujButton(generujButton, wymiarXTextField, wymiarYTextField, wagaOdTextField, wagaDoTextField, szansaNaKrawedzTextField);

        return generujButton;
    }
    private Label createWymiarXLabel(Pane root){
        Label wymiarXLabel = new Label("Wymiar x");
        wymiarXLabel.setFont(new Font(12));
        wymiarXLabel.setAlignment(Pos.CENTER);
        wymiarXLabel.setPrefWidth(50);
        wymiarXLabel.setPrefHeight(25);
        wymiarXLabel.setLayoutX(10);
        wymiarXLabel.setLayoutY(90);
        root.getChildren().add(wymiarXLabel);

        return wymiarXLabel;
    }
    private TextField createWymiarXTextField(Pane root){
        wymiarXTextField = new TextField();
        wymiarXTextField.setFont(new Font(12));
        wymiarXTextField.setAlignment(Pos.CENTER);
        wymiarXTextField.setPrefWidth(185);
        wymiarXTextField.setPrefHeight(25);
        wymiarXTextField.setLayoutX(130);
        wymiarXTextField.setLayoutY(90);
        root.getChildren().add(wymiarXTextField);

        return wymiarXTextField;
    }
    private Label createWymiarYLabel(Pane root){
        Label wymiarYLabel = new Label("Wymiar y");
        wymiarYLabel.setFont(new Font(12));
        wymiarYLabel.setAlignment(Pos.CENTER);
        wymiarYLabel.setPrefWidth(50);
        wymiarYLabel.setPrefHeight(25);
        wymiarYLabel.setLayoutX(10);
        wymiarYLabel.setLayoutY(130);
        root.getChildren().add(wymiarYLabel);

        return wymiarYLabel;
    }
    private TextField createWymiarYTextField(Pane root){
        wymiarYTextField = new TextField();
        wymiarYTextField.setFont(new Font(12));
        wymiarYTextField.setAlignment(Pos.CENTER);
        wymiarYTextField.setPrefWidth(185);
        wymiarYTextField.setPrefHeight(25);
        wymiarYTextField.setLayoutX(130);
        wymiarYTextField.setLayoutY(130);
        root.getChildren().add(wymiarYTextField);

        return wymiarYTextField;
    }
    private Label createWagaOdLabel(Pane root){
        Label wagaOdLabel = new Label("Waga od");
        wagaOdLabel.setFont(new Font(12));
        wagaOdLabel.setAlignment(Pos.CENTER);
        wagaOdLabel.setPrefWidth(50);
        wagaOdLabel.setPrefHeight(25);
        wagaOdLabel.setLayoutX(10);
        wagaOdLabel.setLayoutY(170);
        root.getChildren().add(wagaOdLabel);
        
        return wagaOdLabel;
    }
    private TextField createWagaOdTextField(Pane root){
        wagaOdTextField = new TextField();
        wagaOdTextField.setFont(new Font(12));
        wagaOdTextField.setAlignment(Pos.CENTER);
        wagaOdTextField.setPrefWidth(185);
        wagaOdTextField.setPrefHeight(25);
        wagaOdTextField.setLayoutX(130);
        wagaOdTextField.setLayoutY(170);
        root.getChildren().add(wagaOdTextField);

        return wagaOdTextField;
    }
    
    private Label createWagaDoLabel(Pane root){
        Label wagaDoLabel = new Label("Waga do");
        wagaDoLabel.setFont(new Font(12));
        wagaDoLabel.setAlignment(Pos.CENTER);
        wagaDoLabel.setPrefWidth(50);
        wagaDoLabel.setPrefHeight(25);
        wagaDoLabel.setLayoutX(10);
        wagaDoLabel.setLayoutY(210);
        root.getChildren().add(wagaDoLabel);

        return wagaDoLabel;
    }
    private TextField createWagaDoTextField(Pane root){
        wagaDoTextField = new TextField();
        wagaDoTextField.setFont(new Font(12));
        wagaDoTextField.setAlignment(Pos.CENTER);
        wagaDoTextField.setPrefWidth(185);
        wagaDoTextField.setPrefHeight(25);
        wagaDoTextField.setLayoutX(130);
        wagaDoTextField.setLayoutY(210);
        root.getChildren().add(wagaDoTextField);

        return wagaDoTextField;
    }
    private Label createSzansaNaKrawedzLabel(Pane root){
        Label szansaNaKrawedzLabel = new Label("Szansa na krawedz");
        szansaNaKrawedzLabel.setFont(new Font(12));
        szansaNaKrawedzLabel.setAlignment(Pos.CENTER);
        szansaNaKrawedzLabel.setPrefWidth(100);
        szansaNaKrawedzLabel.setPrefHeight(25);
        szansaNaKrawedzLabel.setLayoutX(10);
        szansaNaKrawedzLabel.setLayoutY(250);
        root.getChildren().add(szansaNaKrawedzLabel);

        return szansaNaKrawedzLabel;
    }
    private TextField createSzansaNaKrawedzTextField(Pane root){
        szansaNaKrawedzTextField = new TextField();
        szansaNaKrawedzTextField.setFont(new Font(12));
        szansaNaKrawedzTextField.setAlignment(Pos.CENTER);
        szansaNaKrawedzTextField.setPrefWidth(185);
        szansaNaKrawedzTextField.setPrefHeight(25);
        szansaNaKrawedzTextField.setLayoutX(130);
        szansaNaKrawedzTextField.setLayoutY(250);
        root.getChildren().add(szansaNaKrawedzTextField);

        return szansaNaKrawedzTextField;
    }

    public Scene getGuiScene(){
        return guiScene;
    }
}

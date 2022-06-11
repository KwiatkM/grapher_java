package GUI;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.Scene;
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

        createLabels(root);
        createTextFields(root);
        createButtons(root);



        //...



    }

    private void createLabels(Pane root){
        createWymiarXLabel(root);
        createWymiarYLabel(root);
        createWagaOdLabel(root);
        createWagaDoLabel(root);
        createSzansaNaKrawedzLabel(root);
        createDlugoscOstatniejSciezkiLabel(root);
        createCzyGrafSpojnyLabel(root);
        createSkalaKolorowLabel(root);
    }
    private void createTextFields(Pane root){
        createWymiarXTextField(root);
        createWymiarYTextField(root);
        createWagaOdTextField(root);
        createWagaDoTextField(root);
        createSzansaNaKrawedzTextField(root);
        createDlugoscOstatniejSciezkiTextField(root);
        createCzyGrafSpojnyTextField(root);
    }
    private void createButtons(Pane root){
        createZapiszButton(root);
        createWczytajButton(root);
        createWymazSciezkiButton(root);
        createUsunKrawedzieButton(root);
        createWyjdzButton(root);

        createGenerujButton(root);
    }

    private Button createGenerujButton(Pane root){
        Button button = new Button("Generuj");
        button.setFont(new Font(12));
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(140);
        button.setPrefHeight(30);
        button.setLayoutX(130);
        button.setLayoutY(35);
        root.getChildren().add(button);

        ActionEventClass actionEvent = new ActionEventClass();
        actionEvent.setOnActionGenerujButton(button, wymiarXTextField, wymiarYTextField, wagaOdTextField, wagaDoTextField, szansaNaKrawedzTextField);

        return button;
    }
    private Label createWymiarXLabel(Pane root){
        Label label = new Label("Wymiar x");
        label.setFont(new Font(12));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(50);
        label.setPrefHeight(25);
        label.setLayoutX(10);
        label.setLayoutY(90);
        root.getChildren().add(label);

        return label;
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
        Label label = new Label("Wymiar y");
        label.setFont(new Font(12));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(50);
        label.setPrefHeight(25);
        label.setLayoutX(10);
        label.setLayoutY(130);
        root.getChildren().add(label);

        return label;
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
        Label label = new Label("Waga od");
        label.setFont(new Font(12));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(50);
        label.setPrefHeight(25);
        label.setLayoutX(10);
        label.setLayoutY(170);
        root.getChildren().add(label);
        
        return label;
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
        Label label = new Label("Waga do");
        label.setFont(new Font(12));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(50);
        label.setPrefHeight(25);
        label.setLayoutX(10);
        label.setLayoutY(210);
        root.getChildren().add(label);

        return label;
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
        Label label = new Label("Szansa na krawedz");
        label.setFont(new Font(12));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(100);
        label.setPrefHeight(25);
        label.setLayoutX(10);
        label.setLayoutY(250);
        root.getChildren().add(label);

        return label;
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
    private Button createZapiszButton(Pane root){
        Button button = new Button("Zapisz");
        button.setFont(new Font(12));
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(140);
        button.setPrefHeight(30);
        button.setLayoutX(20);
        button.setLayoutY(310);
        root.getChildren().add(button);

        ActionEventClass actionEvent = new ActionEventClass();
        actionEvent.setOnActionZapiszButton(button);

        return button;
    }
    private Button createWczytajButton(Pane root){
        Button button = new Button("Wczytaj");
        button.setFont(new Font(12));
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(140);
        button.setPrefHeight(30);
        button.setLayoutX(20);
        button.setLayoutY(360);
        root.getChildren().add(button);

        ActionEventClass actionEvent = new ActionEventClass();
        actionEvent.setOnActionWczytajButton(button);

        return button;
    }
    private Button createWymazSciezkiButton(Pane root){
        Button button = new Button("Wymaz sciezki");
        button.setFont(new Font(12));
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(140);
        button.setPrefHeight(30);
        button.setLayoutX(180);
        button.setLayoutY(310);
        root.getChildren().add(button);

        ActionEventClass actionEvent = new ActionEventClass();
        actionEvent.setOnActionWymazSciezkiButton(button);

        return button;
    }
    private Button createUsunKrawedzieButton(Pane root){
        Button button = new Button("Usun krawedzie sciezki");
        button.setFont(new Font(12));
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(140);
        button.setPrefHeight(30);
        button.setLayoutX(180);
        button.setLayoutY(360);
        root.getChildren().add(button);

        ActionEventClass actionEvent = new ActionEventClass();
        actionEvent.setOnActionUsunKrawedzieButton(button);

        return button;
    }
    private Label createDlugoscOstatniejSciezkiLabel(Pane root){
        Label label = new Label("Dlugosc ostatniej sziezki");
        label.setFont(new Font(12));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(140);
        label.setPrefHeight(25);
        label.setLayoutX(20);
        label.setLayoutY(410);
        root.getChildren().add(label);

        return label;
    }
    private TextField createDlugoscOstatniejSciezkiTextField(Pane root){
        TextField textField = new TextField();
        textField.setFont(new Font(12));
        textField.setAlignment(Pos.CENTER);
        textField.setPrefWidth(140);
        textField.setPrefHeight(25);
        textField.setLayoutX(180);
        textField.setLayoutY(410);
        textField.setEditable(false);
        root.getChildren().add(textField);

        return textField;
    }
    private Label createCzyGrafSpojnyLabel(Pane root){
        Label label = new Label("Czy graf spojny");
        label.setFont(new Font(12));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(140);
        label.setPrefHeight(25);
        label.setLayoutX(20);
        label.setLayoutY(450);
        root.getChildren().add(label);

        return label;
    }
    private TextField createCzyGrafSpojnyTextField(Pane root){
        TextField textField = new TextField();
        textField.setFont(new Font(12));
        textField.setAlignment(Pos.CENTER);
        textField.setPrefWidth(140);
        textField.setPrefHeight(25);
        textField.setLayoutX(180);
        textField.setLayoutY(450);
        textField.setEditable(false);
        root.getChildren().add(textField);

        return textField;
    }
    private Label createSkalaKolorowLabel(Pane root){
        Label label = new Label("Skala kolorow");
        label.setFont(new Font(12));
        label.setAlignment(Pos.CENTER);
        label.setPrefWidth(80);
        label.setPrefHeight(25);
        label.setLayoutX(130);
        label.setLayoutY(500);
        root.getChildren().add(label);

        return label;
    }

    //SKALA KOLOROW

    private Button createWyjdzButton(Pane root){
        Button button = new Button("Wyjdz");
        button.setFont(new Font(12));
        button.setAlignment(Pos.CENTER);
        button.setPrefWidth(140);
        button.setPrefHeight(30);
        button.setLayoutX(20);
        button.setLayoutY(630);
        root.getChildren().add(button);

        ActionEventClass actionEvent = new ActionEventClass();
        actionEvent.setOnActionWyjdzButton(button);

        return button;
    }

    public Scene getGuiScene(){
        return guiScene;
    }
}

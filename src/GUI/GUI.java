package GUI;

import grapher.Graf;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GUI {
    private static Scene guiScene;
    private TextField wymiarXTextField;
    private TextField wymiarYTextField;
    private TextField wagaOdTextField;
    private TextField wagaDoTextField;
    private TextField szansaNaKrawedzTextField;

    private Canvas skalaKolorow;
    static private TextField czyGrafSpojnyTextField;
    static private TextField dlugoscSciezkiTextField;


    private static Pane root;

    private static ScrollPane widokGrafu;              // do siatki grafu
    private static GridPane siatkaWierzcholkow;        // do siatki grafu

    private final static double SCENE_WIDTH = 950;
    private final static double SCENE_HEIGHT = 700;

    public GUI(){
        initializeScene();
    }

    private void initializeScene(){
        root = new Pane();

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
        createSkalaKolorow(root);

        createGenerujButton(root);
    }

    static CanvasGraf initializeWidokGrafu( Graf graf){             //do siatki grafu(uruchamiane w ActionEventClass
                                                                     //po wygenerowaniu lub wczytaniu grafu)
        widokGrafu = initializeScrollPane();
        double skala = 1.0;
        if(graf.getWymiarX() > 16|| graf.getWymiarY() > 16) skala = 0.8;
        if(graf.getWymiarX() > 30|| graf.getWymiarY() > 30) skala = 0.5;
        if(graf.getWymiarX() > 40|| graf.getWymiarY() > 40) skala = 0.45;
        if(graf.getWymiarX() > 50|| graf.getWymiarY() > 50) skala = 0.35;
        if(graf.getWymiarX() > 60|| graf.getWymiarY() > 60) skala = 0.3;
        if(graf.getWymiarX() > 70|| graf.getWymiarY() > 70) skala = 0.35;
        if(graf.getWymiarX() > 99|| graf.getWymiarY() > 99) skala = 0.2;
        CanvasGraf c = new CanvasGraf(graf,skala);
        widokGrafu.setContent(c.getCanvas());
        root.getChildren().add(widokGrafu);
        Main.stage.setScene(guiScene);
        return c;
    }
    private static ScrollPane initializeScrollPane(){      //do siatki grafu
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(590);
        scrollPane.setPrefHeight(670);
        scrollPane.setLayoutX(340);
        scrollPane.setLayoutY(20);



        return scrollPane;
    }
    private static void initializeSiatkaWierzcholkow(){
        siatkaWierzcholkow.getChildren().clear();

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
        actionEvent.setOnActionGenerujButton(button, wymiarXTextField, wymiarYTextField, wagaOdTextField, wagaDoTextField, szansaNaKrawedzTextField, czyGrafSpojnyTextField);

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
        wymiarXTextField = new TextField("20");
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
        wymiarYTextField = new TextField("20");
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
        wagaOdTextField = new TextField("0");
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
        wagaDoTextField = new TextField("10");
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
        szansaNaKrawedzTextField = new TextField("1.0");
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
        actionEvent.setOnActionWczytajButton(button, czyGrafSpojnyTextField);

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
        dlugoscSciezkiTextField = new TextField();
        dlugoscSciezkiTextField.setFont(new Font(12));
        dlugoscSciezkiTextField.setAlignment(Pos.CENTER);
        dlugoscSciezkiTextField.setPrefWidth(140);
        dlugoscSciezkiTextField.setPrefHeight(25);
        dlugoscSciezkiTextField.setLayoutX(180);
        dlugoscSciezkiTextField.setLayoutY(410);
        dlugoscSciezkiTextField.setEditable(false);
        root.getChildren().add(dlugoscSciezkiTextField);

        return dlugoscSciezkiTextField;
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
        czyGrafSpojnyTextField = new TextField();
        czyGrafSpojnyTextField.setFont(new Font(12));
        czyGrafSpojnyTextField.setAlignment(Pos.CENTER);
        czyGrafSpojnyTextField.setPrefWidth(140);
        czyGrafSpojnyTextField.setPrefHeight(25);
        czyGrafSpojnyTextField.setLayoutX(180);
        czyGrafSpojnyTextField.setLayoutY(450);
        czyGrafSpojnyTextField.setEditable(false);
        root.getChildren().add(czyGrafSpojnyTextField);

        return czyGrafSpojnyTextField;
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

    private void createSkalaKolorow(Pane root){
        int wysokosc = 20;
        int szerokosc = 260;
        skalaKolorow = new Canvas(szerokosc,wysokosc);
        skalaKolorow.setLayoutX(40);
        skalaKolorow.setLayoutY(530);
        GraphicsContext gc = skalaKolorow.getGraphicsContext2D();

        for(int i = 0; i < szerokosc;i++){
            gc.setStroke(kolorDoSkali(i,szerokosc));
            gc.strokeLine(i,0,i,wysokosc);
        }




        root.getChildren().add(skalaKolorow);
    }

    private Color kolorDoSkali (double i, double max){
        final double BLUE_HUE = Color.BLUE.getHue();
        final double Red_HUE = Color.RED.getHue();
        double hue = BLUE_HUE + (Red_HUE - BLUE_HUE) * i/ max ;
        return Color.hsb(hue, 1.0, 1.0);
    }

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

    static void setCzyGrafSpojny(String txt){
        czyGrafSpojnyTextField.setText(txt);
    }

    static void setDlugoscSciezki(double d){
        dlugoscSciezkiTextField.setText("" + d);
    }

    public Scene getGuiScene(){
        return guiScene;
    }
}

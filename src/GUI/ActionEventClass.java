package GUI;

import grapher.BFS;
import grapher.Graf;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ActionEventClass {
    static Graf graf;
    static CanvasGraf canvas;
    static BFS bfs;
    static boolean czyWygenerowanyLubWczytanyGraf = false;
    public void setOnActionGenerujButton(Button generujButton, TextField wymiarXTextField, TextField wymiarYTextField, TextField wagaOdTextField, TextField wagaDoTextField, TextField szansaNaKrawedzTextField, TextField czyGrafSpojnyTextField, TextField dlugoscSciezkiTextField){
        generujButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                graf = new Graf();

                try {
                    if(Integer.parseInt(wymiarXTextField.getText()) <= 0){
                        graf.setWymiarX(100);
                    } else {
                        graf.setWymiarX(Integer.parseInt(wymiarXTextField.getText()));
                    }
                } catch (NumberFormatException ex){
                    graf.setWymiarX(100);
                }

                try {
                    if(Integer.parseInt(wymiarYTextField.getText()) <= 0){
                        graf.setWymiarY(100);
                    } else {
                        graf.setWymiarY(Integer.parseInt(wymiarYTextField.getText()));
                    }
                } catch (NumberFormatException ex){
                    graf.setWymiarY(100);
                }

                try {
                    if(Double.parseDouble(wagaOdTextField.getText()) < 0 || Double.parseDouble(wagaOdTextField.getText()) > 100){
                        graf.setWagaOd(0);
                    } else {
                        graf.setWagaOd(Double.parseDouble(wagaOdTextField.getText()));
                    }
                } catch (NumberFormatException ex){
                    graf.setWagaOd(0);
                }

                try {
                    if(Double.parseDouble(wagaDoTextField.getText()) < 0 || Double.parseDouble(wagaDoTextField.getText()) > 100){
                        graf.setWagaDo(100);
                    } else {
                        graf.setWagaDo(Double.parseDouble(wagaDoTextField.getText()));
                    }
                } catch (NumberFormatException ex){
                    graf.setWagaDo(100);
                }

                try {
                    if(Double.parseDouble(szansaNaKrawedzTextField.getText()) < 0 || Double.parseDouble(szansaNaKrawedzTextField.getText()) > 1){
                        graf.setSzansaNaKrawedz(1);
                    } else {
                        graf.setSzansaNaKrawedz(Double.parseDouble(szansaNaKrawedzTextField.getText()));
                    }
                } catch (NumberFormatException ex){
                    graf.setSzansaNaKrawedz(1);
                }

                if(graf.getWagaOd() > graf.getWagaDo()){
                    double tmp = graf.getWagaOd();
                    graf.setWagaOd(graf.getWagaDo());
                    graf.setWagaDo(tmp);
                }

                graf.generujGraf();
                canvas = GUI.initializeWidokGrafu(graf);             //do siatki grafu
                czyWygenerowanyLubWczytanyGraf = true;
                bfs = new BFS(graf);
                wyswietlSpojnoscGrafu(czyGrafSpojnyTextField);
            }
        });
    }
    public void setOnActionZapiszButton(Button zapiszButton){
        zapiszButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                if(czyWygenerowanyLubWczytanyGraf) {
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Zapisywanie jako");
                    fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Dokumenty tekstowe", "*.txt"));

                    String userDirectoryString = System.getProperty("user.dir");

                    File userDirectory = new File(userDirectoryString);
                    if(!userDirectory.canRead()) {
                        userDirectory = new File("c:/");
                    }
                    fileChooser.setInitialDirectory(userDirectory);

                    File file = fileChooser.showOpenDialog(Main.stage);
                    if (file != null) {
                        try {
                            graf.zapiszDoPliku(file.getAbsolutePath());
                            System.out.println("Graf zostal zapisany do pliku " + file.getAbsolutePath());
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Przed zapisaniem grafu nalezy go wygenerowac lub wczytac.");
                    alert.show();
                }
            }
        });
    }
    public void setOnActionWczytajButton(Button wczytajButton, TextField czyGrafSpojnyTextField, TextField dlugoscSciezkiTextField){
        wczytajButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Wczytaj jako");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Dokumenty tekstowe", "*.txt"));

                String userDirectoryString = System.getProperty("user.dir");

                File userDirectory = new File(userDirectoryString);
                if(!userDirectory.canRead()) {
                    userDirectory = new File("c:/");
                }
                fileChooser.setInitialDirectory(userDirectory);

                File file = fileChooser.showOpenDialog(Main.stage);
                if (file != null) {
                    try {
                        graf = new Graf(file.getAbsolutePath());
                        //bfs.start();
                        //GUI.initializeWidokGrafu();                     //do siatki grafu
                        canvas = GUI.initializeWidokGrafu(graf);             //do siatki grafu
                        czyWygenerowanyLubWczytanyGraf = true;
                        bfs = new BFS(graf);
                        wyswietlSpojnoscGrafu(czyGrafSpojnyTextField);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }
    public void setOnActionWymazSciezkiButton(Button wymazSciezkiButton){
        wymazSciezkiButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            canvas.fullRedraw();

            }
        });
    }
    public void setOnActionUsunKrawedzieButton(Button usunKrawedzieButton){
        usunKrawedzieButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            graf.usunSciezke(canvas.getCurrentPath());
            canvas.fullRedraw();
            bfs.start();
            if(bfs.grafSpojny()) GUI.setCzyGrafSpojny("Tak");
            else GUI.setCzyGrafSpojny("Nie");
            }
        });
    }
    public void setOnActionWyjdzButton(Button wyjdzButton){
        wyjdzButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                Platform.exit();
            }
        });
    }

    private void wyswietlSpojnoscGrafu(TextField czyGrafSpojnyTextField){
        bfs.start();
        if(bfs.grafSpojny()){
            czyGrafSpojnyTextField.setText("Tak");
        } else{
            czyGrafSpojnyTextField.setText("Nie");
        }
    }
}

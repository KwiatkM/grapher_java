package GUI;

import grapher.Graf;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ActionEventClass {
    public void setOnActionGenerujButton(Button generujButton, TextField wymiarXTextField, TextField wymiarYTextField, TextField wagaOdTextField, TextField wagaDoTextField, TextField szansaNaKrawedzTextField){
        generujButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("GenerujButtonClicked");
                Graf graf = new Graf();
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
                graf.wypiszGraf();
            }
        });
    }
    public void setOnActionZapiszButton(Button zapiszButton){
        zapiszButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            }
        });
    }
    public void setOnActionWczytajButton(Button wczytajButton){
        wczytajButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            }
        });
    }
    public void setOnActionWymazSciezkiButton(Button wymazSciezkiButton){
        wymazSciezkiButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

            }
        });
    }
    public void setOnActionUsunKrawedzieButton(Button usunKrawedzieButton){
        usunKrawedzieButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {

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
}

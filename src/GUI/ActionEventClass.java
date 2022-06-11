package GUI;

import grapher.Graf;
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
                graf.setWymiarX(Integer.parseInt(wymiarXTextField.getText()));
                graf.setWymiarY(Integer.parseInt(wymiarYTextField.getText()));
                graf.setWagaOd(Double.parseDouble(wagaOdTextField.getText()));
                graf.setWagaDo(Double.parseDouble(wagaDoTextField.getText()));
                graf.setSzansaNaKrawedz(Double.parseDouble(szansaNaKrawedzTextField.getText()));

                graf.setWierzcholki();
                graf.WypiszGraf();
            }
        });
    }
}

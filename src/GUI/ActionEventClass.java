package GUI;

import grapher.Graf;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ActionEventClass {
    public void setOnActionGenerujButton(Button generujButton){
        generujButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                System.out.println("GenerujButtonClicked");
                Graf graf = new Graf(5,5,1,0,10);
                graf.WypiszGraf();
            }
        });
    }
}

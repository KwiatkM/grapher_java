package GUI;

import grapher.Graf;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Aplikacja");
        primaryStage.show();
    }

    public static void main(String[] args){
        System.out.println("grapher_java");
        Graf graf = new Graf(5,5,1,0,10);
        graf.WypiszGraf();

        launch(args);
    }
}

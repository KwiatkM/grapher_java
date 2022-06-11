package GUI;

import grapher.Graf;

import java.io.IOException;

public class Main {

    public static void main( String[] args) throws IOException {
        System.out.println("grapher_java");
        Graf graf = new Graf(5,6,1,0,10);
        //Graf graf1 = new Graf("PlikTestowy");
        //graf.wypiszGraf();
        //graf.zapiszDoPliku("PlikTestowy");
        graf.wypiszGraf();
    }
}

package GUI;

import grapher.Graf;

import java.io.IOException;

public class Main {

    public static void main( String[] args) throws IOException {
        System.out.println("grapher_java");
        Graf graf = new Graf(5,5,1,0,10);
        graf.generujKrawedzie();
        //graf.wypiszGraf();
        //graf.zapiszDoPliku("PlikTestowy");
    }
}

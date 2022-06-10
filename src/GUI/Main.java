package GUI;

import grapher.Graf;

public class Main {

    public static void main( String[] args){
        System.out.println("grapher_java");
        Graf graf = new Graf(5,5,1,0,10);
        graf.WypiszGraf();
    }
}

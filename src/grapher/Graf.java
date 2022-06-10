package grapher;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Graf  {
    private Wierzcholek[] wierzcholki;
    private int wymiarX;
    private int wymiarY;
    private double szansaNaKrawedz;
    private double wagaOd;
    private double wagaDo;

    public Graf(int X, int Y, double szansaNaKrawedz, double wagaOd, double wagaDo){
        wymiarX = X;
        wymiarY = Y;
        this.szansaNaKrawedz = szansaNaKrawedz;
        this.wagaOd = wagaOd;
        this.wagaDo = wagaDo;

        wierzcholki = new Wierzcholek[wymiarX * wymiarY];
        for(int i = 0; i < wymiarY * wymiarX; i++){
            wierzcholki[i] = new Wierzcholek(wagaOd, wagaDo);
        }
    }

    public void WypiszGraf(){
        for (int i = 0; i < wymiarY * wymiarX; i++){
            System.out.println((i+1) + ": " + wierzcholki[i].toString());
        }
    }



}

package grapher;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    private boolean jestKrawedzGora(int nrWierzcholka){
        return nrWierzcholka - wymiarY >= 0;
    }
    private boolean jestKrawedzPrawo(int nrWierzcholka){
        return (nrWierzcholka + 1)% wymiarY != 0;
    }
    private boolean jestKrawedzDol(int nrWierzcholka){
        return nrWierzcholka < (wymiarY * wymiarX) - wymiarY;
    }
    private boolean jestKrawedzLewo(int nrWierzcholka){
        return nrWierzcholka % wymiarY != 0;
    }

    public Graf(int X, int Y, double szansaNaKrawedz, double wagaOd, double wagaDo){
        wymiarX = X;
        wymiarY = Y;
        this.szansaNaKrawedz = szansaNaKrawedz;
        this.wagaOd = wagaOd;
        this.wagaDo = wagaDo;

        wierzcholki = new Wierzcholek[wymiarX * wymiarY];
        for(int i = 0; i < wymiarY * wymiarX; i++){
            wierzcholki[i] = new Wierzcholek();
        }
    }

    public void generujKrawedzie(){
        for(int i = 0; i < wymiarY * wymiarX; i++){
            if((Math.random() < szansaNaKrawedz) && jestKrawedzPrawo(i)){
                wierzcholki[i].setKrawedz_prawo(Math.random() * (wagaDo - wagaOd) + wagaDo);
            }
            if((Math.random() < szansaNaKrawedz) && jestKrawedzDol(i)){
                wierzcholki[i].setKrawedz_dol(Math.random() * (wagaDo - wagaOd) + wagaDo);
            }
        }
    }

    public void zapiszDoPliku(String sciezkaDoPliku) throws IOException {
        File plik = new File(sciezkaDoPliku);
        System.out.println(plik.getAbsolutePath());
        FileWriter fw = new FileWriter(plik);
        fw.write(wymiarX + " " + wymiarY + "\n");
        for(int i = 0; i < wymiarY * wymiarX; i++){
            fw.write("  ");
            if(jestKrawedzGora(i)){
                fw.write(" " + (i - wymiarY) + " :" + wierzcholki[i-wymiarY].getKrawedz_dol() );
            }
            if(jestKrawedzPrawo(i)){
                fw.write(" " + (i + 1) + " :" + wierzcholki[i].getKrawedz_prawo() );
            }
            if(jestKrawedzDol(i)){
                fw.write(" " + (i + wymiarY) + " :" + wierzcholki[i].getKrawedz_dol() );
            }
            if(jestKrawedzLewo(i)){
                fw.write(" " + (i - 1) + " :" + wierzcholki[i-1].getKrawedz_prawo() );
            }
            fw.write("\n");
        }
        fw.close();
    }

    public void wypiszGraf(){
        for (int i = 0; i < wymiarY * wymiarX; i++){
            System.out.println((i+1) + ": " + wierzcholki[i].toString());
        }
    }





}

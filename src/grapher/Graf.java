package grapher;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Graf  {
    private Wierzcholek[] wierzcholki;
    private int wymiarX;
    private int wymiarY;
    private double szansaNaKrawedz;
    private double wagaOd;
    private double wagaDo;

    public boolean jestKrawedzGora(int nrWierzcholka){
        return nrWierzcholka - wymiarY >= 0;
    }
    public boolean jestKrawedzPrawo(int nrWierzcholka){
        return (nrWierzcholka + 1)% wymiarY != 0;
    }
    public boolean jestKrawedzDol(int nrWierzcholka){
        return nrWierzcholka < (wymiarY * wymiarX) - wymiarY;
    }
    public boolean jestKrawedzLewo(int nrWierzcholka){
        return nrWierzcholka % wymiarY != 0;
    }

    public int nrIndeksuGora(int nrWierzcholka){
        return nrWierzcholka - wymiarY;
    }
    public int nrIndeksuPrawo(int nrWierzcholka){
        return nrWierzcholka +1;
    }
    public int nrIndeksuDol(int nrWierzcholka){
        return nrWierzcholka + wymiarY;
    }
    public int nrIndeksuLewo(int nrWierzcholka){
        return nrWierzcholka - 1;
    }

    public double getWagaKrawedziGora(int nrWierzcholka){
        return wierzcholki[nrIndeksuGora(nrWierzcholka)].getKrawedz_dol();
    }
    public double getWagaKrawedziPrawo(int nrWierzcholka){
        return wierzcholki[nrWierzcholka].getKrawedz_prawo();
    }
    public double getWagaKrawedziDol(int nrWierzcholka){
        return wierzcholki[nrWierzcholka].getKrawedz_dol();
    }
    public double getWagaKrawedziLewo(int nrWierzcholka){
        return wierzcholki[nrIndeksuLewo(nrWierzcholka)].getKrawedz_prawo();
    }

    private void inicjalizajaGrafu(){
        wierzcholki = new Wierzcholek[wymiarX * wymiarY];
        for(int i = 0; i < wymiarY * wymiarX; i++){
            wierzcholki[i] = new Wierzcholek();
        }
    }
    public Graf(){

    }


    public Graf(int X, int Y, double szansaNaKrawedz, double wagaOd, double wagaDo){
        wymiarX = X;
        wymiarY = Y;
        this.szansaNaKrawedz = szansaNaKrawedz;
        this.wagaOd = wagaOd;
        this.wagaDo = wagaDo;
        inicjalizajaGrafu();
        generujKrawedzie();
    }


    public Graf (String sciezkaDoPliku) throws FileNotFoundException {
        File plik = new File(sciezkaDoPliku);
        Scanner scanner = new Scanner(plik);
        this.wymiarX = scanner.nextInt();
        this.wymiarY = scanner.nextInt();
        scanner.nextLine();

        inicjalizajaGrafu();

        String regex = "([0-9]+) :([0-9]+.[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        String tmp;
        for ( int i = 0; i < wymiarY*wymiarX; i++){
            System.out.println("Wczytuje wierzcholek nr " + (i+1));
            tmp = scanner.nextLine();
            matcher =pattern.matcher(tmp);
            while(matcher.find()){
                /*if(Integer.parseInt(matcher.group(1)) == nrIndeksuGora(i)){
                    wierzcholki[nrIndeksuGora(i)].setKrawedz_dol(Double.parseDouble(matcher.group(2)));
                    continue;
                }*/
                if(Integer.parseInt(matcher.group(1)) == nrIndeksuPrawo(i)){
                    wierzcholki[i].setKrawedz_prawo(Double.parseDouble(matcher.group(2)));
                    continue;
                }
                if(Integer.parseInt(matcher.group(1)) == nrIndeksuDol(i)){
                    wierzcholki[i].setKrawedz_dol(Double.parseDouble(matcher.group(2)));
                    continue;
                }
                /*if(Integer.parseInt(matcher.group(1)) == nrIndeksuLewo(i)){
                    wierzcholki[nrIndeksuLewo(i)].setKrawedz_prawo(Double.parseDouble(matcher.group(2)));

                }*/
            }
        }
    }

    public void generujGraf(){
        inicjalizajaGrafu();
        generujKrawedzie();
    }

    private void generujKrawedzie(){
        for(int i = 0; i < wymiarY * wymiarX; i++){
            if((Math.random() < szansaNaKrawedz) && jestKrawedzPrawo(i)){
                wierzcholki[i].setKrawedz_prawo(Math.random() * (wagaDo - wagaOd) + wagaOd);
            }
            if((Math.random() < szansaNaKrawedz) && jestKrawedzDol(i)){
                wierzcholki[i].setKrawedz_dol(Math.random() * (wagaDo - wagaOd) + wagaOd);
            }
        }
    }

    public void zapiszDoPliku(String sciezkaDoPliku) throws IOException {
        File plik = new File(sciezkaDoPliku);
        //System.out.println(plik.getAbsolutePath());
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

    public void setWymiarX(int wymiarX) {
        this.wymiarX = wymiarX;
    }

    public void setWymiarY(int wymiarY) {
        this.wymiarY = wymiarY;
    }

    public void setSzansaNaKrawedz(double szansaNaKrawedz) {
        this.szansaNaKrawedz = szansaNaKrawedz;
    }

    public void setWagaOd(double wagaOd) {
        this.wagaOd = wagaOd;
    }

    public void setWagaDo(double wagaDo) {
        this.wagaDo = wagaDo;
    }

    public double getWagaOd() {
        return wagaOd;
    }

    public double getWagaDo() {
        return wagaDo;
    }

    public Wierzcholek[] getWierzcholki() {
        return wierzcholki;
    }

    public int getWymiarX() {
        return wymiarX;
    }

    public int getWymiarY() {
        return wymiarY;
    }
}

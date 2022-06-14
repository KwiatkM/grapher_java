package grapher;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
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

    public void usunKrawedzieWierzcholka(int nrWierzcholka){
        wierzcholki[nrWierzcholka].setKrawedzie(-1.1,-1.1);
        if(jestKrawedzGora(nrWierzcholka)) {wierzcholki[nrIndeksuGora(nrWierzcholka)].setKrawedz_dol(-1.1);}
        if(jestKrawedzLewo(nrWierzcholka)) {wierzcholki[nrIndeksuLewo(nrWierzcholka)].setKrawedz_prawo(-1.1);}
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

        double wagaMax = 0;
        double wagaMin = Double.MAX_VALUE;

        scanner.nextLine();

        inicjalizajaGrafu();

        String regex = "([0-9]+) :([0-9]+.[0-9]+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;

        String tmp;
        for ( int i = 0; i < wymiarY*wymiarX; i++){
            tmp = scanner.nextLine();
            matcher =pattern.matcher(tmp);
            double dTmp;
            while(matcher.find()){
                if(Integer.parseInt(matcher.group(1)) == nrIndeksuPrawo(i)){
                    dTmp = Double.parseDouble(matcher.group(2));
                    wierzcholki[i].setKrawedz_prawo(dTmp);
                    if( dTmp > wagaMax ) wagaMax = dTmp;
                    if(dTmp < wagaMin) wagaMin = dTmp;
                    continue;
                }
                if(Integer.parseInt(matcher.group(1)) == nrIndeksuDol(i)){
                    dTmp = Double.parseDouble(matcher.group(2));
                    wierzcholki[i].setKrawedz_dol(dTmp);
                    if( dTmp > wagaMax ) wagaMax = dTmp;
                    if(dTmp < wagaMin) wagaMin = dTmp;
                    continue;
                }
            }
        }
        wagaOd = wagaMin;
        wagaDo = wagaMax;
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

    public void usunSciezke( ArrayList<Integer> sciezka){
        for(int i : sciezka){
            usunKrawedzieWierzcholka(i);
        }
    }

    public void usunSciezkeDokladnie(ArrayList<Integer> sciezka){
        for(int i = 0; i < sciezka.size()-1 ;i++) {
            if(sciezka.get(i+1) == nrIndeksuGora(sciezka.get(i))){
                wierzcholki[nrIndeksuGora(sciezka.get(i))].setKrawedz_dol(-1.1);
            }
            if(sciezka.get(i+1) == nrIndeksuPrawo(sciezka.get(i))){
                wierzcholki[sciezka.get(i)].setKrawedz_prawo(-1.1);
            }
            if(sciezka.get(i+1) == nrIndeksuDol(sciezka.get(i))){
                wierzcholki[sciezka.get(i)].setKrawedz_dol(-1.1);
            }
            if(sciezka.get(i+1) == nrIndeksuLewo(sciezka.get(i))){
                wierzcholki[nrIndeksuLewo(sciezka.get(i))].setKrawedz_prawo(-1.1);
            }

        }
    }

    public void wypiszGraf(){
        for (int i = 0; i < wymiarY * wymiarX; i++){
            System.out.println((i) + ": " + wierzcholki[i].toString());
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

    public Wierzcholek getWierzcholek(int i){
        return wierzcholki[i];
    }

    public int getWymiarX() {
        return wymiarX;
    }

    public int getWymiarY() {
        return wymiarY;
    }
}

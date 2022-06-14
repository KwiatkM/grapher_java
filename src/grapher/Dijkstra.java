package grapher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Dijkstra {
    private final double nieskonczonosc = Double.MAX_VALUE;
    private Graf graf;
    private int iloscWierzcholkow;
    private double[] odleglosc;
    private boolean[] odwiedzone;
    private int[] poprzednik;

    private int wierzcholekStartowy;

    private PriorityQueue<Integer> kolejkaPriorytetowa;

    public Dijkstra(Graf graf){
        this.graf = graf;
        this.iloscWierzcholkow = graf.getWymiarX() * graf.getWymiarY();
        odleglosc = new double[iloscWierzcholkow];
        odwiedzone = new boolean[iloscWierzcholkow];
        poprzednik = new int[iloscWierzcholkow];
        for(int i = 0; i < iloscWierzcholkow; i++){
            odleglosc[i] = nieskonczonosc;
            odwiedzone[i] = false;
            poprzednik[i] = Integer.MAX_VALUE;
        }

        kolejkaPriorytetowa = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                double roznica = odleglosc[o1] - odleglosc[o2];
                return Double.compare(roznica, 0.0);
            }
        });
    }

    public void start(int start){
        reset();
        wierzcholekStartowy = start;
        odleglosc[start] = 0.0;
        kolejkaPriorytetowa.add(start);
        int u;
        double tmp;
        int somsiad;
        while(!kolejkaPriorytetowa.isEmpty()){
            u = kolejkaPriorytetowa.poll();
            odwiedzone[u] = true;

            if(graf.jestKrawedzLewo(u) && !odwiedzone[graf.nrIndeksuLewo(u)] && graf.getWagaKrawedziLewo(u) > 0.0){
                somsiad = graf.nrIndeksuLewo(u);
                if(odleglosc[somsiad] > odleglosc[u] + graf.getWagaKrawedziLewo(u)){
                    tmp = odleglosc[somsiad];
                    odleglosc[somsiad] = odleglosc[u] + graf.getWagaKrawedziLewo(u);
                    poprzednik[somsiad] = u;
                    if(tmp == nieskonczonosc) kolejkaPriorytetowa.add(somsiad);
                    else {
                        kolejkaPriorytetowa.remove(somsiad);
                        kolejkaPriorytetowa.add(somsiad);
                    }
                }
            }

            if(graf.jestKrawedzGora(u) && !odwiedzone[graf.nrIndeksuGora(u)] && graf.getWagaKrawedziGora(u) > 0.0){
                somsiad = graf.nrIndeksuGora(u);
                if(odleglosc[somsiad] > odleglosc[u] + graf.getWagaKrawedziGora(u)){
                    tmp = odleglosc[somsiad];
                    odleglosc[somsiad] = odleglosc[u] + graf.getWagaKrawedziGora(u);
                    poprzednik[somsiad] = u;
                    if(tmp == nieskonczonosc) kolejkaPriorytetowa.add(somsiad);
                    else {
                        kolejkaPriorytetowa.remove(somsiad);
                        kolejkaPriorytetowa.add(somsiad);
                    }
                }
            }

            if(graf.jestKrawedzPrawo(u) && !odwiedzone[graf.nrIndeksuPrawo(u)] && graf.getWagaKrawedziPrawo(u) > 0.0){
                somsiad = graf.nrIndeksuPrawo(u);
                if(odleglosc[somsiad] > odleglosc[u] + graf.getWagaKrawedziPrawo(u)){
                    tmp = odleglosc[somsiad];
                    odleglosc[somsiad] = odleglosc[u] + graf.getWagaKrawedziPrawo(u);
                    poprzednik[somsiad] = u;
                    if(tmp == nieskonczonosc) kolejkaPriorytetowa.add(somsiad);
                    else {
                        kolejkaPriorytetowa.remove(somsiad);
                        kolejkaPriorytetowa.add(somsiad);
                    }
                }
            }

            if(graf.jestKrawedzDol(u) && !odwiedzone[graf.nrIndeksuDol(u)] && graf.getWagaKrawedziDol(u) > 0.0){
                somsiad = graf.nrIndeksuDol(u);
                if(odleglosc[somsiad] > odleglosc[u] + graf.getWagaKrawedziDol(u)){
                    tmp = odleglosc[somsiad];
                    odleglosc[somsiad] = odleglosc[u] + graf.getWagaKrawedziDol(u);
                    poprzednik[somsiad] = u;
                    if(tmp == nieskonczonosc) kolejkaPriorytetowa.add(somsiad);
                    else {
                        kolejkaPriorytetowa.remove(somsiad);
                        kolejkaPriorytetowa.add(somsiad);
                    }
                }
            }
        }
    }

    private void reset(){
        for(int i = 0; i < iloscWierzcholkow; i++){
            odleglosc[i] = nieskonczonosc;
            odwiedzone[i] = false;
            poprzednik[i] = Integer.MAX_VALUE;
        }
    }


    // metoda zwraca najkrótszą ścieżkę do wierzchołka "wierzchołekDocelowy" w postaci ArrayList
    public ArrayList<Integer> znajdzNajkrotszaSciezke (int wierzholekDocelowy){
        ArrayList<Integer> sciezka = new ArrayList<>();
        int aktualnyWierzcholek = wierzholekDocelowy;
        while(aktualnyWierzcholek != Integer.MAX_VALUE){
            sciezka.add(aktualnyWierzcholek);
            aktualnyWierzcholek = poprzednik[aktualnyWierzcholek];
        }
        return sciezka;
    }

    public void usunSciezke( ArrayList<Integer> sciezka){
        for(int i : sciezka){
        graf.usunKrawedzieWierzcholka(i);
        }
    }

    public double[] getOdleglosc() {
        return odleglosc;
    }

    public double znajdzNajdluzszaOdleglosc(){
        double max = 0;
        for(int i = 0; i < iloscWierzcholkow; i++){
            if(odleglosc[i] != nieskonczonosc && odleglosc[i] > max)
                max = odleglosc[i];

        }
        return max;
    }

    public void wypiszTablice(){
        for (int i = 0; i < iloscWierzcholkow; i++){
            System.out.println( "wierzch. " + i + ", odl. " + odleglosc[i] + ", poprz. " + poprzednik[i] + ", odw. " + odwiedzone[i]);
        }
    }
}



package grapher;

import java.util.concurrent.ConcurrentLinkedQueue;

public class BFS {
    private boolean[] odwiedzone;
    private int iloscWierzcholkow;
    private ConcurrentLinkedQueue<Integer> kolejka;
    Graf graf;


    public BFS(Graf graf) {
        iloscWierzcholkow = graf.getWymiarX() * graf.getWymiarY();
        this.graf = graf;
        odwiedzone = new boolean[iloscWierzcholkow];
        for (int i = 0; i < iloscWierzcholkow; i++) {
            odwiedzone[i] = false;
        }
        kolejka = new ConcurrentLinkedQueue<>();
    }

    private void start(){
        kolejka.add(0);
        odwiedzone[0] = true;
        int u;
        while(!kolejka.isEmpty()){
            u = kolejka.poll();
            if(graf.jestKrawedzLewo(u) && graf.getWagaKrawedziLewo(u) > 0.0 && !odwiedzone[graf.nrIndeksuLewo(u)]){
                kolejka.add(graf.nrIndeksuLewo(u));
                odwiedzone[graf.nrIndeksuLewo(u)] = true;
            }
            if(graf.jestKrawedzGora(u) && graf.getWagaKrawedziGora(u) > 0.0 && !odwiedzone[graf.nrIndeksuGora(u)]){
                kolejka.add(graf.nrIndeksuGora(u));
                odwiedzone[graf.nrIndeksuGora(u)] = true;
            }
            if(graf.jestKrawedzPrawo(u) && graf.getWagaKrawedziPrawo(u) > 0.0 && !odwiedzone[graf.nrIndeksuPrawo(u)]){
                kolejka.add(graf.nrIndeksuPrawo(u));
                odwiedzone[graf.nrIndeksuPrawo(u)] = true;
            }
            if(graf.jestKrawedzDol(u) && graf.getWagaKrawedziDol(u) > 0.0 && !odwiedzone[graf.nrIndeksuDol(u)]){
                kolejka.add(graf.nrIndeksuDol(u));
                odwiedzone[graf.nrIndeksuDol(u)] = true;
            }
        }
    }

    public boolean grafSpojny(){
        start();
        boolean spojnosc = true;
        for(int i = 0; i < iloscWierzcholkow; i++){
            if(!odwiedzone[i]){
                spojnosc = false;
                break;
            }
        }
        return spojnosc;
    }

    public void wypiszTablice(){
        for (int i = 0; i < iloscWierzcholkow; i++){
            System.out.println( "wierzch. " + i + " - odwiedzone: " + odwiedzone[i]);
        }
    }

}



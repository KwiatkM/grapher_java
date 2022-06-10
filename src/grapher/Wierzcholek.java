package grapher;

public class Wierzcholek {
    public double krawedz_prawo;
    public double krawedz_dol;

    public Wierzcholek (double waga_od, double waga_do){
        krawedz_dol = Math.random() * (waga_do - waga_od) + waga_od;
        krawedz_prawo = Math.random() * (waga_do - waga_od) + waga_od;
    }

    @Override
    public String toString() {
        return "prawo: " + krawedz_prawo + ", dol: " + krawedz_dol;
    }


}

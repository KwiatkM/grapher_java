package grapher;

public class Wierzcholek {
    private double krawedz_prawo;
    private double krawedz_dol;

    public Wierzcholek (){
        krawedz_dol = -1.1;
        krawedz_prawo = -1.1;
    }

    public void setKrawedzie (double prawo, double dol){
        krawedz_prawo = prawo;
        krawedz_dol = dol;
    }

    public void setKrawedz_prawo(double krawedz_prawo) {
        this.krawedz_prawo = krawedz_prawo;
    }

    public void setKrawedz_dol(double krawedz_dol) {
        this.krawedz_dol = krawedz_dol;
    }

    public double getKrawedz_prawo() {
        return krawedz_prawo;
    }

    public double getKrawedz_dol() {
        return krawedz_dol;
    }

    @Override
    public String toString() {
        return "prawo: " + krawedz_prawo + ", dol: " + krawedz_dol;
    }


}

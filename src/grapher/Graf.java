package grapher;

public class Graf  {
    private Wierzcholek[] wierzcholki;
    private int wymiarX = 100;
    private int wymiarY = 100;
    private double szansaNaKrawedz = 1;
    private double wagaOd = 0;
    private double wagaDo = 100;

    /*
    public Graf(){
        wierzcholki = new Wierzcholek[wymiarX * wymiarY];
        for(int i = 0; i < wymiarY * wymiarX; i++){
            wierzcholki[i] = new Wierzcholek(wagaOd, wagaDo);
        }
    }
    */
    /*
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
    */
    public void setWierzcholki() {
        wierzcholki = new Wierzcholek[wymiarX * wymiarY];
        for (int i = 0; i < wymiarY * wymiarX; i++) {
            wierzcholki[i] = new Wierzcholek(wagaOd, wagaDo);
        }
    }

    public void WypiszGraf(){
        for (int i = 0; i < wymiarY * wymiarX; i++){
            System.out.println((i+1) + ": " + wierzcholki[i].toString());
        }
    }

    public void setWymiarX(int wymiarX){
        this.wymiarX = wymiarX;
    }
    public void setWymiarY(int wymiarY){
        this.wymiarY = wymiarY;
    }
    public void setWagaOd(double wagaOd){
        this.wagaOd = wagaOd;
    }
    public void setWagaDo(double wagaDo){
        this.wagaDo = wagaDo;
    }
    public void setSzansaNaKrawedz(double szansaNaKrawedz){
        this.szansaNaKrawedz = szansaNaKrawedz;
    }
}

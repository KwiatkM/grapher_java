package GUI;

import grapher.Dijkstra;
import grapher.Graf;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class CanvasTest {
    private Graf graf;
    private Canvas canvas;
    private double scale;
    private GraphicsContext gc;

    private int graphXsize;
    private int graphYsize;

    private double selectedX;
    private double selectedY;
    private boolean nodeSelected;
    private int mainNodeNumber;
    private double minDist;
    private double maxDist;
    private double canvasSizeX;
    private double canvasSizeY;
    private Color idleNode = Color.GRAY;
    private Color selectedColor = Color.rgb(112,112,112);

    private double d;
    private double odstep;
    private double szerokoscKrawedzi;

    private Dijkstra dijkstra;
    private ArrayList<Integer> currentPath;

    public CanvasTest(Graf graf, double skala){
        this.graf = graf;
        minDist = 0;
        maxDist = Math.sqrt(graf.getWymiarX() * graf.getWymiarY()) * graf.getWagaDo()/1.5;

        scale = skala;
        d = 30*scale; // średnica kółka
        szerokoscKrawedzi =
        odstep = d/3; // odstęp między kółkami
        canvasSizeX = graf.getWymiarX() * (d+odstep) + odstep; // rozmiar X płutna
        canvasSizeY = graf.getWymiarY() * (d+odstep) + odstep; // rozmiar Y płutna
        canvas = new Canvas(canvasSizeY,canvasSizeX);
        graphXsize = graf.getWymiarX();
        graphYsize = graf.getWymiarY();

        dijkstra = new Dijkstra(graf);

        gc = canvas.getGraphicsContext2D();

        draw();

        // tu niżej x i y są zamienione miejscem
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                /*
                if(nodeSelected){
                    gc.setFill(idleNode);
                    gc.fillOval(selectedY,selectedX,d,d);
                    nodeSelected = false;
                }*/

                selectedX = mouseEvent.getX();
                selectedY = mouseEvent.getY();

                double tmpx = ((mouseEvent.getX() - odstep)/(d + odstep));
                double tmpy = ((mouseEvent.getY() - odstep)/(d + odstep));

                int tmpIx = (int) tmpx;
                int tmpIy = (int) tmpy;

                double dx = d/(d+odstep);

                // warunek na naciśnięcie na kółko (wierzchołek)
                if((tmpx - tmpIx < dx) && (tmpy - tmpIy < dx) ){
                    selectedX = (tmpIy + 1) * odstep + tmpIy * d;
                    selectedY = (tmpIx + 1) * odstep + tmpIx * d;
                    if(!nodeSelected) {
                        mainNodeNumber = tmpIy * graphYsize + tmpIx;
                        draw(dijkstra);
                        gc.setFill(Color.rgb(255, 255, 255));

                        gc.fillOval(selectedY, selectedX, d, d);
                        nodeSelected = true;
                    }
                    else {
                        gc.setFill(selectedColor);
                        gc.fillOval(selectedY, selectedX, d, d);
                        drawPath(tmpIy * graphYsize + tmpIx);
                    }
                }
            }
        });

        nodeSelected = false;
    }

    private Color getEdgeColor (Graf graf, int nrWierzcholka, boolean right ){
        final double BLUE_HUE = Color.BLUE.getHue();
        final double Red_HUE = Color.RED.getHue();
        double hue;
        if(right)
            hue = BLUE_HUE + (Red_HUE - BLUE_HUE) * (graf.getWagaKrawedziPrawo(nrWierzcholka) - graf.getWagaOd())/(graf.getWagaDo() - graf.getWagaOd() );
        else
            hue = BLUE_HUE + (Red_HUE - BLUE_HUE) * (graf.getWagaKrawedziDol(nrWierzcholka) - graf.getWagaOd())/(graf.getWagaDo() - graf.getWagaOd() );
        return Color.hsb(hue, 1.0, 1.0);
    }

    private void draw(){
        gc.setFill(Color.rgb(252, 241, 230));
        gc.fillRect(0,0,canvasSizeX,canvasSizeY);

        //rysowanie krawędzi poziomych
        double xPos = odstep + d/2- odstep/4;
        double yPos = odstep + d/2 ;
        for(int x = 0; x < graf.getWymiarX(); x++){
            for(int y = 0; y < graf.getWymiarY()-1;y++){
                if(graf.getWierzcholek(graphYsize * x + y).getKrawedz_prawo() != -1.1){
                    gc.setFill(getEdgeColor(graf,graphYsize * x + y,true));
                    gc.fillRect(yPos,xPos,d+odstep,odstep/2);
                }
                yPos += (d + odstep);
            }
            yPos = odstep + d/2;
            xPos += (d + odstep);
        }

        // rysowanie krawędzi pionowych
        xPos = odstep + d/2;
        yPos = odstep + d/2 - odstep/4;
        for(int y = 0; y < graf.getWymiarY(); y++){
            for(int x = 0; x < graf.getWymiarX()-1;x++){
                if(graf.getWierzcholek(graphYsize * x + y).getKrawedz_dol() != -1.1){
                    gc.setFill(getEdgeColor(graf,graphYsize * x + y,false));
                    gc.fillRect(yPos,xPos,odstep/2,d+odstep);
                }
                xPos += (d + odstep);
            }
            xPos = odstep + d/2;
            yPos += (d + odstep);
        }


        gc.setFill(idleNode);
        for(int x = 0; x < graf.getWymiarX(); x++){
            for(int y = 0; y < graf.getWymiarY(); y ++){
                gc.fillOval( (y+1)*odstep + y * d,(x+1)*odstep + x *d, d,d);
            }
        }
    }

    private void draw(Dijkstra dijkstra){
        dijkstra.start(mainNodeNumber);

        gc.setFill(Color.rgb(252, 241, 230));
        gc.fillRect(0,0,canvasSizeX,canvasSizeY);

        //rysowanie krawędzi poziomych
        double xPos = odstep + d/2- odstep/4;
        double yPos = odstep + d/2 ;
        for(int x = 0; x < graf.getWymiarX(); x++){
            for(int y = 0; y < graf.getWymiarY()-1;y++){
                if(graf.getWierzcholek(graphYsize * x + y).getKrawedz_prawo() != -1.1){
                    gc.setFill(getEdgeColor(graf,graphYsize * x + y,true));
                    gc.fillRect(yPos,xPos,d+odstep,odstep/2);
                }
                yPos += (d + odstep);
            }
            yPos = odstep + d/2;
            xPos += (d + odstep);
        }

        // rysowanie krawędzi pionowych
        //gc.setFill(Color.RED);
        xPos = odstep + d/2;
        yPos = odstep + d/2 - odstep/4;
        for(int y = 0; y < graf.getWymiarY(); y++){
            for(int x = 0; x < graf.getWymiarX()-1;x++){
                if(graf.getWierzcholek(graphYsize * x + y).getKrawedz_dol() != -1.1){
                    gc.setFill(getEdgeColor(graf,graphYsize * x + y,false));
                    gc.fillRect(yPos,xPos,odstep/2,d+odstep);
                }
                xPos += (d + odstep);
            }
            xPos = odstep + d/2;
            yPos += (d + odstep);
        }


        for(int x = 0; x < graf.getWymiarX(); x++){
            for(int y = 0; y < graf.getWymiarY(); y ++){
                gc.setFill(getNodeColor(dijkstra.getOdleglosc()[x*graphYsize + y]));
                gc.fillOval( (y+1)*odstep + y * d,(x+1)*odstep + x *d, d,d);
            }
        }
    }

    private Color getNodeColor (double odleglosc){
        if (odleglosc == Double.MAX_VALUE) return Color.BLACK;
        if (odleglosc > maxDist) return Color.RED;
        final double BLUE_HUE = Color.BLUE.getHue();
        final double Red_HUE = Color.RED.getHue();
        double hue = BLUE_HUE + (Red_HUE - BLUE_HUE) * (odleglosc - minDist)/( maxDist- minDist );
        return Color.hsb(hue, 1.0, 1.0);
    }

    public void drawPath(int nrW){
       currentPath = dijkstra.znajdzNajkrotszaSciezke(nrW);
       /*

        -------------------------- DO ZROBIENIA ----------------------------------

        int wspGrX;
        int wspGrY;
        double wspCnvX;
        double wspCnvY;
        double wspKolX;
        double wspKolY;
       for(int i = currentPath.size()-1; i > 0 ;i++){
           wspGrX = (int) nrW/graphYsize;
           wspGrY = nrW - (wspGrX * graphYsize);


           if(currentPath.get(i-1) == graf.nrIndeksuGora(currentPath.get(i))){

           }
       }*/

    }





    public void redraw(){
        draw();
    }


    public Canvas getCanvas() {
        return canvas;
    }


}

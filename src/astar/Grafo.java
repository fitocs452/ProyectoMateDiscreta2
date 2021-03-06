package astar;
import java.util.ArrayList;
import java.util.Random;
/**
* Universidad Del Valle 
* Pablo Díaz 13203
* Adolfo Morales 13014
* Jorge García
*/


public class Grafo {
    
    private int ancho;
    private int alto;
    private int[] diagonalesNodos = null;
    private ArrayList<ArrayList<Nodo>> grafo; 

    public Grafo(int w, int h, int[] diagonalesNodos) {
        this.ancho = w;
        this.alto = h;
        this.diagonalesNodos = diagonalesNodos;
        crearGrafo();
    }

    public Nodo getNodo(int x, int y) {
        return grafo.get(y).get(x);
    }
    
    private void crearGrafo() 
    {
        Nodo nodo;
        grafo = new ArrayList<>();
        for (int y = 0; y < alto; y++) 
        {
            ArrayList temp= new ArrayList();
            grafo.add(temp);
            for (int x = 0; x < ancho; x++)
            {
                nodo = new Nodo(x, y, this);
                grafo.get(y).add(nodo);
            }
        }
        
        int contador = 0;
        for (int y = 0; y < alto; y++) 
        {
            for (int x = 0; x < ancho; x++)
            {
                this.getNodo(x, y).setDistribuicion(diagonalesNodos[contador]);
                contador++;
            }
        }
        
    }

    public void getGrafoGrafico(){
       
        for (int y = 0; y < alto; y++) 
        {
            for (int x = 0; x < ancho; x++)
            {
                //System.out.print(grafo.get(y).get(x));
                if (x==ancho-1){
                    System.out.println("");
                }
                
            }
        }
        
    }
    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public ArrayList<ArrayList<Nodo>> getGrafo() {
        return grafo;
    }

    public void setGrafo(ArrayList<ArrayList<Nodo>> grafo) {
        this.grafo = grafo;
    }

    public int[] getDiagonalesNodos() {
        return diagonalesNodos;
    }

    public void setDiagonalesNodos(int[] diagonalesNodos) {
        this.diagonalesNodos = diagonalesNodos;
    }
    
     public void crearOstaculo4x4() {
       grafo.get(1).get(1).setIsObstaculo(true);
        grafo.get(2).get(2).setIsObstaculo(true);
        grafo.get(1).get(2).setIsObstaculo(true);
    }
    
    public void crearOstaculo15x15() {
        grafo.get(6).get(7).setIsObstaculo(true);
        grafo.get(7).get(7).setIsObstaculo(true);
        grafo.get(3).get(7).setIsObstaculo(true);
        grafo.get(4).get(7).setIsObstaculo(true);
        grafo.get(5).get(7).setIsObstaculo(true);
        grafo.get(7).get(8).setIsObstaculo(true);
        grafo.get(7).get(9).setIsObstaculo(true);
    }
    
    public void crearOstaculo20x20() {
        grafo.get(6).get(7).setIsObstaculo(true);
        grafo.get(7).get(7).setIsObstaculo(true);
        grafo.get(3).get(7).setIsObstaculo(true);
        grafo.get(4).get(7).setIsObstaculo(true);
        grafo.get(5).get(7).setIsObstaculo(true);
        grafo.get(7).get(8).setIsObstaculo(true);
        grafo.get(7).get(9).setIsObstaculo(true);
        grafo.get(7).get(10).setIsObstaculo(true);
        grafo.get(7).get(11).setIsObstaculo(true);
    }
    
}

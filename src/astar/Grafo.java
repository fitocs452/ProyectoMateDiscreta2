package astar;
import java.util.ArrayList;


public class Grafo {
    private int ancho;
    private int alto;
    private ArrayList<ArrayList<Nodo>> grafo;

    public Grafo(int w, int h) {
        this.ancho = w;
        this.alto = h;

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
            grafo.add(new ArrayList<>());
            for (int x = 0; x < ancho; x++)
            {
                nodo = new Nodo(x, y, this);
                grafo.get(y).add(nodo);
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
        
}

package astar;

import java.util.ArrayList;
import java.util.Random;


public class Nodo implements Comparable<Nodo> {
    private boolean isObstaculo;
    private Nodo raiz;
    private Grafo grafo;
    private int x;
    private int y;
    private double funcionHeursitica;//costo del camino nodo actual al final -> Greedy
    private double distanciaDesdeInicio; //funcion G:costo del mejor camino encontrado->Dijsktra
    private double funcionF; //f = g+h



    public Nodo(int x, int y, Grafo grafo) {
        this.x = x;
        this.y = y;
        this.isObstaculo = false;
        this.distanciaDesdeInicio =1;
        this.grafo = grafo;
    }

    public boolean equals(Nodo nodo) {
            return (this.x == nodo.x) && (this.y == nodo.y);
    }
	
	
    public ArrayList<Nodo> getNodosAdyacente(boolean diagonales) {
    Random aleatorio = new Random();
    int distribuicion =0;
    if (diagonales)
        distribuicion= aleatorio.nextInt(2)+1;
        
        
        ArrayList<Nodo> nodosAdyacentes = new ArrayList<>();
        if ((y != 0)) 
        {
                nodosAdyacentes.add(grafo.getNodo(x, (y - 1)));
        }
        
       
        if ((x != (grafo.getAncho() - 1))) {
                nodosAdyacentes.add(grafo.getNodo(x + 1, y));

        }
        if (diagonales)
        {
            if (distribuicion ==1)
            {
                //diagonales hacia derecha y arriba
                 if ((y != 0) && !(x == (grafo.getAncho() - 1))) 
                {
                        nodosAdyacentes.add(grafo.getNodo(x + 1, y - 1));

                }
                //diagonales hacia  izquierda y abajo
                if ((x != 0) && (y != (grafo.getAlto() - 1)))
                {
                    nodosAdyacentes.add(grafo.getNodo(x - 1, y + 1));

                } 
            }
            if (distribuicion ==2)
            {
                //diagonales hacia abajo y derecha
                if ((x != (grafo.getAncho()- 1)) && !(y == (grafo.getAlto() - 1))) 
                {
                        nodosAdyacentes.add(grafo.getNodo(x + 1, y + 1));
                }
                //diagonales hacia izquierda y arriba
                if ((x != 0) && (y != 0)) 
                {
                    nodosAdyacentes.add(grafo.getNodo(x - 1, y - 1));

                }
            }
        }
        
        if ((y != (grafo.getAlto() - 1)))
        {
                nodosAdyacentes.add(grafo.getNodo(x, y + 1));

        }
        
      
        if ((x != 0)) 
        {
            nodosAdyacentes.add(grafo.getNodo(x - 1, y));

        }
        
        
        return nodosAdyacentes;
    }

    public double getFuncionF() {
        return funcionF;
    }

    public void setFuncionF(double funcionF) {
        this.funcionF = funcionF;
    }

    
    public boolean isIsObstaculo() {
        return isObstaculo;
    }

    public void setIsObstaculo(boolean isObstaculo) {
        this.isObstaculo = isObstaculo;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public void setGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getFuncionHeursitica() {
        return funcionHeursitica;
    }

    public void setFuncionHeursitica(double funcionHeursitica) {
        this.funcionHeursitica = funcionHeursitica;
    }

    public double getFuncionG() {
        return distanciaDesdeInicio;
    }

    public void setFuncionG(double funcionG) {
        this.distanciaDesdeInicio = funcionG;
    }

        
    @Override
    public int compareTo(Nodo other) {
        double totalDistanceFromGoal = this.distanciaDesdeInicio + this.funcionHeursitica;
        double otherDistanceFromGoal = other.distanciaDesdeInicio + other.funcionHeursitica;
        
        if (totalDistanceFromGoal < otherDistanceFromGoal)
                return -1;
        if (otherDistanceFromGoal > totalDistanceFromGoal)
                return 1;
        return 0;
    }
    @Override
    public String toString()
    {
        return "("+this.x +" ," + this.y +")";
    }
}

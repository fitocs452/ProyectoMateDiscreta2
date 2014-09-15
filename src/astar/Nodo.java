package astar;

import java.util.ArrayList;


public class Nodo implements Comparable<Nodo> {
	private boolean isObstaculo;
	private Nodo raiz;
	private Grafo grafo;
	private int x;
	private int y;
	private double funcionHeursitica;//costo del camino nodo actual al final -> Greedy
	private double funcionG; //costo del mejor camino encontrado->Dijsktra
	
	
	
	public Nodo(int x, int y, Grafo grafo) {
            this.x = x;
            this.y = y;
            this.funcionG = Integer.MAX_VALUE;
            this.isObstaculo = false;
            this.grafo = grafo;
	}
	
	public boolean equals(Nodo nodo) {
		return (this.x == nodo.x) && (this.y == nodo.y);
	}
	
	
	public ArrayList<Nodo> getNodosAdyacente() {
            ArrayList<Nodo> nodosAdyacentes = new ArrayList<>();
            if ((y != 0)) 
            {
                    nodosAdyacentes.add(grafo.getNodo(x, (y - 1)));
            }
            if ((y != 0) && !(x == (grafo.getAncho() - 1))) 
            {
                    nodosAdyacentes.add(grafo.getNodo(x + 1, y - 1));
        
            }
            if ((x != (grafo.getAncho() - 1))) {
                    nodosAdyacentes.add(grafo.getNodo(x + 1, y));
       
            }
            /*if ((x != (map.width - 1)) && !(y == (map.height - 1))) 
            {
                    neighborList.add(map.getNode(x + 1, y + 1));
        
            }*/
            if ((y != (grafo.getAlto() - 1)))
            {
                    nodosAdyacentes.add(grafo.getNodo(x, y + 1));

            }
            if ((x != 0) && (y != (grafo.getAlto() - 1)))
            {
                    nodosAdyacentes.add(grafo.getNodo(x - 1, y + 1));
       
            }
            if ((x != 0)) 
            {
                nodosAdyacentes.add(grafo.getNodo(x - 1, y));
         
            }
            if ((x != 0) && (y != 0)) 
            {
                nodosAdyacentes.add(grafo.getNodo(x - 1, y - 1));
         
            }
            return nodosAdyacentes;
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
        return funcionG;
    }

    public void setFuncionG(double funcionG) {
        this.funcionG = funcionG;
    }

        
	@Override
	public int compareTo(Nodo other) {
		double totalDistanceFromGoal = this.funcionG + this.funcionHeursitica;
		double otherDistanceFromGoal = other.funcionG + other.funcionHeursitica;
		if (totalDistanceFromGoal < otherDistanceFromGoal)
			return -1;
		if (otherDistanceFromGoal < totalDistanceFromGoal)
			return 1;
		return 0;
	}
}

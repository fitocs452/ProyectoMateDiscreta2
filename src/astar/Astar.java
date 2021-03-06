package astar;

/**
* Universidad Del Valle 
* Pablo Díaz 13203
* Adolfo Morales 13014
* Jorge García
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;


public class Astar {

    private final Grafo grafo;
    private final Nodo inicio;
    private final Nodo destino;
    private List<Nodo> path = new ArrayList<>();
    private  Set<Nodo> nodosEvaluados;
    private int[] arregloDiagonales;
    
    public Astar(int ancho, int alto, int[] arregloDistri) 
    {
        this.grafo = new Grafo(ancho, alto, arregloDistri);
        this.arregloDiagonales = this.grafo.getDiagonalesNodos();
        this.inicio = new Nodo(0, 0, grafo);
        this.destino = new Nodo(ancho-1, alto-1, grafo);
        
        
    }

    public void calcular(boolean diagonales) 
    {
        nodosEvaluados= new HashSet<>(); //set de nodos evaluados
        PriorityQueue<Nodo> nodosPorEvaluar = new PriorityQueue<>();//set de nodos por evaluar, contiene inicialmente al nodo inicial

        inicio.setFuncionG(0); //costo desde el inicio hasta el mejor camino conocido
        inicio.setFuncionHeursitica(calcularHeuristica(inicio, destino,diagonales));
        nodosPorEvaluar.add(inicio);
        

        int contadorIteraciones=0;
        while (!nodosPorEvaluar.isEmpty()) {
            
            Nodo actual = nodosPorEvaluar.poll();//obtener el nodo con menor funcion f

            if (actual.equals(destino)) 
            {  
                System.out.println("Iteraciones totales-> " + contadorIteraciones);
                System.out.println("Costo Total-> " + actual.getFuncionG()+actual.getFuncionHeursitica());
                reconstruirCamino(actual);
                break;
            }
            System.out.println("nodos por evaluar (frontera)"+ contadorIteraciones);
            System.out.println(nodosPorEvaluar);
            System.out.println("nodos evaluados");
            System.out.println(nodosEvaluados);
            nodosPorEvaluar.remove(actual);
            nodosEvaluados.add(actual);

            
            for (Nodo adyacente : actual.getNodosAdyacente(diagonales)) {
                /************************************
                int contN = 0;
                int diagonalN = arregloDiagonales.indexOf(contN);
                adyacente.setDistribuicion(diagonalN);
                contN++;
                ***********************************/
          
                boolean adyacenteIsMejor;
                if (nodosEvaluados.contains(adyacente))
                    continue; //se salta una iteracion
                

                if (!adyacente.isObstaculo()) {
                    double nuevoCosto = actual.getFuncionG() + getDistanciaEntre(actual, adyacente);
                    
                    if (!nodosPorEvaluar.contains(adyacente)){
                        //Collections.sort(nodosPorEvaluar);
                        //equivale a cambiar la prioridad a una cola
                        nodosPorEvaluar.add(adyacente);
                        adyacenteIsMejor = true;
                    }
                    else if (nuevoCosto < adyacente.getFuncionG())
                        adyacenteIsMejor = true;
                    else{
                        adyacenteIsMejor =false;
                        //nodosPorEvaluar.remove(adyacente);
                    }
                    if (adyacenteIsMejor){
                        adyacente.setRaiz(actual); //añadir el camino
                        //System.out.println("n: " + nuevoCosto);
                        adyacente.setFuncionG(nuevoCosto);
                        adyacente.setFuncionHeursitica(calcularHeuristica(adyacente, destino,diagonales));
                    }
                }
            }//cierra for adyacente
            contadorIteraciones++;
        }//cierra while
    }//cierra calcular

    //método para mostrar el camino más corto encontrado
    public void reconstruirCamino(Nodo nodo)
    {
        grafo.getGrafoGrafico();
        while (!(nodo.getRaiz() == null)) {
            path.add(nodo);
            nodo = nodo.getRaiz();
        }
        path.add(nodo);
        Collections.reverse(path); //cambiar el orden
        System.out.println("");
        System.out.println(path.toString() + " ->Camino más corto");
        
    }
    //distancia entre nodos
    public double getDistanciaEntre(Nodo n1, Nodo n2) {
        if ((n1.getX() == n2.getX() ) || (n1.getY() == n2.getY()))
            return 1; //si estan a a la par el costo es constante
        else
            return Math.sqrt(2); //en otro caso estan en diagonal, costo = raiz de 2
    }
    
    //referencia: http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html#diagonal-distance
    public double calcularHeuristica(Nodo current, Nodo goal, boolean diagonales) {
        
        double D = 1.0; //peso de aristas adyacentes
        double D2 = Math.sqrt(2); //peso de arista diagonales
        double dx = Math.abs(current.getX()-goal.getX());
        double dy= Math.abs(current.getY()-goal.getY());
        double p = 1/1000;//minimum cost of taking one step/expected maximum path length
        //se realiza un promedio de distancia manhattan de 8 movimientos y 4 movimientos
        double promedio = ((D*(dx+dy)+(D2-2*D)*Math.min(dx,dy))+(D*(dx+dy)))/2;
        if (diagonales)
            return promedio*(1.0 + p);
        return (D*(dx+dy))*(1.0+p);
       
    }

    public Set<Nodo> getNodosEvaluados() {
        return nodosEvaluados;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public List<Nodo> getPath() {
        return path;
    }
}


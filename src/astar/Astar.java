package astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Astar {

    private final Grafo grafo;
    private final Nodo inicio;
    private final Nodo destino;

    public Astar(int ancho, int alto) 
    {
        this.grafo = new Grafo(ancho, alto);
        this.inicio = new Nodo(0, 0, grafo);
        this.destino = new Nodo(ancho-1, alto-1, grafo);

    }

    public void calcular(boolean diagonales) 
    {
        ArrayList<Nodo> nodosEvaluados= new ArrayList<>(); //set de nodos evaluados
        ArrayList<Nodo> nodosPorEvaluar = new ArrayList<>();//set de nodos por evaluar, contiene inicialmente al nodo inicial

        inicio.setFuncionG(0); //costo desde el inicio hasta el mejor camino conocido

        nodosPorEvaluar.add(inicio);

        int contadorIteraciones=0;
        while (!nodosPorEvaluar.isEmpty()) {

            Nodo actual = nodosPorEvaluar.get(0);//obtener el nodo con menor funcion f

            if (actual.equals(destino)) 
            {  
                System.out.println("Iteraciones totales-> " + contadorIteraciones);
                System.out.println("Costo Total-> " + actual.getFuncionG()+actual.getFuncionHeursitica());
                reconstruirCamino(actual);
                break;
            }
             
            nodosPorEvaluar.remove(actual);
            nodosEvaluados.add(actual);

            for (Nodo adyacente : actual.getNodosAdyacente(diagonales)) {
                boolean adyacenteIsMejor;
                if (nodosEvaluados.contains(adyacente))
                    continue; //se salta una iteracion

                if (!adyacente.isIsObstaculo()) {
                    double nuevoCosto = actual.getFuncionG() + getDistanciaEntre(actual, adyacente);
                    
                    

                    if (!nodosPorEvaluar.contains(adyacente) ||nuevoCosto < adyacente.getFuncionG()) {
                        Collections.sort(nodosPorEvaluar); //equivale a cambiar la prioridad a una cola
                        nodosPorEvaluar.add(adyacente);
                        adyacente.setRaiz(actual); //añadir el camino
                        //System.out.println("n: " + nuevoCosto);
                        adyacente.setFuncionG(nuevoCosto);
                        adyacente.setFuncionHeursitica(calcularHeuristica(adyacente, destino,diagonales));


                    }//cierra true adyacente mejor

                }//cierra if obstaculo 
                contadorIteraciones++;
            }//cierra for adyacente
        }//cierra while

    }//cierra calcular

    public void reconstruirCamino(Nodo nodo)
    {
        grafo.getGrafoGrafico();
        
        List<String> path = new ArrayList<>();
        double costo=0;
        int cont =1;
        while (!(nodo.getRaiz() == null)) {
            path.add("("+nodo.getX() +"," + nodo.getY()+")");
            costo+= nodo.getFuncionG()+nodo.getFuncionHeursitica();
           
            nodo = nodo.getRaiz();
        }
      
        path.add("("+nodo.getX() +"," + nodo.getY()+")");
        Collections.reverse(path);
        System.out.println("");
        System.out.println(path.toString() + " ->Camino más corto");
        


    }

    public double getDistanciaEntre(Nodo n1, Nodo n2) {
            if ((n1.getX() == n2.getX() ) || (n1.getY() == n2.getY()))
                    return 1; //si estan a a la par el costo es constante
            else
                    return Math.sqrt(2); //en otro caso estan en diagonal, costo = raiz de 2
    }

    public double calcularHeuristica(Nodo current, Nodo goal, boolean diagonales) {
        
        double D = 1.0; //peso de aristas adyacentes
        double D2 = Math.sqrt(2); //peso de arista diagonales
        double dx = Math.abs(current.getX()-goal.getX());
        double dy= Math.abs(current.getY()-goal.getY());
        double p = 1/1000;//minimum cost of taking one step/expected maximum path length
        return (D*(dx+dy)+(D2-2*D)*Math.min(dx,dy))*(1.0 + p);

    }


}


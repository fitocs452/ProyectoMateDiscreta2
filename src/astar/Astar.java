package astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Astar {
	 
	private Grafo grafo;
	
	private Nodo start;
	private Nodo finish;
	
	public Astar() {
            this.grafo = new Grafo(4, 4);
            this.start = new Nodo(0, 0, grafo);
            this.finish = new Nodo(3, 3, grafo);
		
		
	}
	
	public void calcular() {
            ArrayList<Nodo> nodosPorEvaluar = new ArrayList<>();
            ArrayList<Nodo> nodosEvaluados =new ArrayList<>();

            start.setFuncionG(0);
            nodosPorEvaluar.add(start);

            Nodo actual = null;
		
            while (!nodosPorEvaluar.isEmpty()) {
                
                actual = nodosPorEvaluar.get(0);
                
                if (actual.equals(finish)) 
                {
                    reconstruirCamino(actual);
                    break;
                }
                
                nodosPorEvaluar.remove(actual);
                nodosEvaluados.add(actual);
                
                for (Nodo adyacente : actual.getNodosAdyacente()) {
                    boolean adyacenteIsMejor;
                    if (nodosEvaluados.contains(adyacente))
                        continue; //se salta una iteracion
                    if (!adyacente.isIsObstaculo()) {
                        double distanciaInicio = actual.getFuncionG() + getDistanciaEntre(actual, adyacente);
                        if (!nodosPorEvaluar.contains(adyacente)) {
                            nodosPorEvaluar.add(adyacente);
                            Collections.sort(nodosPorEvaluar);
                            adyacenteIsMejor = true;
                        }
                        else if (!nodosPorEvaluar.contains(adyacente)||distanciaInicio < adyacente.getFuncionG())
                            adyacenteIsMejor = true;
                        else
                            adyacenteIsMejor = false;
                        if (adyacenteIsMejor) {
                            adyacente.setRaiz(actual);
                            adyacente.setFuncionG(distanciaInicio);
                            adyacente.setFuncionHeursitica(getDistanciaDestino(adyacente, finish));

                        }
                    } 

                }
            }
		
			
	}
	
	public void reconstruirCamino(Nodo node) {
          
           List<String> path = new ArrayList<>();
        
		while (!(node.getRaiz() == null)) {
            path.add("("+node.getX() +"," + node.getY()+")");
			node = node.getRaiz();
		}
        path.add("("+node.getX() +"," + node.getY()+")");
        Collections.reverse(path);
        System.out.println("");
        System.out.println(path.toString() + " ->Camino más corto");
        

	}
	
	private double getDistanciaEntre(Nodo n1, Nodo n2) {
		if ((n1.getX() == n2.getX() ) || (n1.getY() == n2.getY()))
			return 1;
		else
			return 1.9;
	}
	
	private double getDistanciaDestino(Nodo start, Nodo finish) {
		
		//Closest heuristic
		//		double dx = finish.x - start.x;
		//		double dy = finish.y - start.y;
		//		return (dx + dx) * (dy + dy);
		
		//Diagonal heuristic
		double h_diagonal = Math.min(Math.abs(start.getX()- finish.getX()), Math.abs(start.getY() - finish.getY()));
		double h_straight = Math.abs(start.getX() - finish.getX()) + Math.abs(start.getY() - finish.getY());
		double h_result = Math.sqrt(2) * h_diagonal + (h_straight - 2 * h_diagonal);
		
		
		return h_result;
	}
	
}

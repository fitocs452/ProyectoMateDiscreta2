/**
* Universidad Del Valle 
* Pablo Díaz 13203
* Adolfo Morales 13014
*/

package astar;

import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class starM {
    
    /********** Método para crear nuevas diagongales 
       Recibe como parámetro el tamanho del arreglo, que es la cantidad de nodos
                                                    ********************/
    public static int[] nuevoDistri(int tamanio){
        Random aleatorio = new Random();
        int[] arregloDistri = new int[tamanio];
        for (int i = 0; i<arregloDistri.length; i++){
            int distribuicion= aleatorio.nextInt(2)+1;
            arregloDistri[i] = distribuicion; 
        }
        return arregloDistri;
    }
    
    public static void main(String[] args) {
        
        /* Realizo todas las diagonales para los grafos de distintos tamanhos*/
        int[] arregloDistri1 = nuevoDistri(16);
        int[] arregloDistri2 = nuevoDistri(225);
        int[] arregloDistri3 = nuevoDistri(400);
        
        boolean salir = false;
        /* Ciclo para ejecutar algoritmos */
        while(!salir){
            int decision = JOptionPane.showOptionDialog(
            null,
            "Seleccione condicion", 
            "Selector de opciones",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,    // null para icono por defecto.
            new Object[] { "Grafo sin Diagonales", "Grafo con Diagonales"},   // null para YES, NO y CANCEL
            "Grafo sin Diagonales");

            
            boolean diagonales;
            /* Condicion para saber si el grafo cuenta con diagonales o no*/
            if (decision ==1){
                diagonales=true;
            }
            else diagonales=false;

            /* Condicion para saber el tamanho de los grafos*/
            int tamanio = JOptionPane.showOptionDialog(
            null,
            "Seleccione condicion", 
            "Selector de opciones",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,    // null para icono por defecto.
            new Object[] { "Grafo 4x4", "Grafo 15x15", "Grafo 20x20"},   // null para YES, NO y CANCEL
            "Grafo 4x4");
            
            
            /** Se ejecuta el algoritmo de camino mas corto segun el tamanho del grafo**/
            Astar astar = null;
            if (tamanio==0){
                tamanio = 4;
                astar = new Astar(tamanio, tamanio, arregloDistri1);
            }
            if (tamanio == 1){
                tamanio = 15;
                astar = new Astar(tamanio, tamanio, arregloDistri2);
            }
            if (tamanio ==2){
                tamanio = 20;
                astar = new Astar(tamanio, tamanio, arregloDistri3);
            }


            astar.calcular(diagonales);

            //mostrar parte gráfica 
            JFrame window = new JFrame();

            window.setSize(450, 450);
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            window.add(new GrafoGrafico(astar.getGrafo(),tamanio,tamanio,astar.getPath(),astar.getNodosEvaluados()));
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            
            /***************** Condicion para continuar probando algoritmos *******************/
            int seleccion = JOptionPane.showOptionDialog(
            null, // Componente padre
            "¿Desea continuar?", //Mensaje
            "Seleccione una opción", // Título
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,    // null para icono por defecto.
            new Object[] { "Si", "No"},    // null para YES, NO y CANCEL
            "Si");
            if (seleccion == 1){
                salir = true;
            }else if (seleccion == 0){
                
                /************************** Condicion para crear nuevas diagonales ***********************/
                int crearNuevasDiagonales = JOptionPane.showOptionDialog(
                null, // Componente padre
                "¿Desea crear nuevas diagonales?", //Mensaje
                "Cambiar Grafo", // Título
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,    // null para icono por defecto.
                new Object[] { "Si", "No"},    // null para YES, NO y CANCEL
                "Si");
                    
                //Se crean nuevas diagonales
                if (crearNuevasDiagonales == 0){
                    arregloDistri1 = nuevoDistri(16);
                    arregloDistri2 = nuevoDistri(225);
                    arregloDistri3 = nuevoDistri(400);
                }
            }
        }
    }
}

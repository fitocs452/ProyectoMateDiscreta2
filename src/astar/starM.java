/**
* Universidad Del Valle 
* Pablo Díaz 13203
* Adolfo Morales 13014
*/

package astar;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class starM {
    
    public static void main(String[] args) {
        
        int[] arregloDistri1 = new int[16];
        int[] arregloDistri2 = new int[225];
        int[] arregloDistri3 = new int[400];
        Random aleatorio = new Random();
        for (int i = 0; i<arregloDistri1.length; i++){
            int distribuicion= aleatorio.nextInt(2)+1;
            arregloDistri1[i] = distribuicion; 
        }
        for (int i = 0; i<arregloDistri2.length; i++){
            int distribuicion= aleatorio.nextInt(2)+1;
            arregloDistri2[i] = distribuicion; 
        }
        for (int i = 0; i<arregloDistri3.length; i++){
            int distribuicion= aleatorio.nextInt(2)+1;
            arregloDistri3[i] = distribuicion; 
        }
        boolean salir = false;
        
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

            if (decision ==1)
                diagonales=true;
            else diagonales=false;

            int tamanio = JOptionPane.showOptionDialog(
            null,
            "Seleccione condicion", 
            "Selector de opciones",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,    // null para icono por defecto.
            new Object[] { "Grafo 4x4", "Grafo 15x15", "Grafo 20x20"},   // null para YES, NO y CANCEL
            "Grafo 4x4");
            Astar astar = null;
            if (tamanio==0)
                tamanio = 4;
                astar = new Astar(tamanio, tamanio, arregloDistri1);
            if (tamanio == 1)
                tamanio = 15;
                astar = new Astar(tamanio, tamanio, arregloDistri2);
            if (tamanio ==2)
                tamanio = 20;
                astar = new Astar(tamanio, tamanio, arregloDistri3);


            astar.calcular(diagonales);

            //mostrar parte gráfica 
             JFrame window = new JFrame();

              window.setSize(450, 450);
              window.setLocationRelativeTo(null);
              window.setVisible(true);
              window.add(new GrafoGrafico(astar.getGrafo(),tamanio,tamanio,astar.getPath(),astar.getNodosEvaluados()));
              window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          }
    }

}

/**
* Universidad Del Valle 
* Pablo Díaz 13203
* Adolfo Morales 13014
*/

package astar;

import javax.swing.JOptionPane;

/**
 *
 * @author Pablo
 */
public class starM {
    
    public static void main(String[] args) {
        
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
        
        if (tamanio==0)
            tamanio = 4;
        if (tamanio == 1)
            tamanio = 15;
        if (tamanio ==2)
            tamanio = 20;
        
        Astar astar = new Astar(tamanio,tamanio);
        astar.calcular(diagonales);

    }

}

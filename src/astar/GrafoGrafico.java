/**
* Universidad Del Valle 
* Pablo Díaz 13203
*/

package astar;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JPanel;

/**
 *
 * @author Pablo
 */
public class GrafoGrafico extends JPanel{
    private Grafo grafo;
    private int EscalaX;
    private int EscalaY;
    private int ancho;
    private int alto;
    private List<Nodo> path;

    public GrafoGrafico(Grafo grafo, int ancho, int alto, List<Nodo> path) {
        this.grafo = grafo;
        this.ancho = ancho;
        this.alto = alto;
        this.path = path;
        this.EscalaX=400/ancho;
        this.EscalaY=400/alto;
        setSize(EscalaX * ancho, EscalaY * alto);
        setVisible(true);
    }
    
    
    private void fillRect(Graphics graphics, int x, int y) {
          graphics.fill3DRect(EscalaX*x, EscalaY*y, EscalaX, EscalaY, true);
     }
     public void paintObstacles(Graphics graphics) {
          graphics.setColor(Color.BLACK);
          
          for (int x = 0; x < alto; ++x) {
               for (int y = 0; y < ancho; ++y) {
                    if (grafo.getNodo(x, y).isIsObstaculo()) {
                         fillRect(graphics, x, y);
                    }
               }
          }
     }
    public void paintGrafo(Graphics graphics){
        graphics.setColor(Color.BLUE);
        for (int y = 0; y < alto; y++) 
       {
           for (int x = 0; x < ancho; x++)
           {
             fillRect(graphics,x,y);

           }
       }
    }
      @Override
    public void paint(Graphics graphics) {

         graphics.setColor(Color.BLUE);
         paintGrafo(graphics);
         paintObstacles(graphics);
         paintPath(graphics);
    }
      
    private void paintPath(Graphics graphics) {
        graphics.setColor(Color.GREEN);

        
       for (Nodo n : path) {
           
           int x = n.getX(); int y = n.getY();
           fillRect(graphics, x, y);
           
           
       }
    }
}

/*
 Universidad del Valle de Guatemala
 Gustavo Adolfo Morales Martínez
 Carné: 13014
 Guatemala xx de xx de 201x
 Descripción: 
 */
package objetos;
import java.util.ArrayList;

public class Coordenadas extends ArrayList < int[] >
{
    private int xMaxima;
    private int yMaxima;
    public Coordenadas(){
        // initialise instance variables
        this(0, 0);
    }
    public Coordenadas(int xMaxima, int yMaxima){
        // initialise instance variables
        super();
        this.xMaxima = xMaxima;
        this.yMaxima = yMaxima;
    }
    public Coordenadas(int xMaxima, int yMaxima,int x, int y){
        // initialise instance variables
        super();        
        this.xMaxima = xMaxima;
        this.yMaxima = yMaxima;
        addCoordenada(x,y);
    }
    public void setXMaxima(int xMaxima){
        this.xMaxima = xMaxima;
    }
    public void setYMaxima(int yMaxima){
        this.yMaxima = yMaxima;
    }
    public void addCoordenada(int x, int y){
        if(x >= 0 && x <= xMaxima && y >= 0 && y <= yMaxima)
        {
            int[] parXY = {x, y};
            add(parXY);
        }
    }

    public int getxMaxima() {
        return xMaxima;
    }

    public int getyMaxima() {
        return yMaxima;
    }

}
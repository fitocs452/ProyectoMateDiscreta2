/*
 Universidad del Valle de Guatemala
 Gustavo Adolfo Morales Martínez
 Carné: 13014
 Guatemala xx de xx de 201x
 Descripción: 
 */
package objetos;

public class Arista{
    private int idArista;
    private String nombreArista;
    private int peso;//Distancia
    private LineaQuebrada lineaQuebrada;//Ésta es la via representada graficamente    
    private boolean habilitado;

    public Arista(){
        // initialise instance variables
        this(-1,"");
    }
    public Arista(int idArista){
        // initialise instance variables
        this(-1,"");
    }
    public Arista(int idArista,String nombreArista){
        // initialise instance variables
        this(-1,"",1);
    }
    public Arista(int idArista,String nombreArista,int peso){
        // initialise instance variables
        this.idArista = idArista;
        this.nombreArista = nombreArista;
        this.peso = peso;
        lineaQuebrada = null;
        habilitado = true;
    }
    public void setIdArista(int idArista){
        this.idArista = idArista;
    }
    public int getIdArista(){
        return idArista;
    }
    public void setNombreArista(String nombreVia){
        this.nombreArista = nombreVia;
    }
    public String getNombreArista(){
        return nombreArista;
    }
    public void setPeso(int peso){
        this.peso = peso;
    }
    public int getPeso(){
        return peso;
    }
    public void setLineaQuebrada(LineaQuebrada lineaQuebrada){
        this.lineaQuebrada = lineaQuebrada;
        if(lineaQuebrada != null){
            this.lineaQuebrada.setLongitud(peso);
        }
    }
    public LineaQuebrada getLineaQuebrada(){
        return lineaQuebrada;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
}

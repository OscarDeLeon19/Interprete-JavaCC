
package errores;

import instrucciones.Instruccion;
import tabla.Tipo;


public class Error extends Instruccion{
 
    private String enunciado;
    private Tipo tipo;
    private int fila;
    private int columna;

    public Error(Tipo id, String enunciado, Tipo tipo, int fila, int columna) {
        super(id);
        this.tipo = tipo;
        this.enunciado = enunciado;
        this.fila = fila;
        this.columna = columna;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    public String getMensaje(){
        String msj = "\n /*\n Error de tipo: " + tipo + ":\n"
                + enunciado + ". \n"
                + "Fila: " + fila + " \n"
                + "Columna " + columna + " \n*/\n";
        return msj;
    }
    
    
    
}

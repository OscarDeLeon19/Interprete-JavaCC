
package instrucciones;

import expresiones.Valor;
import tabla.TablaSimbolos;
import tabla.Tipo;


public class Imprimir extends Instruccion{
    
    private Instruccion expresion;
    private int fila;
    private int columna;

    public Imprimir(Tipo id, Instruccion expresion, int fila, int columna) {
        super(id);
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }

    public Instruccion getExpresion() {
        return expresion;
    }

    public void setExpresion(Instruccion expresion) {
        this.expresion = expresion;
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

    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        expresion.setConsola(super.getConsola());
        Instruccion val = expresion.operar(tabla);
        return val;
        
        
    }
    
    
    
    
    
}

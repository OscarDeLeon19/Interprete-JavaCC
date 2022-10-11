/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrucciones;

import tabla.TablaSimbolos;
import tabla.Tipo;


public class Retorno extends Instruccion{
    
    private Instruccion expresion;
    private int fila;
    private int columna;
    
    /**
     * Constructor de la instruccio retorno
     * @param id El identificador de la instruccion
     * @param expresion La expresion del retorno
     * @param fila La fila en donde se declara
     * @param columna La columna en donde se declara
     */
    public Retorno(Tipo id, Instruccion expresion, int fila, int columna) {
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

    /**
     * Opera la instruccion
     * @param tabla La tabla de simbolos
     * @return Una instruccion
     */
    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        // Solo opera la instruccion y la devuelve
        expresion.setConsola(super.getConsola());
        if(expresion != null){
            Instruccion valor = expresion.operar(tabla);
            return valor;
        }
        return null;
        
    }
    
    
    
    
}

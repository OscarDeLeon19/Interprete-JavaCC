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

    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        if(expresion != null){
            Instruccion valor = expresion.operar(tabla);
            return valor;
        }
        return null;
        
    }
    
    
    
    
}

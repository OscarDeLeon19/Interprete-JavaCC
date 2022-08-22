package instrucciones;

import java.util.ArrayList;
import tabla.TablaSimbolos;
import tabla.Tipo;

public class Funcion extends Instruccion {

    private String identificador;
    private ArrayList<Declaracion> parametros;
    private ArrayList<Instruccion> instrucciones;
    private Tipo tipo;
    private int fila;
    private int columna;

    public Funcion(Tipo id, String identificador, ArrayList<Declaracion> parametros, ArrayList<Instruccion> instrucciones, Tipo tipo, int fila, int columna) {
        super(id);
        this.identificador = identificador;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public ArrayList<Declaracion> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<Declaracion> parametros) {
        this.parametros = parametros;

    }

    public ArrayList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(ArrayList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;

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
        System.out.println("NADA"); //To change body of generated methods, choose Tools | Templates.
        System.out.println(identificador);
        for (Instruccion ins : instrucciones) {
            ins.operar(tabla);
        }
        
        return null;
    }

}

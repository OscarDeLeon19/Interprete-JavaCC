
package instrucciones;

import tabla.Simbolo;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;

public class Iteracion extends Instruccion{
    
    private String identificador = null;
    private Tipo tipo;
    private int fila;
    private int columna;

    public Iteracion(Tipo tipo, int fila, int columna, Tipo id) {
        super(id);
        this.fila = fila;
        this.columna = columna;
        this.tipo = tipo;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
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

    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        Simbolo variable = tabla.obtenerSimbolo(identificador);
        if (variable == null){
            return new Error(Tipo.ERROR, "No se enotro la variable del ciclo", Tipo.SEMANTICO, fila, columna);
        }
        
        if (variable.getTipo() != Tipo.ENTERO){
            return new Error(Tipo.ERROR, "Solo se pueden iterar valores enteros", Tipo.SEMANTICO, fila, columna);
        }
        
        if(this.tipo == Tipo.INCREMENTO){
            int valor = (int) variable.getValor() ;
            int nuevoValor = valor +1;
            variable.setValor(nuevoValor);
        } else {
            int valor = (int) variable.getValor() ;
            int nuevoValor = valor -1;
            variable.setValor(nuevoValor);
        }
        return new Instruccion();
    }
    
    
}


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

    /**
     * Constructor de la instruccion iteracion
     * @param tipo El tipo de iteracion
     * @param fila La fila en la que se declara
     * @param columna La columna en donde se declara
     * @param id El id de la instruccion
     */
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
        // Obtiene el simbolo de la tabla de simbolos
        Simbolo variable = tabla.obtenerSimbolo(identificador);
        if (variable == null){
            return new Error(Tipo.ERROR, "No se enotro la variable del ciclo", Tipo.SEMANTICO, fila, columna);
        }
        
        // Si es diferente de un entero ocurrira un error
        if (variable.getTipo() != Tipo.ENTERO){
            return new Error(Tipo.ERROR, "Solo se pueden iterar valores enteros", Tipo.SEMANTICO, fila, columna);
        }
        
        // Si el tipo de iteracion es de incremento, la variable suma 1
        // Si no a la variable se le resta un 1
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

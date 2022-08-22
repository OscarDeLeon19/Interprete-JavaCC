package expresiones;

import instrucciones.Instruccion;
import tabla.Simbolo;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;

public class Valor extends Instruccion {

    private Object valor;
    private Tipo tipo;
    private int fila;
    private int columna;

    public Valor(Tipo id, Object valor, Tipo tipo, int fila, int columna) {
        super(id);
        this.valor = valor;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;

    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
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
        if (tipo == Tipo.ENTERO) {
            return new Valor(Tipo.VALOR, Integer.parseInt(String.valueOf(valor)), Tipo.ENTERO, this.fila, this.columna);
        } else if (tipo == Tipo.DECIMAL) {
            return new Valor(Tipo.VALOR, Double.parseDouble(String.valueOf(valor)), Tipo.DECIMAL, this.fila, this.columna);
        } else if (tipo == Tipo.BOOLEAN) {
            return this;
        } else if (tipo == Tipo.CADENA) {
            String val = String.valueOf(valor);
            val = val.substring(1, val.length() - 1);
            return new Valor(Tipo.VALOR, val, Tipo.CADENA, this.fila, this.columna);
        } else if (tipo == Tipo.IDENTIFICADOR) {
            Simbolo simbolo = tabla.obtenerSimbolo(String.valueOf(valor));
            if (simbolo != null) {
                return new Valor(Tipo.VALOR, simbolo.getValor(), simbolo.getTipo(), this.fila, this.columna);
            } else {
                return new Error(Tipo.ERROR, "No se encontro el simbolo solicitado", Tipo.SEMANTICO, fila, columna);
            }
        } else {
            return new Error(Tipo.ERROR, "El tipo del valor no se puede operar", Tipo.SEMANTICO, fila, columna);
        }

    }

}

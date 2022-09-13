package instrucciones;

import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;
import expresiones.Relacional;
import expresiones.Valor;
import tabla.Simbolo;

public class Declaracion extends Instruccion {

    private String identificador;
    private Instruccion valor;
    private Tipo tipo;
    private int fila;
    private int columna;

    public Declaracion(Tipo id, String identificador, Instruccion valor, Tipo tipo, int fila, int columna) {
        super(id);
        this.identificador = identificador;
        this.valor = valor;
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

    public Instruccion getValor() {
        return valor;
    }

    public void setValor(Instruccion valor) {
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

    public Instruccion operar(TablaSimbolos tabla) {
        Instruccion val = null;
        valor.setConsola(super.getConsola());
        if (this.valor != null) {
            val = valor.operar(tabla);
            if (val instanceof Error) {
                return val;
            }

            Valor valorExpresion = (Valor) val;
            if (comprobarTipo(valorExpresion) == true) {
                return new Error(Tipo.ERROR, "No se puede asignar un " + valorExpresion.getTipo() + " a una variable"
                        + tipo, Tipo.SEMANTICO, fila, columna);
            }
        }

        String id = identificador;
        boolean comprobacion = tabla.buscarSimboloLocal(id);
        if (comprobacion == false) {
            if (val == null) {
                tabla.agregarSimbolo(new Simbolo(tipo, id, null, fila, columna));
            } else {
                Valor valExpresion = (Valor) val;
                tabla.agregarSimbolo(new Simbolo(tipo, id, valExpresion.getValor(), fila, columna));
            }
            return new Instruccion();
        } else {
            return new Error(Tipo.ERROR, "La variable " + id +" ya esta establecida", Tipo.SEMANTICO, fila, columna);
        }
    }

    public boolean comprobarTipo(Valor valor) {
        return tipo != valor.getTipo();
    }

}

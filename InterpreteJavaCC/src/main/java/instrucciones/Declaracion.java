package instrucciones;

import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;
import expresiones.Valor;
import tabla.Simbolo;

public class Declaracion extends Instruccion {

    private String identificador;
    private Instruccion valor;
    private Tipo tipo;
    private int fila;
    private int columna;

    /**
     * Constructor de la instruccion declaracion
     * @param id El id de la declaracion
     * @param identificador El identificador que se declarara
     * @param valor El valor que se le asignara al simbolo
     * @param tipo El tipo de simbolo
     * @param fila La fila en donde se declara
     * @param columna La columna en donde se declara
     */
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
            // Si el valor es diferente de nulo, se operará
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
        // Se comprueba que el identificador no sea el mismo que el de otro simbolo.
        String id = identificador;
        boolean comprobacion = tabla.buscarSimboloLocal(id);
        if (comprobacion == false) {
            // Si el identificador no existe, se crea un nuevo simbolo en la tabla de simbolos.
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

    /**
     * Comprueba que el tipo de la declaracion sea el mismo que el del valor
     * @param valor El valor con el que se hara la comparacion
     * @return Si es el mismo o no.
     */
    public boolean comprobarTipo(Valor valor) {
        return tipo != valor.getTipo();
    }

}

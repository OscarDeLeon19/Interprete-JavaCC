package expresiones;

import instrucciones.Instruccion;
import tabla.TablaSimbolos;
import tabla.Tipo;

public class Logica extends Instruccion {

    private Instruccion valIzq;
    private Instruccion valDer;
    private Tipo tipo;
    private int fila;
    private int columna;

    /**
     * Constructor de la instruccion Logica
     * @param id El id de la instruccion
     * @param valIzq El valor de la izquierda 
     * @param valDer El valor de la derecha
     * @param tipo El tipo de la instruccion
     * @param fila La fila en la que se declara la instruccion
     * @param columna La columna en la que se declara la instruccion
     */
    public Logica(Tipo id, Instruccion valIzq, Instruccion valDer, Tipo tipo, int fila, int columna) {
        super(id);
        this.valIzq = valIzq;
        this.valDer = valDer;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    public Instruccion getValIzq() {
        return valIzq;
    }

    public void setValIzq(Instruccion valIzq) {
        this.valIzq = valIzq;
    }

    public Instruccion getValDer() {
        return valDer;
    }

    public void setValDer(Instruccion valDer) {
        this.valDer = valDer;
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

    /**
     * Se opera la instruccion Logica
     * @param tabla La tabla de simbolos
     * @return Una instruccion
     */
    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        // Se comprueba que ambos valores no sean nulos
        if (valIzq != null && valDer != null) {
            // Opera ambos valores, si hay algun error la instruccion lo retornara.
            Instruccion nodoIzq = valIzq.operar(tabla);
            Instruccion nodoDer = valDer.operar(tabla);
            if (nodoIzq instanceof errores.Error) {
                return nodoIzq;
            }

            if (nodoDer instanceof errores.Error) {
                return nodoDer;
            }

            // Se castea los valores
            Valor val1 = (Valor) nodoIzq;
            Valor val2 = (Valor) nodoDer;

            // Se comprueba que ambos tipos sean iguales y valores booleanos.
            if (val1.getTipo() == val2.getTipo()) {
                if (val1.getTipo() == Tipo.BOOLEAN) {
                    // se obtienen ambos valores.
                    boolean number1 = (boolean) val1.getValor();
                    boolean number2 = (boolean) val2.getValor();
                    // Dependiendo de el tipo de valor se retornaran diferentes valores
                    if (tipo == Tipo.AND) {
                        return new Valor(Tipo.VALOR, number1 && number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.OR) {
                        return new Valor(Tipo.VALOR, number1 || number2, Tipo.BOOLEAN, fila, columna);
                    } else {
                        return new errores.Error(Tipo.ERROR, "La operacion " + tipo + " no se puede realizar", Tipo.SEMANTICO, fila, columna);
                    }
                } else {
                    return new errores.Error(Tipo.ERROR, "No se puede evaluar algo que no sea un boolean ", Tipo.SEMANTICO, fila, columna);
                }
            } else {
                return new errores.Error(Tipo.ERROR, "No se pueden operar valores de distinto tipo", Tipo.SEMANTICO, fila, columna);
            }
        } else {
            // Si uno de los valores es nulo, se operara una funcion Not
            if (valIzq != null && valDer == null) {
                Instruccion nodoIzq = valIzq.operar(tabla);
                if (nodoIzq instanceof errores.Error) {
                    return nodoIzq;
                }
                Valor val1 = (Valor) nodoIzq;
                if (val1.getTipo() == Tipo.BOOLEAN && tipo == Tipo.NOT) {
                    boolean valor = (boolean) val1.getValor();
                    if (valor == true) {
                        return new Valor(Tipo.VALOR, false, Tipo.BOOLEAN, fila, columna);
                    } else {
                        return new Valor(Tipo.VALOR, true, Tipo.BOOLEAN, fila, columna);
                    }
                } else {
                    return new errores.Error(Tipo.ERROR, "No se puede evaluar el " + val1.getTipo(), Tipo.SEMANTICO, fila, columna);
                }
            } else {
                return new errores.Error(Tipo.ERROR, "Error al realizar la operacion", Tipo.SEMANTICO, fila, columna);
            }
        }
    }
}

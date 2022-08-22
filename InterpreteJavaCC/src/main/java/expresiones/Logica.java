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

    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        if (valIzq != null && valDer != null) {
            Instruccion nodoIzq = valIzq.operar(tabla);
            Instruccion nodoDer = valDer.operar(tabla);
            if (nodoIzq instanceof errores.Error) {
                return nodoIzq;
            }

            if (nodoDer instanceof errores.Error) {
                return nodoDer;
            }

            Valor val1 = (Valor) nodoIzq;
            Valor val2 = (Valor) nodoDer;

            if (val1.getTipo() == val2.getTipo()) {
                if (val1.getTipo() == Tipo.BOOLEAN) {
                    boolean number1 = (boolean) val1.getValor();
                    boolean number2 = (boolean) val2.getValor();
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

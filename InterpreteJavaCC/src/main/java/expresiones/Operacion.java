package expresiones;

import instrucciones.Instruccion;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;

public class Operacion extends Instruccion {

    private Instruccion valIzq;
    private Instruccion valDer;
    private Tipo tipo;
    private int fila;
    private int columna;

    public Operacion(Tipo id, Instruccion valIzq, Instruccion valDer, Tipo tipo, int fila, int columna) {
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
        if (tipo == Tipo.MENOS) {
            valIzq.setConsola(super.getConsola());
            Instruccion nodoIzq = valIzq.operar(tabla);
            if (nodoIzq instanceof Error) {
                return nodoIzq;
            }
            Valor val1 = (Valor) nodoIzq;
            
            if(val1.getTipo() == Tipo.ENTERO){
                int izq = (int) val1.getValor();
                return new Valor(Tipo.VALOR, -izq, Tipo.ENTERO, fila, columna);
            } else if(val1.getTipo() == Tipo.DECIMAL){
                double izq = (double) val1.getValor();
                return new Valor(Tipo.VALOR, -izq, Tipo.DECIMAL, fila, columna);
            } else {
                return new Error(Tipo.ERROR, "Error al realizar la operacion", Tipo.SEMANTICO, fila, columna);
            }
        } else {
            valIzq.setConsola(super.getConsola());
            valDer.setConsola(super.getConsola());
            Instruccion nodoIzq = valIzq.operar(tabla);
            Instruccion nodoDer = valDer.operar(tabla);

            
            
            
            if (nodoIzq instanceof Error) {
                return nodoIzq;
            }

            if (nodoDer instanceof Error) {
                return nodoDer;
            }

            Valor val1 = (Valor) nodoIzq;
            Valor val2 = (Valor) nodoDer;
            if (tipo == Tipo.SUMA) {
                if (val1.getTipo() == Tipo.DECIMAL && val2.getTipo() == Tipo.DECIMAL) {
                    double izq = (double) val1.getValor();
                    double der = (double) val2.getValor();
                    double suma = izq + der;
                    return new Valor(Tipo.VALOR, suma, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.DECIMAL && val2.getTipo() == Tipo.ENTERO) {
                    double izq = (double) val1.getValor();
                    int der = (int) val2.getValor();
                    double suma = izq + der;
                    return new Valor(Tipo.VALOR, suma, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.DECIMAL && val2.getTipo() == Tipo.CADENA) {
                    String izq = String.valueOf(val1.getValor());
                    String der = String.valueOf(val2.getValor());
                    String suma = izq + der;
                    return new Valor(Tipo.VALOR, suma, Tipo.CADENA, fila, columna);
                } else if (val1.getTipo() == Tipo.ENTERO && val2.getTipo() == Tipo.DECIMAL) {
                    int izq = (int) val1.getValor();
                    double der = (double) val2.getValor();
                    double suma = izq + der;
                    return new Valor(Tipo.VALOR, suma, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.ENTERO && val2.getTipo() == Tipo.ENTERO) {
                    int izq = (int) val1.getValor();
                    int der = (int) val2.getValor();
                    int suma = izq + der;
                    return new Valor(Tipo.VALOR, suma, Tipo.ENTERO, fila, columna);
                } else if (val1.getTipo() == Tipo.ENTERO && val2.getTipo() == Tipo.CADENA) {
                    String izq = String.valueOf(val1.getValor());
                    String der = String.valueOf(val2.getValor());
                    String suma = izq + der;
                    return new Valor(Tipo.VALOR, suma, Tipo.CADENA, fila, columna);
                } else if (val1.getTipo() == Tipo.CADENA && val2.getTipo() == Tipo.DECIMAL) {
                    String izq = String.valueOf(val1.getValor());
                    String der = String.valueOf(val2.getValor());
                    String suma = izq + der;
                    return new Valor(Tipo.VALOR, suma, Tipo.CADENA, fila, columna);
                } else if (val1.getTipo() == Tipo.CADENA && val2.getTipo() == Tipo.ENTERO) {
                    String izq = String.valueOf(val1.getValor());
                    String der = String.valueOf(val2.getValor());
                    String suma = izq + der;
                    return new Valor(Tipo.VALOR, suma, Tipo.CADENA, fila, columna);
                } else if (val1.getTipo() == Tipo.CADENA && val2.getTipo() == Tipo.CADENA) {
                    String izq = String.valueOf(val1.getValor());
                    String der = String.valueOf(val2.getValor());
                    String suma = izq + der;
                    return new Valor(Tipo.VALOR, suma, Tipo.CADENA, fila, columna);
                } else {
                    return new Error(Tipo.ERROR, "No se puede sumar un " + val1.getTipo() + " con un " + val2.getTipo(), Tipo.SEMANTICO, fila, columna);
                }
            } else if (tipo == Tipo.RESTA) {
                if (val1.getTipo() == Tipo.DECIMAL && val2.getTipo() == Tipo.DECIMAL) {
                    double izq = (double) val1.getValor();
                    double der = (double) val2.getValor();
                    double resta = izq - der;
                    return new Valor(Tipo.VALOR, resta, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.DECIMAL && val2.getTipo() == Tipo.ENTERO) {
                    double izq = (double) val1.getValor();
                    int der = (int) val2.getValor();
                    double resta = izq - der;
                    return new Valor(Tipo.VALOR, resta, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.ENTERO && val2.getTipo() == Tipo.DECIMAL) {
                    int izq = (int) val1.getValor();
                    double der = (double) val2.getValor();
                    double resta = izq - der;
                    return new Valor(Tipo.VALOR, resta, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.ENTERO && val2.getTipo() == Tipo.ENTERO) {
                    int izq = (int) val1.getValor();
                    int der = (int) val2.getValor();
                    int resta = izq - der;
                    return new Valor(Tipo.VALOR, resta, Tipo.ENTERO, fila, columna);
                } else {
                    return new Error(Tipo.ERROR, "No se puede restar un " + val1.getTipo() + " con un " + val2.getTipo(), Tipo.SEMANTICO, fila, columna);
                }
            } else if (tipo == Tipo.MULTIPLICACION) {
                if (val1.getTipo() == Tipo.DECIMAL && val2.getTipo() == Tipo.DECIMAL) {
                    double izq = (double) val1.getValor();
                    double der = (double) val2.getValor();
                    double multiplicacion = izq * der;
                    return new Valor(Tipo.VALOR, multiplicacion, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.DECIMAL && val2.getTipo() == Tipo.ENTERO) {
                    double izq = (double) val1.getValor();
                    int der = (int) val2.getValor();
                    double multiplicacion = izq * der;
                    return new Valor(Tipo.VALOR, multiplicacion, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.ENTERO && val2.getTipo() == Tipo.DECIMAL) {
                    int izq = (int) val1.getValor();
                    double der = (double) val2.getValor();
                    double multiplicacion = izq * der;
                    return new Valor(Tipo.VALOR, multiplicacion, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.ENTERO && val2.getTipo() == Tipo.ENTERO) {
                    int izq = (int) val1.getValor();
                    int der = (int) val2.getValor();
                    int multiplicacion = izq * der;
                    return new Valor(Tipo.VALOR, multiplicacion, Tipo.ENTERO, fila, columna);
                } else {
                    return new Error(Tipo.ERROR, "No se puede multiplicar un " + val1.getTipo() + " con un " + val2.getTipo(), Tipo.SEMANTICO, fila, columna);
                }
            } else if (tipo == Tipo.DIVISION) {
                if (val1.getTipo() == Tipo.DECIMAL && val2.getTipo() == Tipo.DECIMAL) {
                    double izq = (double) val1.getValor();
                    double der = (double) val2.getValor();
                    double division = izq / der;
                    return new Valor(Tipo.VALOR, division, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.DECIMAL && val2.getTipo() == Tipo.ENTERO) {
                    double izq = (double) val1.getValor();
                    int der = (int) val2.getValor();
                    double division = izq / der;
                    return new Valor(Tipo.VALOR, division, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.ENTERO && val2.getTipo() == Tipo.DECIMAL) {
                    int izq = (int) val1.getValor();
                    double der = (double) val2.getValor();
                    double division = izq / der;
                    return new Valor(Tipo.VALOR, division, Tipo.DECIMAL, fila, columna);
                } else if (val1.getTipo() == Tipo.ENTERO && val2.getTipo() == Tipo.ENTERO) {
                    int izq = (int) val1.getValor();
                    int der = (int) val2.getValor();
                    int division = izq / der;
                    return new Valor(Tipo.VALOR, division, Tipo.ENTERO, fila, columna);
                } else {
                    return new Error(Tipo.ERROR, "No se puede multiplicar un " + val1.getTipo() + " con un " + val2.getTipo(), Tipo.SEMANTICO, fila, columna);
                }
            } else {
                return new Error(Tipo.ERROR, "No existe este tipo de operacion", Tipo.SEMANTICO, fila, columna);
            }
        }
    }

}

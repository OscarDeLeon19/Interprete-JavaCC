/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expresiones;

import instrucciones.Instruccion;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;

public class Relacional extends Instruccion {

    private Instruccion valIzq;
    private Instruccion valDer;
    private Tipo tipo;
    private int fila;
    private int columna;

    /**
     * Constructor de la instruccion relacional
     * @param id El id de la instruccion
     * @param valIzq El valor izquierdo de la relacion
     * @param valDer El valor derecho de la relacion.
     * @param tipo El tipo de relacion
     * @param fila
     * @param columna 
     */
    public Relacional(Tipo id, Instruccion valIzq, Instruccion valDer, Tipo tipo, int fila, int columna) {
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
            // Se operan ambos valores y se castean
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
            
            // Los tipos que se van a comparar deben ser del mismo tipo
            // Dependiendo del tipo de valores, se realizaran acciones diferentes.
            if (val1.getTipo() == val2.getTipo()) {
                if (val1.getTipo() == Tipo.CADENA) {
                    String cad1 = String.valueOf(val1.getValor());
                    String cad2 = String.valueOf(val2.getValor());
                    if (tipo == Tipo.IGUAL) {
                        return new Valor(Tipo.VALOR, cad1.equals(cad2), Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.DIFERENTE) {
                        return new Valor(Tipo.VALOR, !cad1.equals(cad2), Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MAYOR) {
                        return new Valor(Tipo.VALOR, cad1.length() > cad2.length(), Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MENOR) {
                        return new Valor(Tipo.VALOR, cad1.length() < cad2.length(), Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MAYORIGUAL) {
                        return new Valor(Tipo.VALOR, cad1.length() >= cad2.length(), Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MENORIGUAL) {
                        return new Valor(Tipo.VALOR, cad1.length() <= cad2.length(), Tipo.BOOLEAN, fila, columna);
                    } else {
                        return new Error(Tipo.ERROR, "La operacion " + tipo + " no se puede realizar", Tipo.SEMANTICO, fila, columna);
                    }
                } else if (val1.getTipo() == Tipo.ENTERO) {
                    int number1 = (int) val1.getValor();
                    int number2 = (int) val2.getValor();
                    if (tipo == Tipo.IGUAL) {
                        return new Valor(Tipo.VALOR, number1 == number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.DIFERENTE) {
                        return new Valor(Tipo.VALOR, number1 != number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MAYOR) {
                        return new Valor(Tipo.VALOR, number1 > number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MENOR) {
                        return new Valor(Tipo.VALOR, number1 < number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MAYORIGUAL) {
                        return new Valor(Tipo.VALOR, number1 >= number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MENORIGUAL) {
                        return new Valor(Tipo.VALOR, number1 <= number2, Tipo.BOOLEAN, fila, columna);
                    } else {
                        return new Error(Tipo.ERROR, "La operacion " + tipo + " no se puede realizar", Tipo.SEMANTICO, fila, columna);
                    }
                } else if (val1.getTipo() == Tipo.DECIMAL) {
                    double number1 = (double) val1.getValor();
                    double number2 = (double) val2.getValor();
                    if (tipo == Tipo.IGUAL) {
                        return new Valor(Tipo.VALOR, number1 == number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.DIFERENTE) {
                        return new Valor(Tipo.VALOR, number1 != number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MAYOR) {
                        return new Valor(Tipo.VALOR, number1 > number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MENOR) {
                        return new Valor(Tipo.VALOR, number1 < number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MAYORIGUAL) {
                        return new Valor(Tipo.VALOR, number1 >= number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.MENORIGUAL) {
                        return new Valor(Tipo.VALOR, number1 <= number2, Tipo.BOOLEAN, fila, columna);
                    } else {
                        return new Error(Tipo.ERROR, "La operacion " + tipo + " no se puede realizar", Tipo.SEMANTICO, fila, columna);
                    }
                } else if (val1.getTipo() == Tipo.BOOLEAN) {
                    boolean number1 = (boolean) val1.getValor();
                    boolean number2 = (boolean) val2.getValor();
                    if (tipo == Tipo.IGUAL) {
                        return new Valor(Tipo.VALOR, number1 == number2, Tipo.BOOLEAN, fila, columna);
                    } else if (tipo == Tipo.DIFERENTE) {
                        return new Valor(Tipo.VALOR, number1 != number2, Tipo.BOOLEAN, fila, columna);
                    } else {
                        return new Error(Tipo.ERROR, "La operacion " + tipo + " no se puede realizar", Tipo.SEMANTICO, fila, columna);
                    }
                } else {
                    return new Error(Tipo.ERROR, "No se puede evaluar el " + tipo, Tipo.SEMANTICO, fila, columna);
                }
            } else {
                return new Error(Tipo.ERROR, "No se pueden operar valores de distinto tipo", Tipo.SEMANTICO, fila, columna);
            }
        
    }
}

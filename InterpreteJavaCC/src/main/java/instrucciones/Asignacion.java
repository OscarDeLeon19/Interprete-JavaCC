package instrucciones;

import tabla.Simbolo;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;
import expresiones.Valor;

public class Asignacion extends Instruccion {

    
    private String identificador;
    private Instruccion expresion;
    private int fila;
    private int columna;

    /**
     * Construccion de la instruccion asignacion
     * @param id Id de la instruccion
     * @param identificador Identificador en donde se realizara la asignacion
     * @param expresion La expresion que se asignara
     * @param fila La fila en donde se declara la instruccion
     * @param columna La columna en donde se declara la instruccion
     */
    public Asignacion(Tipo id, String identificador, Instruccion expresion, int fila, int columna) {
        super(id);
        this.identificador = identificador;
        this.expresion = expresion;
        this.fila = fila;
        this.columna = columna;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Instruccion getExpresion() {
        return expresion;
    }

    public void setExpresion(Instruccion expresion) {
        this.expresion = expresion;
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
        // Se obtiene el simbolo de la tabla de simbolos y se castea.
        Simbolo simbolo = tabla.obtenerSimbolo(identificador);
        expresion.setConsola(super.getConsola());
        Instruccion ins = expresion.operar(tabla);
        if (ins instanceof Error) {
            return ins;
        }
        
        Valor valor = (Valor) ins;
        
        // En el programa hay casteo implicito, por lo que dependiendo de el tipo de los datos se convertiran al tipo del simbolo.
        if (simbolo.getTipo() == Tipo.CADENA) {
            if (valor.getTipo() == Tipo.CADENA) {
                simbolo.setValor(valor.getValor());
            } else if (valor.getTipo() == Tipo.DECIMAL) {
                simbolo.setValor(String.valueOf(valor.getValor()));
            } else if (valor.getTipo() == Tipo.ENTERO) {
                simbolo.setValor(String.valueOf(valor.getValor()));
            } else if (valor.getTipo() == Tipo.BOOLEAN) {
                boolean booleano = (boolean) valor.getValor();
                String nuevoValor = "0";
                if (booleano == true) {
                    nuevoValor = "1";
                }
                simbolo.setValor(nuevoValor);
            }else {
                return new Error(Tipo.ERROR, "No se puede asignar el tipo " + valor.getTipo() + " a"
                        + " la variable " + simbolo.getId(), Tipo.SEMANTICO, fila, columna);
            }
        } else if (simbolo.getTipo() == Tipo.ENTERO) {
            if (valor.getTipo() == Tipo.DECIMAL) {
                double decimal = (double) valor.getValor();
                int nuevoValor = (int) decimal;
                simbolo.setValor(nuevoValor);
            } else if (valor.getTipo() == Tipo.ENTERO) {
                simbolo.setValor(valor.getValor());
            } else if (valor.getTipo() == Tipo.BOOLEAN) {
                boolean booleano = (boolean) valor.getValor();
                int nuevoValor = 0;
                if (booleano == true) {
                    nuevoValor = 1;
                }
                simbolo.setValor(nuevoValor);
            } else {
                return new Error(Tipo.ERROR, "No se puede asignar el tipo " + valor.getTipo() + " a"
                        + " la variable " + simbolo.getId(), Tipo.SEMANTICO, fila, columna);
            }
        } else if (simbolo.getTipo() == Tipo.DECIMAL) {
            if (valor.getTipo() == Tipo.DECIMAL) {
                simbolo.setValor(valor.getValor());
            } else if (valor.getTipo() == Tipo.ENTERO) {
                int entero = (int) valor.getValor();
                double nuevoValor = entero;
                simbolo.setValor(nuevoValor);
            } else if (valor.getTipo() == Tipo.BOOLEAN) {
                boolean booleano = (boolean) valor.getValor();
                double nuevoValor = 0;
                if (booleano == true) {
                    nuevoValor = 1;
                }
                simbolo.setValor(nuevoValor);
            } else {
                return new Error(Tipo.ERROR, "No se puede asignar el tipo " + valor.getTipo() + " a"
                        + " la variable " + simbolo.getId(), Tipo.SEMANTICO, fila, columna);
            }
        }
        return new Instruccion();
        
    }

}

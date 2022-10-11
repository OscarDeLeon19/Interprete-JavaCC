package instrucciones;

import expresiones.Valor;
import java.util.ArrayList;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;
import javax.swing.JTextArea;

public class Funcion extends Instruccion {

    private String identificador;
    private ArrayList<Declaracion> parametros;
    private ArrayList<Instruccion> instrucciones;
    private Tipo tipo;
    private int fila;
    private int columna;
    private Valor valorRetorno;

    /**
     * Constructor de la instruccion Funcion
     * @param id El id de la instruccion
     * @param identificador El identificador de la funcion
     * @param parametros Los parametros de la funcion
     * @param instrucciones Las instrucciones que debe realizar
     * @param tipo El tipo de la funcion
     * @param fila La fila en donde se declara la funcion
     * @param columna La columna en donde se declara la funcion
     */
    public Funcion(Tipo id, String identificador, ArrayList<Declaracion> parametros, ArrayList<Instruccion> instrucciones, Tipo tipo, int fila, int columna) {
        super(id);
        this.identificador = identificador;
        this.parametros = parametros;
        this.instrucciones = instrucciones;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    public Valor getValorRetorno() {
        return valorRetorno;
    }

    public void setValorRetorno(Valor valorRetorno) {
        this.valorRetorno = valorRetorno;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public ArrayList<Declaracion> getParametros() {
        return parametros;
    }

    public void setParametros(ArrayList<Declaracion> parametros) {
        this.parametros = parametros;

    }

    public ArrayList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(ArrayList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
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
        // Se realiza un ciclo para operar cada una de las instrucciones
        for (Instruccion ins : instrucciones) {
            ins.setConsola(super.getConsola());
            // Si la instruccion es una funcion de imprimir entonces se imprime en la consola el valor de esta.
            if (ins instanceof Imprimir) {
                Instruccion val = ins.operar(tabla);
                if (val instanceof Valor) {
                    super.getConsola().append(String.valueOf(((Valor) val).getValor()) + "\n");
                } else {
                    return val;
                }
            } else if (ins instanceof Retorno) {
                // Si es un Retorno, se ejecuta y luego el ciclo de instrucciones se termina.
                if (tipo != Tipo.VOID) {
                    // Si la funcion es diferente de void, entonces se asigna el valor del retorno
                    // a la variable valorRetorno.
                    Instruccion retorno = ins.operar(tabla);
                    if (retorno != null) {
                        if (retorno instanceof Error) {
                            return retorno;
                        }
                        valorRetorno = (Valor) retorno;
                        break;
                    } else {
                        return new Error(Tipo.ERROR, "El retorno debe tener un valor que retornar", tipo.SEMANTICO, fila, columna);
                    }
                } else {
                    // Si la funcion es de tipo void nada mas se finaliza el ciclo
                    break;
                }
            } else if (ins instanceof Si) {
                // Si la instruccion es un Si, se comprueba que esta tenga un valor de retorno.
                // Si existe un valor de retorno entonces se asignara al valor de retorno de la funcion
                Instruccion valor = ins.operar(tabla);
                if (valor instanceof Error) {
                    return valor;
                }
                if (((Si) ins).getRetorno() != null) {
                    valorRetorno = (Valor) ((Si) ins).getRetorno();
                }
            } else {
                // La instruccion se opera con normalidad.
                Instruccion valor = ins.operar(tabla);
                if (valor instanceof Error) {
                    return valor;
                }
            }
        }
        return new Instruccion();
    }

    
    
}

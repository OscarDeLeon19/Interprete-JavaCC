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
    private JTextArea consola;
    private Valor valorRetorno;

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

    public JTextArea getConsola() {
        return consola;
    }

    public void setConsola(JTextArea consola) {
        this.consola = consola;
    }

    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        for (Instruccion ins : instrucciones) {
            if (ins instanceof Imprimir) {
                Instruccion val = ins.operar(tabla);
                if (val instanceof Valor) {
                    consola.append(String.valueOf(((Valor) val).getValor()) + "\n");
                } else {
                    return val;
                }
            } else if (ins instanceof Retorno) {
                if (tipo != Tipo.VOID) {
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
                    break;
                }
            } else {
                Instruccion valor = ins.operar(tabla);
                if (valor instanceof Error) {
                    return valor;
                }
            }
        }
        return new Instruccion();
    }

}

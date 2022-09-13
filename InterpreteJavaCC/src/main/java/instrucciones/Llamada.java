package instrucciones;

import java.util.ArrayList;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;
import expresiones.Valor;
import javax.swing.JTextArea;
import tabla.Simbolo;

public class Llamada extends Instruccion {

    private String identificador;
    private ArrayList<Instruccion> expresiones;
    private int fila;
    private int columna;

    public Llamada(Tipo id, String identificador, ArrayList<Instruccion> expresiones, int fila, int columna) {
        super(id);
        this.identificador = identificador;
        this.expresiones = expresiones;
        this.fila = fila;
        this.columna = columna;

    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public ArrayList<Instruccion> getExpresiones() {
        return expresiones;
    }

    public void setExpresiones(ArrayList<Instruccion> expresiones) {
        this.expresiones = expresiones;
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
        Funcion funcion = tabla.obtenerFuncion(identificador);
        if (funcion == null) {
            return new Error(Tipo.ERROR, "No se encontro la funcion " + identificador, Tipo.SEMANTICO, fila, columna);
        }
        TablaSimbolos nuevaTabla = new TablaSimbolos(tabla);
        if (funcion.getParametros().size() > 0) {
            for (int i = 0; i < funcion.getParametros().size(); i++) {
                Declaracion decla = funcion.getParametros().get(i);
                if (nuevaTabla.buscarSimboloLocal(decla.getIdentificador()) == false) {
                    expresiones.get(i).setConsola(super.getConsola());
                    Instruccion expresion = expresiones.get(i).operar(tabla);
                    if (expresion instanceof Error) {
                        return expresion;
                    }
//                    //decla.setValor(expresion);
//                    //Instruccion ins = decla.operar(nuevaTabla);
//                    if (ins instanceof Error) {
//                        return ins;
//                    }

                    Valor valorExpresion = (Valor) expresion;
                    System.out.println("VAL: "+valorExpresion.getValor());
                    if (valorExpresion.getTipo() != decla.getTipo()) {
                        return new Error(Tipo.ERROR, "El parametro " + decla.getIdentificador() + " tiene signo incompatible con el valor " + valorExpresion.getTipo(), Tipo.SEMANTICO, fila, columna);
                    }
                    nuevaTabla.agregarSimbolo(new Simbolo(decla.getTipo(), decla.getIdentificador(), valorExpresion.getValor(), decla.getFila(), decla.getColumna()));
                    nuevaTabla.recorrerTabla();
                } else {
                    return new Error(Tipo.ERROR, "El simbolo ya esta declarado", Tipo.SEMANTICO, fila, columna);
                }
            }
        }

        funcion.setConsola(super.getConsola());
        Instruccion ins = funcion.operar(nuevaTabla);
        if (ins instanceof Error) {
            return ins;
        }

        if (funcion.getTipo() != Tipo.VOID) {
            Instruccion val = funcion.getValorRetorno();
            if (val instanceof Error) {
                return val;
            }
            Valor valorRetorno = (Valor) val;
            if (valorRetorno.getTipo() != funcion.getTipo()) {
                return new Error(Tipo.ERROR, "El tipo que retorna es diferente del tipo de la funcion", Tipo.SEMANTICO, fila, columna);
            }
            return valorRetorno;
        } else {
            return new Instruccion();
        }
    }

    public boolean comprobarTipo(Declaracion decla, Valor valor) {
        return decla.getTipo() != valor.getTipo();
    }

}

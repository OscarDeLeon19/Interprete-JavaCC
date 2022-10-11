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

    /**
     * Constructor de la instruccion llamada
     * @param id El identificador de la instruccion
     * @param identificador El id de la funcion
     * @param expresiones Las expresiones que se mandaran como parametros
     * @param fila La fila donde se realiza la llamada
     * @param columna La columna donde se realiza la llamada.
     */
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
    
    /**
     * Operacion de la instruccion llamada
     * @param tabla La tabla de simbolos
     * @return Una instruccion
     */
    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        // Se obtiene la funcion de la tabla de simbolos
        Funcion funcion = tabla.obtenerFuncion(identificador);
        if (funcion == null) {
            return new Error(Tipo.ERROR, "No se encontro la funcion " + identificador, Tipo.SEMANTICO, fila, columna);
        }
        // Se crea la nueva tabla donde se guardaran los datos de las instrucciones de la funcion
        TablaSimbolos nuevaTabla = new TablaSimbolos(tabla);
        
        
        if(funcion.getParametros().size() != expresiones.size()){
            return new Error(Tipo.ERROR, "Debe tener la misma cantidad de parametros de la funcion", Tipo.SEMANTICO, fila, columna);
        }
        // Si hay paramteros en la funcion, se realizaran las asignaciones
        if (funcion.getParametros().size() > 0) {
            for (int i = 0; i < funcion.getParametros().size(); i++) {
                // Se crea una declaracion para crear una variable
                Declaracion decla = funcion.getParametros().get(i);
                if (nuevaTabla.buscarSimboloLocal(decla.getIdentificador()) == false) {
                    expresiones.get(i).setConsola(super.getConsola());
                    Instruccion expresion = expresiones.get(i).operar(tabla);
                    if (expresion instanceof Error) {
                        return expresion;
                    }

                    Valor valorExpresion = (Valor) expresion;
                    if (valorExpresion.getTipo() != decla.getTipo()) {
                        return new Error(Tipo.ERROR, "El parametro " + decla.getIdentificador() + " tiene signo incompatible con el valor " + valorExpresion.getTipo(), Tipo.SEMANTICO, fila, columna);
                    }
                    // Se agrega la nueva variable a la tabla de simbolos nueva
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
        // Si la funcion es diferente de Void, se operara el return y se devolverá.
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

    /**
     * Compruebaque el tipo de declaracion y funcion sea diferente
     * @param decla La declaracion 
     * @param valor El valor a comprobar
     * @return Si es igual o diferente
     */
    public boolean comprobarTipo(Declaracion decla, Valor valor) {
        return decla.getTipo() != valor.getTipo();
    }

}

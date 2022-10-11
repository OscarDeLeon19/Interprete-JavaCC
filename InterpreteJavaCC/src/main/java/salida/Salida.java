package salida;

import instrucciones.Instruccion;
import java.util.ArrayList;
import tabla.TablaSimbolos;
import errores.Error;
import instrucciones.Funcion;
import javax.swing.JTextArea;
import tabla.Tipo;

public class Salida {

    private ArrayList<Instruccion> instrucciones = new ArrayList<Instruccion>();
    private JTextArea consola;
    private boolean hayError = false;

    public Salida() {
    }

    public ArrayList<Instruccion> getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(ArrayList<Instruccion> instrucciones) {
        this.instrucciones = instrucciones;
    }

    public void agregarInstruccion(Instruccion ins) {
        instrucciones.add(ins);
    }

    public JTextArea getConsola() {
        return consola;
    }

    public void setConsola(JTextArea consola) {
        this.consola = consola;
    }

    /**
     * Agrega un error lexico al area de texto.
     * @param error El error que se mostrará
     * @param fila La fila en donde se muestra el error
     * @param columna La columna en donde se muestra el error
     */
    public void agregarErrorLexico(String error, int fila, int columna) {
        hayError = true;
        Error errors = new Error(Tipo.ERROR, error, Tipo.LEXICO, fila, columna);
        consola.append(errors.getMensaje());
    }
    
    /**
     * Agrega un error sintactico a la consola
     * @param error El error que se mostrará
     * @param fila La fila en done surge el error
     * @param columna La columna en donde surge el error
     * @param expected Los tokens que se esperan
     */
    public void agregarErrorSintactico(String error, int fila, int columna, String expected) {
        hayError = true;
        Error errors = new Error(Tipo.ERROR, error, Tipo.SINTACTICO, fila, columna);
        consola.append(errors.getMensaje());
        consola.append("Se esperaba cualquiera de los siguientes tokens: " + expected);
    }

    /**
     * Opera la salida despues de haber analizado el codigo
     */
    public void operarSalida() {
        if (hayError == false) {
            TablaSimbolos tabla = new TablaSimbolos(null);
            for (Instruccion instruccion : instrucciones) {
                if (instruccion instanceof Funcion) {

                    tabla.agregarFuncion((Funcion) instruccion);
                } else {
                    Instruccion operada = instruccion.operar(tabla);
                    if (operada instanceof Error) {
                        consola.append(((Error) operada).getMensaje());
                    }
                }
            }
            ejecutarPrincipal(tabla);
        }
        //tabla.recorrerTabla();
    }

    /**
     * Ejecuta la funcion principal del codigo. Si no hay, genera un error
     * @param tabla La tabla de simbolos que se ejecutara
     */
    public void ejecutarPrincipal(TablaSimbolos tabla) {
        System.out.println(tabla.getFunciones().size());
        Funcion funcion = tabla.obtenerFuncion("Principal");
        if (funcion == null) {
            Error err = new Error(Tipo.ERROR, "No hay funcion principal", Tipo.SEMANTICO, 0, 0);
            consola.append(err.getEnunciado());
        } else {
            TablaSimbolos nuevaTabla = new TablaSimbolos(tabla);
            funcion.setConsola(consola);
            Instruccion ins = funcion.operar(nuevaTabla);
            if (ins instanceof Error) {
                consola.append(((Error) ins).getMensaje());
            }
        }
    }

}

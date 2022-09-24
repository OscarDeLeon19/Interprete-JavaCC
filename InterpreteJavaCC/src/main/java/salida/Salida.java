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

    
    public void agregarErrorLexico(String error, int fila, int columna) {
        hayError = true;
        Error errors = new Error(Tipo.ERROR, error, Tipo.LEXICO, fila, columna);
        consola.append(errors.getMensaje());
    }
    
    public void agregarErrorSintactico(String error, int fila, int columna, String expected) {
        hayError = true;
        Error errors = new Error(Tipo.ERROR, error, Tipo.SINTACTICO, fila, columna);
        consola.append(errors.getMensaje());
        consola.append("Se esperaba cualquiera de los siguientes tokens: " + expected);
    }

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

    public void ejecutarPrincipal(TablaSimbolos tabla) {
        System.out.println(tabla.getFunciones().size());
        Funcion funcion = tabla.obtenerFuncion("Principal");
        if (funcion == null) {
            //ERROR
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

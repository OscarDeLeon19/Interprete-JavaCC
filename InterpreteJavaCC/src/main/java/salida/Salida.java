package salida;

import instrucciones.Instruccion;
import java.util.ArrayList;
import tabla.TablaSimbolos;
import errores.Error;
import instrucciones.Funcion;

public class Salida {

    ArrayList<Instruccion> instrucciones = new ArrayList<Instruccion>();

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

    public void operarSalida() {
        TablaSimbolos tabla = new TablaSimbolos(null);
        for (Instruccion instruccion : instrucciones) {
            if (instruccion instanceof Funcion) {
                tabla.agregarFuncion((Funcion) instruccion);
            } else {
                Instruccion operada = instruccion.operar(tabla);
                if (operada instanceof Error) {
                    System.out.println(((Error) operada).getEnunciado() + " Fila: " + ((Error) operada).getFila());
                }
            }
        }
        ejecutarPrincipal(tabla);
        tabla.recorrerTabla();
    }
    
    public void ejecutarPrincipal(TablaSimbolos tabla){
        Funcion funcion = tabla.obtenerFuncion("Principal");
        if(funcion == null){
            //ERROR
        } else {
            TablaSimbolos nuevaTabla = new TablaSimbolos(tabla);
            funcion.operar(tabla);
        }
    }

}

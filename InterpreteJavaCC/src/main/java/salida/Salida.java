package salida;

import instrucciones.Instruccion;
import java.util.ArrayList;
import tabla.TablaSimbolos;
import errores.Error;
import instrucciones.Funcion;
import javax.swing.JTextArea;

public class Salida {

    private ArrayList<Instruccion> instrucciones = new ArrayList<Instruccion>();
    private JTextArea consola;

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

    public void operarSalida() {
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
        //tabla.recorrerTabla();
    }
    
    public void ejecutarPrincipal(TablaSimbolos tabla){
        Funcion funcion = tabla.obtenerFuncion("Principal");
        if(funcion == null){
            //ERROR
        } else {
            TablaSimbolos nuevaTabla = new TablaSimbolos(tabla);
            funcion.setConsola(consola);
            Instruccion ins = funcion.operar(nuevaTabla);
            if(ins instanceof Error){
                consola.append(((Error) ins).getMensaje());
            }
        }
    }

}

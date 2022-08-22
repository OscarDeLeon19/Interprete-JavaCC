
package salida;

import instrucciones.Instruccion;
import java.util.ArrayList;
import tabla.TablaSimbolos;
import errores.Error;


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
    
    public void agregarInstruccion(Instruccion ins){
        instrucciones.add(ins);
    }
    
    public void operarSalida(){
        TablaSimbolos tabla = new TablaSimbolos(null);
        for (Instruccion instruccion : instrucciones) {
            Instruccion operada = instruccion.operar(tabla);
            if(operada instanceof Error){
                System.out.println(((Error) operada).getEnunciado() + " Fila: " + ((Error) operada).getFila());
            }
        }
        tabla.recorrerTabla();
    }
    
    
    
    
    
}

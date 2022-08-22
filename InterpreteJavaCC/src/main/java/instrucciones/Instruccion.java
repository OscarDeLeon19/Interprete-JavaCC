/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrucciones;

import tabla.TablaSimbolos;
import tabla.Tipo;

/**
 *
 * @author oscar
 */
public class Instruccion {
    
    private Tipo id;

    public Tipo getId() {
        return id;
    }

    public void setId(Tipo id) {
        this.id = id;
    }

    public Instruccion(Tipo id) {
        this.id = id;
    }

    public Instruccion() {
    }
    
    public Instruccion operar(TablaSimbolos tabla){
        return null;
    }
    
    
}

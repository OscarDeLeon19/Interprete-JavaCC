/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrucciones;

import javax.swing.JTextArea;
import tabla.TablaSimbolos;
import tabla.Tipo;

/**
 *
 * @author oscar
 */
public class Instruccion {
    
    private Tipo id;
    private JTextArea consola;

    public Tipo getId() {
        return id;
    }

    public void setId(Tipo id) {
        this.id = id;
    }

    public JTextArea getConsola() {
        return consola;
    }

    public void setConsola(JTextArea consola) {
        this.consola = consola;
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

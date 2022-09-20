
package instrucciones;

import tabla.TablaSimbolos;
import tabla.Tipo;


public class Continuar extends Instruccion{

    public Continuar(Tipo id) {
        super(id);
    }

    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        return new Instruccion(); 
    }
    
    
    
}

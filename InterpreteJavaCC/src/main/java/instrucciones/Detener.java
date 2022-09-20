
package instrucciones;

import tabla.TablaSimbolos;
import tabla.Tipo;


public class Detener extends Instruccion{

    public Detener(Tipo id) {
        super(id);
    }

    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        return new Instruccion();
    }
    
    
    
    
}

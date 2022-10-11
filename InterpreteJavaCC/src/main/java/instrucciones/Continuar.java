
package instrucciones;

import tabla.TablaSimbolos;
import tabla.Tipo;


public class Continuar extends Instruccion{

    /**
     * Constructor de la instruccion continuar
     * @param id El id de la instruccion
     */
    public Continuar(Tipo id) {
        super(id);
    }

    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        // No realiza nada.
        return new Instruccion(); 
    }
    
    
    
}


import expresiones.Operacion;
import expresiones.Valor;
import instrucciones.Instruccion;
import tabla.Tipo;
import errores.Error;


public class Prueba {
    
    public static void main(String[] args) {
        
        Valor val1 = new Valor(Tipo.VALOR, 25, Tipo.ENTERO, 5, 5);
        Valor val2 = new Valor(Tipo.VALOR, 5.5, Tipo.DECIMAL, 5, 5);
        Operacion operacion = new Operacion(Tipo.OPERACION, val1, val2, Tipo.DIVISION, 3, 3);
        
        Instruccion ins = operacion.operar(null);
        if(ins instanceof Valor){
            Valor v = (Valor) ins;
            System.out.println(String.valueOf(v.getValor()));
        } else {
            Error e = (Error) ins;
            System.out.println(e.getEnunciado());
        }
        
    }
    
}

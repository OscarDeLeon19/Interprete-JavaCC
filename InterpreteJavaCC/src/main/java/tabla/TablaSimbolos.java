package tabla;

import instrucciones.Funcion;
import java.util.ArrayList;

public class TablaSimbolos {

    private TablaSimbolos padre = null;
    private ArrayList<Simbolo> simbolos = new ArrayList<>();
    private ArrayList<Funcion> funciones = new ArrayList<>();

    public TablaSimbolos(TablaSimbolos padre) {
        this.padre = padre;
    }

    public TablaSimbolos getPadre() {
        return padre;
    }

    public void setPadre(TablaSimbolos padre) {
        this.padre = padre;
    }

    public ArrayList<Simbolo> getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(ArrayList<Simbolo> simbolos) {
        this.simbolos = simbolos;
    }

    public ArrayList<Funcion> getFunciones() {
        return funciones;
    }

    public void setFunciones(ArrayList<Funcion> funciones) {
        this.funciones = funciones;
    }

    
    
    
    public Simbolo obtenerSimbolo(String id) {
        for (Simbolo simbolo : simbolos) {
            if (simbolo.getId().equals(id)) {
                return simbolo;
            }
        }
        if (padre != null) {
            return padre.obtenerSimbolo(id);
        }
        return null;
    }

    public boolean buscarSimbolo(String id) {
        for (Simbolo simbolo : simbolos) {
            if (simbolo.getId().equals(id)) {
                return true;
            }
        }
        if (padre != null) {
            return padre.buscarSimbolo(id);
        }
        return false;
    }

    public boolean buscarSimboloLocal(String id) {
        for (Simbolo simbolo : simbolos) {
            if (simbolo.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void agregarSimbolo(Simbolo nuevaVariable) {
        simbolos.add(nuevaVariable);
    }

    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

    public Funcion obtenerFuncion(String id) {
        for (Funcion funcion : funciones) {
            if (funcion.getIdentificador().equals(id)) {
                return funcion;
            }
        }
        if (padre != null) {
            return padre.obtenerFuncion(id);
        }
        return null;
    }

    public Boolean buscarFuncion(String id) {
        
        for (Funcion funcion : funciones) {
            if (funcion.getIdentificador().equals(id)) {
                return true;
            }
        }
        
        if (padre != null) {
            return padre.buscarFuncion(id);
        }
        return false;
    }

    public void recorrerTabla() {
        
        System.out.println("Tabla de simbolos");
        for (Simbolo simbolo : simbolos) {
            if (simbolo.getTipo() == Tipo.ENTERO) {
                System.out.println(simbolo.getId());
                int valor = Integer.parseInt(String.valueOf(simbolo.getValor()));
                System.out.println("Val: " + valor);
                System.out.println("");
            } else if (simbolo.getTipo() == Tipo.DECIMAL) {
                System.out.println(simbolo.getId());
                System.out.println("Val: " + (double) simbolo.getValor());
                System.out.println("");
            } else {
                System.out.println(simbolo.getId());
                System.out.println("Val: " + String.valueOf(simbolo.getValor()));
                System.out.println("");
            }
        }
    }

}

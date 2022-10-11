package tabla;

import instrucciones.Funcion;
import java.util.ArrayList;

public class TablaSimbolos {

    private TablaSimbolos padre = null;
    private ArrayList<Simbolo> simbolos = new ArrayList<>();
    private ArrayList<Funcion> funciones = new ArrayList<>();

    /**
     * Constructor de la tabla de simbolos
     * @param padre La tabla padre
     */
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

    /**
     * Obtiene un simbolo de la tabla
     * @param id El identificador del simbolo
     * @return El simbolo encontrado
     */
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

    /**
     * Busca un simbolo en la tabla de simbolos
     * @param id El id del simbolo
     * @return Retorna si el simbolo existe o no.
     */
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

    /**
     * Busca un simbolo unicamente en la tabla de simbolos actual
     * @param id El identificador del simbolo
     * @return El retorno si esta el simbolo o no
     */
    public boolean buscarSimboloLocal(String id) {
        for (Simbolo simbolo : simbolos) {
            if (simbolo.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Agrega un simbolo a la tabla de simbolos.
     * @param nuevaVariable El nuevo simbolo
     */
    public void agregarSimbolo(Simbolo nuevaVariable) {
        simbolos.add(nuevaVariable);
    }

    /**
     * Agrega una funcion a la tabla de simbolos
     * @param funcion La funcion que se agregará
     */
    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }

    /**
     * Devuelve una funcion de la tabla de simbolos
     * @param id El identificador de la funcion
     * @return El retorno de la funcion.
     */
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

    /**
     * Busca una funcion en la tabla de simbolos
     * @param id El identificador de la funcion
     * @return El retorno de la funcion.
     */
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

}

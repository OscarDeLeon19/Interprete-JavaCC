package expresiones;

import instrucciones.Instruccion;
import tabla.Simbolo;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;
import instrucciones.Llamada;

public class Valor extends Instruccion {

    private Object valor;
    private Tipo tipo;
    private int fila;
    private int columna;

    /**
     * Constructor de la instruccion valor
     * @param id El id de la instruccion
     * @param valor El valor que se obtendra
     * @param tipo El tipo de dato del valor
     * @param fila La fila donde se declaro el valor
     * @param columna La columna donde se declaro
     */
    public Valor(Tipo id, Object valor, Tipo tipo, int fila, int columna) {
        super(id);
        this.valor = valor;
        this.tipo = tipo;
        this.fila = fila;
        this.columna = columna;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        // Dependiendo del tipo de dato, se realizaran diferentes acciones
        if (tipo == Tipo.ENTERO) {
            return new Valor(Tipo.VALOR, Integer.parseInt(String.valueOf(valor)), Tipo.ENTERO, this.fila, this.columna);
        } else if (tipo == Tipo.DECIMAL) {
            return new Valor(Tipo.VALOR, Double.parseDouble(String.valueOf(valor)), Tipo.DECIMAL, this.fila, this.columna);
        } else if (tipo == Tipo.BOOLEAN) {
            return this;
        } else if (tipo == Tipo.CADENA) {
            // Se retiran las comillas de la cadena si empieza con una
            String val = String.valueOf(valor);
            if (val.startsWith("\"")) {
                val = val.substring(1, val.length() - 1);
            }
            return new Valor(Tipo.VALOR, val, Tipo.CADENA, this.fila, this.columna);
        } else if (tipo == Tipo.IDENTIFICADOR) {
            // Se busca el simbolo en la tabla y se retorna su valor
            Simbolo simbolo = tabla.obtenerSimbolo(String.valueOf(valor));
            if (simbolo != null) {
                return new Valor(Tipo.VALOR,  simbolo.getValor(), simbolo.getTipo(), this.fila, this.columna);
            } else {
                return new Error(Tipo.ERROR, "No se encontro el simbolo " + String.valueOf(valor), Tipo.SEMANTICO, fila, columna);
            }
        } else if (tipo == Tipo.LLAMADA) {
            // Se busca la funcion a la que se le realiza la llamada.
            Llamada llamada = (Llamada) valor;
            boolean comprobacion = tabla.buscarFuncion(llamada.getIdentificador());
            if (comprobacion == true) {
                // Si la funcion existe, se opera la llamada.
                llamada.setConsola(super.getConsola());
                Instruccion valorLlamada = llamada.operar(tabla);
                if (valorLlamada instanceof Error) {
                    return valorLlamada;
                }
                if (valorLlamada instanceof Valor) {
                    valorLlamada.setConsola(super.getConsola());
                    return valorLlamada;
                } else {
                    return new Error(Tipo.ERROR, "La llamada es de tipo Void", Tipo.SEMANTICO, fila, columna);
                }
            } else {
                return new Error(Tipo.ERROR, "No se pudo encontrasssssssr la funcion " + llamada.getIdentificador(), Tipo.SEMANTICO, fila, columna);
            }
        } else {
            return new Error(Tipo.ERROR, "El tipo del valor no se puede operar", Tipo.SEMANTICO, fila, columna);
        }

    }

}

package instrucciones;

import expresiones.Valor;
import java.util.ArrayList;
import tabla.TablaSimbolos;
import tabla.Tipo;

public class Mientras extends Instruccion {

    private Instruccion relacion;
    private ArrayList<Instruccion> cuerpo = new ArrayList<>();
    private int fila;
    private int columna;

    /**
     * Constructor de la instruccion mientras
     * @param relacion La relacion que comprobar√° al inicio.
     * @param cuerpo El cuerpo de instrucciones
     * @param fila La fila en donde se hace la declaracion
     * @param columna La columna en donde se hace la declaracion
     * @param id El identificador de la instruccion.
     */
    public Mientras(Instruccion relacion, ArrayList<Instruccion> cuerpo, int fila, int columna, Tipo id) {
        super(id);
        this.relacion = relacion;
        this.cuerpo = cuerpo;
        this.fila = fila;
        this.columna = columna;
    }

    public Instruccion getRelacion() {
        return relacion;
    }

    public void setRelacion(Instruccion relacion) {
        this.relacion = relacion;
    }

    public ArrayList<Instruccion> getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(ArrayList<Instruccion> cuerpo) {
        this.cuerpo = cuerpo;
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

    /**
     * Opera la instruccion Mientras
     * @param tabla La tabla de simbolos
     * @return Una instruccion
     */
    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        // Se crea la nueva tabla para el cuerpo de instrucciones
        TablaSimbolos nuevaTabla = new TablaSimbolos(tabla);

        Instruccion insRela = relacion.operar(nuevaTabla);
        if (insRela instanceof errores.Error) {
            return insRela;
        }

        Valor rel = (Valor) insRela;
        if (rel.getTipo() != Tipo.BOOLEAN) {
            return new errores.Error(Tipo.ERROR, "La expresion debe ser relacional", Tipo.SEMANTICO, fila, columna);
        }

        // Obtenelos el valor de la expresion. Si esta es verdadera se ejecuta el cuerpo del mientras
        boolean valorExpresion = (boolean) rel.getValor();
        boolean fin = false;
        while (valorExpresion == true) {
            for (Instruccion ins : cuerpo) {
                ins.setConsola(super.getConsola());
                // Si la instruccion es Detener, se finaliza el ciclo
                if (ins instanceof Detener) {
                    fin = true;
                    break;
                // Si la instruccion es un continuar, se continuara con la siguiente iteracion
                } else if (ins instanceof Continuar) {
                    break;
                } else if (ins instanceof Imprimir) {
                    Instruccion val = ins.operar(nuevaTabla);
                    if (val instanceof Valor) {
                        super.getConsola().append(String.valueOf(((Valor) val).getValor()) + "\n");
                    } else {
                        return val;
                    }
                } else if (ins instanceof Si) {
                    Instruccion valor = ins.operar(nuevaTabla);
                    if (valor instanceof errores.Error) {
                        return valor;
                    }
                    if (((Si) ins).getValorCiclo() != null) {
                        if (((Si) ins).getValorCiclo() instanceof Detener) {
                            fin = true;
                            break;
                        } else {
                            break;
                        }
                    }
                } else {
                    Instruccion valor = ins.operar(nuevaTabla);
                    if (valor instanceof errores.Error) {
                        return valor;
                    }
                }
            }
            if (fin == true) {
                break;
            }
            // Se opera de nuevo la expresion relacional
            Instruccion nuevaExpresion = relacion.operar(nuevaTabla);
            
            if (nuevaExpresion instanceof errores.Error) {
                return nuevaExpresion;
            }

            Valor valorRel = (Valor) nuevaExpresion;
            if (valorRel.getTipo() != Tipo.BOOLEAN) {
                return new errores.Error(Tipo.ERROR, "La expresion debe ser relacional", Tipo.SEMANTICO, fila, columna);
            }

            valorExpresion = (boolean) valorRel.getValor();
        }
        return new Instruccion();
    }

}

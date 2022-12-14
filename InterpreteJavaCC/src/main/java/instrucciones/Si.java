/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instrucciones;

import java.util.ArrayList;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;
import expresiones.Valor;

/**
 *
 * @author oscar
 */
public class Si extends Instruccion {

    private Instruccion relacion;
    private ArrayList<Instruccion> cuerpo;
    private ArrayList<Instruccion> cuerpo_else;
    private Valor retorno = null;
    private Instruccion valorCiclo = null;
    private int fila;
    private int columna;

    /**
     * Constructor de la instruccion if
     * @param id El identificador de la instruccion
     * @param relacion La relacion de la instruccion
     * @param cuerpo El cuerpo de la instruccion verdadero
     * @param cuerpo_else El cuerpo de la instruccion falsa
     * @param fila La fila en donde se declara
     * @param columna La columna en donde se declara
     */
    public Si(Tipo id, Instruccion relacion, ArrayList<Instruccion> cuerpo, ArrayList<Instruccion> cuerpo_else, int fila, int columna) {
        super(id);
        this.relacion = relacion;
        this.cuerpo = cuerpo;
        this.cuerpo_else = cuerpo_else;
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

    public ArrayList<Instruccion> getCuerpo_else() {
        return cuerpo_else;
    }

    public void setCuerpo_else(ArrayList<Instruccion> cuerpo_else) {
        this.cuerpo_else = cuerpo_else;
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

    public Valor getRetorno() {
        return retorno;
    }

    public void setRetorno(Valor retorno) {
        this.retorno = retorno;
    }

    public Instruccion getValorCiclo() {
        return valorCiclo;
    }

    public void setValorCiclo(Instruccion valorCiclo) {
        this.valorCiclo = valorCiclo;
    }
    
    /**
     * Opera la instruccion
     * @param tabla La tabla de simbolos
     * @return Retorna una funcion
     */
    @Override
    public Instruccion operar(TablaSimbolos tabla) {
        // Reinicia el valor del ciclo
        valorCiclo = null;
        // Opera la relacion
        Instruccion expresion = relacion.operar(tabla);
        if (expresion instanceof Error) {
            return expresion;
        }
        
        Valor operada = (Valor) expresion;
        if (operada.getTipo() != Tipo.BOOLEAN) {
            return new Error(Tipo.ERROR, "La condicion del Si debe ser booleana", Tipo.SEMANTICO, fila, columna);
        }
        // Obtiene el valor de la instruccion
        boolean bandera = (boolean) operada.getValor();

        // Si el valor es verdadero, se ejecuta el cuerpo
        if (bandera == true) {
            TablaSimbolos nuevaTabla = new TablaSimbolos(tabla);
            for (Instruccion ins : cuerpo) {
                ins.setConsola(super.getConsola());
                if (ins instanceof Imprimir) {
                    Instruccion val = ins.operar(nuevaTabla);
                    if (val instanceof Valor) {
                        super.getConsola().append(String.valueOf(((Valor) val).getValor()) + "\n");
                    } else {
                        return val;
                    }
                } else if (ins instanceof Detener || ins instanceof Continuar) {
                    valorCiclo = ins;
                    break;
                } else if (ins instanceof Retorno) {
                    Instruccion valor = ins.operar(nuevaTabla);
                    if (valor instanceof Error) {
                        return valor;
                    }
                    this.setRetorno((Valor) valor);
                    break;
                } else if (ins instanceof Si) {
                    Instruccion valor = ins.operar(nuevaTabla);
                    if (valor instanceof Error) {
                        return valor;
                    }
                    if (((Si) ins).getRetorno() != null) {
                        this.setRetorno(((Si) ins).getRetorno());
                    }
                } else {
                    Instruccion valor = ins.operar(nuevaTabla);
                    if (valor instanceof Error) {
                        return valor;
                    }
                }
            }
        } else {
            // Si es falso, se ejecuta el cuerpo_else
            if (cuerpo_else.isEmpty() == false) {
                TablaSimbolos nuevaTabla = new TablaSimbolos(tabla);
                for (Instruccion ins : cuerpo_else) {
                    ins.setConsola(super.getConsola());
                    if (ins instanceof Imprimir) {
                        Instruccion val = ins.operar(nuevaTabla);
                        if (val instanceof Valor) {
                            super.getConsola().append(String.valueOf(((Valor) val).getValor()) + "\n");
                        } else {
                            return val;
                        }
                    } else if (ins instanceof Detener || ins instanceof Continuar) {
                        valorCiclo = ins;
                        break;
                    } else if (ins instanceof Retorno) {
                        Instruccion valor = ins.operar(nuevaTabla);
                        if (valor instanceof Error) {
                            return valor;
                        }
                        this.setRetorno((Valor) valor);
                        break;
                    } else if (ins instanceof Si) {
                        Instruccion valor = ins.operar(nuevaTabla);
                        if (valor instanceof Error) {
                            return valor;
                        }
                        if (((Si) ins).getRetorno() != null) {
                            this.setRetorno(((Si) ins).getRetorno());
                        }
                    } else {
                        Instruccion valor = ins.operar(nuevaTabla);
                        if (valor instanceof Error) {
                            return valor;
                        }
                    }
                }
            }
        }

        return new Instruccion();
    }

}

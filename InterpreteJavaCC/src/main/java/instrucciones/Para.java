package instrucciones;

import java.util.ArrayList;
import tabla.TablaSimbolos;
import tabla.Tipo;
import errores.Error;
import expresiones.Valor;

public class Para extends Instruccion {

    private Declaracion declaracion;
    private Instruccion relacion;
    private Iteracion iterador;
    private ArrayList<Instruccion> cuerpo = new ArrayList<>();
    private int fila;
    private int columna;

    public Para(Tipo id, Declaracion declaracion, Instruccion relacion, Iteracion iterador, ArrayList<Instruccion> cuerpo, int fila, int columna) {
        super(id);
        this.declaracion = declaracion;
        this.relacion = relacion;
        this.iterador = iterador;
        this.cuerpo = cuerpo;
        this.fila = fila;
        this.columna = columna;
    }

    public Declaracion getDeclaracion() {
        return declaracion;
    }

    public void setDeclaracion(Declaracion declaracion) {
        this.declaracion = declaracion;
    }

    public Instruccion getRelacion() {
        return relacion;
    }

    public void setRelacion(Instruccion relacion) {
        this.relacion = relacion;
    }

    public Iteracion getIterador() {
        return iterador;
    }

    public void setIterador(Iteracion iterador) {
        this.iterador = iterador;
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

    @Override
    public Instruccion operar(TablaSimbolos tabla) {

        TablaSimbolos nuevaTabla = new TablaSimbolos(tabla);
        Instruccion insDecla = declaracion.operar(nuevaTabla);
        if (insDecla instanceof Error) {
            return insDecla;
        }

        Instruccion insRela = relacion.operar(nuevaTabla);
        if (insRela instanceof Error) {
            return insRela;
        }

        Valor rel = (Valor) insRela;
        if (rel.getTipo() != Tipo.BOOLEAN) {
            return new Error(Tipo.ERROR, "La expresion debe ser relacional", Tipo.SEMANTICO, fila, columna);
        }

        iterador.setIdentificador(declaracion.getIdentificador());
        boolean valorExpresion = (boolean) rel.getValor();
        boolean fin = false;
        while (valorExpresion == true) {
            for (Instruccion ins : cuerpo) {
                ins.setConsola(super.getConsola());
                if (ins instanceof Detener) {
                    fin = true;
                    break;
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
                    if (valor instanceof Error) {
                        return valor;
                    }
                    if (((Si) ins).getValorCiclo()!= null) {
                        if (((Si) ins).getValorCiclo() instanceof Detener){
                            fin = true;
                            break;
                        } else {
                            break;
                        }
                    }
                } else {
                    Instruccion valor = ins.operar(nuevaTabla);
                    if (valor instanceof Error) {
                        return valor;
                    }
                }
            }
            if (fin == true) {
                break;
            }
            Instruccion iteracion = iterador.operar(nuevaTabla);
            if (iteracion instanceof Error) {
                return iteracion;
            }

            Instruccion nuevaExpresion = relacion.operar(nuevaTabla);

            if (nuevaExpresion instanceof Error) {
                return nuevaExpresion;
            }

            Valor valorRel = (Valor) nuevaExpresion;
            if (valorRel.getTipo() != Tipo.BOOLEAN) {
                return new Error(Tipo.ERROR, "La expresion debe ser relacional", Tipo.SEMANTICO, fila, columna);
            }

            valorExpresion = (boolean) valorRel.getValor();
        }
        return new Instruccion();
    }

}


package tabla;


public class Simbolo {
    
    private Tipo tipo;
    private String id;
    private Object valor;
    private int fila;
    private int columna;

    public Simbolo(Tipo tipo, String id, Object valor, int fila, int columna) {
        this.tipo = tipo;
        this.id = id;
        this.valor = valor;
        this.fila = fila;
        this.columna = columna;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
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
    
    
}

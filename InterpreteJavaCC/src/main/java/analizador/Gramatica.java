/* Generated By:JavaCC: Do not edit this line. Gramatica.java */
/** Analizador de expresiones aritmeticas sencillas. */
package Analizador;

import expresiones.Logica;
import expresiones.Operacion;
import expresiones.Relacional;
import salida.Salida;
import tabla.Tipo;
import instrucciones.Instruccion;
import instrucciones.Declaracion;
import instrucciones.Imprimir;
import expresiones.Valor;
import instrucciones.Funcion;
import java.util.ArrayList;
import instrucciones.Asignacion;
import instrucciones.Retorno;

public class Gramatica implements GramaticaConstants {

    Salida salida = new Salida();

    public void setSalida(Salida salida){
        this.salida = salida;
    }

  final public void Analizar() throws ParseException {
 Instruccion ins;
    label_1:
    while (true) {
      ins = Instruccion();
                          salida.agregarInstruccion(ins);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CADENA:
      case INT:
      case DOUBLE:
      case BOOLEAN:
      case FUNCION:
      case IDENTIFICADOR:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
    }
    jj_consume_token(0);
  }

  final public Instruccion Instruccion() throws ParseException {
 Instruccion e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CADENA:
    case INT:
    case DOUBLE:
    case BOOLEAN:
      e = Declaracion();
                       {if (true) return e;}
      break;
    case IDENTIFICADOR:
      e = Asignacion();
                        {if (true) return e;}
      break;
    case FUNCION:
      e = Funcion();
                     {if (true) return e;}
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Instruccion InstruccionFuncion() throws ParseException {
 Instruccion e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CADENA:
    case INT:
    case DOUBLE:
    case BOOLEAN:
      e = Declaracion();
                       {if (true) return e;}
      break;
    case IMPRIMIR:
      e = Imprimir();
                      {if (true) return e;}
      break;
    case IDENTIFICADOR:
      e = Asignacion();
                        {if (true) return e;}
      break;
    case RETORNO:
      e = Retorno();
                     {if (true) return e;}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Instruccion Funcion() throws ParseException {
 Token id; ArrayList<Instruccion> instrucciones; ArrayList<Declaracion> declaraciones; Tipo tipo;
    jj_consume_token(FUNCION);
    tipo = FuncionTipo();
    id = jj_consume_token(IDENTIFICADOR);
    jj_consume_token(PARIZ);
    declaraciones = ListaParametros();
    jj_consume_token(PARDE);
    instrucciones = Bloque();
     {if (true) return new Funcion(Tipo.FUNCION, id.image, null, instrucciones, tipo, id.beginLine, id.beginColumn);}
    throw new Error("Missing return statement in function");
  }

  final public ArrayList<Instruccion> Bloque() throws ParseException {
 ArrayList<Instruccion> instrucciones = new ArrayList<Instruccion>(); Instruccion e;
    jj_consume_token(LLAVE_I);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CADENA:
      case INT:
      case DOUBLE:
      case BOOLEAN:
      case IMPRIMIR:
      case RETORNO:
      case IDENTIFICADOR:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_2;
      }
      e = InstruccionFuncion();
                                     instrucciones.add(e);
    }
    jj_consume_token(LLAVE_D);
                                                                         {if (true) return instrucciones;}
    throw new Error("Missing return statement in function");
  }

  final public ArrayList<Declaracion> ListaParametros() throws ParseException {
 ArrayList<Declaracion> lista = new ArrayList<Declaracion>(); Declaracion e; Tipo t; Token id;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case CADENA:
    case INT:
    case DOUBLE:
    case BOOLEAN:
      t = Tipo();
      id = jj_consume_token(IDENTIFICADOR);
                                     lista.add(new Declaracion(Tipo.DECLARACION,id.image, null, t, id.beginLine, id.beginColumn));
      label_3:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMA:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_3;
        }
        jj_consume_token(COMA);
        t = Tipo();
        id = jj_consume_token(IDENTIFICADOR);
                                                lista.add(new Declaracion(Tipo.DECLARACION,id.image, null, t, id.beginLine, id.beginColumn));
      }
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
     {if (true) return lista;}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion Imprimir() throws ParseException {
 Instruccion e;
    jj_consume_token(IMPRIMIR);
    jj_consume_token(PARIZ);
    e = Expresion();
    jj_consume_token(PARDE);
    jj_consume_token(PCOMA);
                                                      {if (true) return new Imprimir(Tipo.IMPRIMIR, e,token.beginLine, token.beginColumn);}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion Asignacion() throws ParseException {
 Instruccion e; Token id;
    id = jj_consume_token(IDENTIFICADOR);
    jj_consume_token(IGUAL);
    e = Expresion();
    jj_consume_token(PCOMA);
                                                          {if (true) return new Asignacion(Tipo.ASIGNACION, id.image, e, id.beginLine, id.beginColumn);}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion Declaracion() throws ParseException {
 Tipo t; Token tok; Instruccion e = null;
    t = Tipo();
    tok = jj_consume_token(IDENTIFICADOR);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IGUAL:
      jj_consume_token(IGUAL);
      e = Expresion();
      break;
    default:
      jj_la1[6] = jj_gen;
      ;
    }
    jj_consume_token(PCOMA);
                                                                         {if (true) return new Declaracion(Tipo.DECLARACION,tok.image, e, t, tok.beginLine, tok.beginColumn);}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion Retorno() throws ParseException {
 Instruccion e = null;
    jj_consume_token(RETORNO);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTERO:
    case DECIMAL:
    case TRUE:
    case FALSE:
    case PARIZ:
    case MENOS:
    case NOT:
    case IDENTIFICADOR:
    case STRING:
      e = Expresion();
      break;
    default:
      jj_la1[7] = jj_gen;
      ;
    }
    jj_consume_token(PCOMA);
                                            {if (true) return new Retorno(Tipo.RETORNO, e, token.beginLine, token.beginColumn);}
    throw new Error("Missing return statement in function");
  }

  final public Tipo FuncionTipo() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
           {if (true) return Tipo.ENTERO;}
      break;
    case DOUBLE:
      jj_consume_token(DOUBLE);
                {if (true) return Tipo.DECIMAL;}
      break;
    case CADENA:
      jj_consume_token(CADENA);
                {if (true) return Tipo.CADENA;}
      break;
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
                 {if (true) return Tipo.BOOLEAN;}
      break;
    case VOID:
      jj_consume_token(VOID);
              {if (true) return Tipo.VOID;}
      break;
    default:
      jj_la1[8] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Tipo Tipo() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
           {if (true) return Tipo.ENTERO;}
      break;
    case DOUBLE:
      jj_consume_token(DOUBLE);
                {if (true) return Tipo.DECIMAL;}
      break;
    case CADENA:
      jj_consume_token(CADENA);
                {if (true) return Tipo.CADENA;}
      break;
    case BOOLEAN:
      jj_consume_token(BOOLEAN);
                 {if (true) return Tipo.BOOLEAN;}
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Instruccion Expresion() throws ParseException {
 Instruccion e, e1;
    e = And();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case OR:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_4;
      }
      jj_consume_token(OR);
      e1 = And();
                         e = new Logica(Tipo.LOGICA, e, e1, Tipo.OR, token.beginLine, token.beginColumn);
    }
     {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion And() throws ParseException {
 Instruccion e, e1;
    e = ExpresionIgual();
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case AND:
        ;
        break;
      default:
        jj_la1[11] = jj_gen;
        break label_5;
      }
      jj_consume_token(AND);
      e1 = ExpresionIgual();
                                     e = new Logica(Tipo.LOGICA, e, e1, Tipo.AND, token.beginLine, token.beginColumn);
    }
     {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion ExpresionIgual() throws ParseException {
 Instruccion e, e1;
    e = ExpresionRelacional();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IGUALACION:
      case DIFERENCIACION:
        ;
        break;
      default:
        jj_la1[12] = jj_gen;
        break label_6;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case IGUALACION:
        jj_consume_token(IGUALACION);
        e1 = ExpresionRelacional();
                                                 e = new Relacional(Tipo.RELACIONAL, e, e1, Tipo.IGUAL, token.beginLine, token.beginColumn);
        break;
      case DIFERENCIACION:
        jj_consume_token(DIFERENCIACION);
        e1 = ExpresionRelacional();
                                                       e = new Relacional(Tipo.RELACIONAL, e, e1, Tipo.DIFERENTE, token.beginLine, token.beginColumn);
        break;
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
     {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion ExpresionRelacional() throws ParseException {
 Instruccion e, e1;
    e = ExpresionAditiva();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MENORQUE:
      case MAYORQUE:
      case MENORIGUAL:
      case MAYORIGUAL:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_7;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MAYORQUE:
        jj_consume_token(MAYORQUE);
        e1 = ExpresionAditiva();
                                            e = new Relacional(Tipo.RELACIONAL, e, e1, Tipo.MAYOR, token.beginLine, token.beginColumn);
        break;
      case MENORQUE:
        jj_consume_token(MENORQUE);
        e1 = ExpresionAditiva();
                                              e = new Relacional(Tipo.RELACIONAL, e, e1, Tipo.MENOR, token.beginLine, token.beginColumn);
        break;
      case MAYORIGUAL:
        jj_consume_token(MAYORIGUAL);
        e1 = ExpresionAditiva();
                                                e = new Relacional(Tipo.RELACIONAL, e, e1, Tipo.MAYORIGUAL, token.beginLine, token.beginColumn);
        break;
      case MENORIGUAL:
        jj_consume_token(MENORIGUAL);
        e1 = ExpresionAditiva();
                                                e = new Relacional(Tipo.RELACIONAL, e, e1, Tipo.MENORIGUAL, token.beginLine, token.beginColumn);
        break;
      default:
        jj_la1[15] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
     {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion ExpresionAditiva() throws ParseException {
 Instruccion e, e1;
    e = ExpresionMultiplicativa();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MAS:
      case MENOS:
        ;
        break;
      default:
        jj_la1[16] = jj_gen;
        break label_8;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case MAS:
        jj_consume_token(MAS);
        e1 = ExpresionMultiplicativa();
                                              e = new Operacion(Tipo.OPERACION, e, e1, Tipo.SUMA, token.beginLine, token.beginColumn);
        break;
      case MENOS:
        jj_consume_token(MENOS);
        e1 = ExpresionMultiplicativa();
                                                  e = new Operacion(Tipo.OPERACION, e, e1, Tipo.RESTA, token.beginLine, token.beginColumn);
        break;
      default:
        jj_la1[17] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
     {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion ExpresionMultiplicativa() throws ParseException {
 Instruccion e, e1;
    e = ExpresionUnaria();
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case POR:
      case DIV:
        ;
        break;
      default:
        jj_la1[18] = jj_gen;
        break label_9;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case POR:
        jj_consume_token(POR);
        e1 = ExpresionUnaria();
                                      e = new Operacion(Tipo.OPERACION, e, e1, Tipo.MULTIPLICACION, token.beginLine, token.beginColumn);
        break;
      case DIV:
        jj_consume_token(DIV);
        e1 = ExpresionUnaria();
                                       e = new Operacion(Tipo.OPERACION, e, e1, Tipo.DIVISION, token.beginLine, token.beginColumn);
        break;
      default:
        jj_la1[19] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
     {if (true) return e;}
    throw new Error("Missing return statement in function");
  }

  final public Instruccion ExpresionUnaria() throws ParseException {
 Instruccion e;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MENOS:
      jj_consume_token(MENOS);
      e = Primitivo();
                             {if (true) return new Operacion(Tipo.OPERACION,  e, null, Tipo.MENOS, token.beginLine, token.beginColumn);}
      break;
    case NOT:
      jj_consume_token(NOT);
      e = Primitivo();
                             {if (true) return new Logica(Tipo.LOGICA,  e, null, Tipo.NOT, token.beginLine, token.beginColumn);}
      break;
    case ENTERO:
    case DECIMAL:
    case TRUE:
    case FALSE:
    case PARIZ:
    case IDENTIFICADOR:
    case STRING:
      e = Primitivo();
                       {if (true) return e;}
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Instruccion Primitivo() throws ParseException {
 Token t; Instruccion e = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ENTERO:
      t = jj_consume_token(ENTERO);
                  {if (true) return new Valor(Tipo.VALOR, t.image, Tipo.ENTERO, t.beginLine, t.beginColumn);}
      break;
    case DECIMAL:
      t = jj_consume_token(DECIMAL);
                    {if (true) return new Valor(Tipo.VALOR, t.image, Tipo.DECIMAL, t.beginLine, t.beginColumn);}
      break;
    case STRING:
      t = jj_consume_token(STRING);
                    {if (true) return new Valor(Tipo.VALOR, t.image, Tipo.CADENA, t.beginLine, t.beginColumn);}
      break;
    case TRUE:
      t = jj_consume_token(TRUE);
                {if (true) return new Valor(Tipo.VALOR, true, Tipo.BOOLEAN, t.beginLine, t.beginColumn);}
      break;
    case FALSE:
      t = jj_consume_token(FALSE);
                 {if (true) return new Valor(Tipo.VALOR, false, Tipo.BOOLEAN, t.beginLine, t.beginColumn);}
      break;
    case IDENTIFICADOR:
      t = jj_consume_token(IDENTIFICADOR);
                         {if (true) return new Valor(Tipo.VALOR, t.image, Tipo.IDENTIFICADOR, t.beginLine, t.beginColumn);}
      break;
    case PARIZ:
      jj_consume_token(PARIZ);
      e = Expresion();
      jj_consume_token(PARDE);
                                        {if (true) return e;}
      break;
    default:
      jj_la1[21] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  /** Generated Token Manager. */
  public GramaticaTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[22];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x26c0,0x26c0,0x4ec0,0x4ec0,0x0,0x6c0,0x40000000,0x810003c,0x7c0,0x6c0,0x0,0x0,0x0,0x0,0x80000000,0x80000000,0xc000000,0xc000000,0x30000000,0x30000000,0x810003c,0x10003c,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x200,0x200,0x200,0x200,0x20,0x0,0x0,0x40300,0x0,0x0,0x80,0x40,0x18,0x18,0x7,0x7,0x0,0x0,0x0,0x0,0x40300,0x40200,};
   }

  /** Constructor with InputStream. */
  public Gramatica(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Gramatica(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new GramaticaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public Gramatica(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new GramaticaTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public Gramatica(GramaticaTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(GramaticaTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 22; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[51];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 22; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 51; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

}

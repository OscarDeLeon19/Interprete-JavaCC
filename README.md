# Interprete-JavaCC

**Manual de Usuario Interprete JavaCC** 

Para iniciar el programa tener instalado Java versión 15. 

El programa fue realizado con el creador de analizadores léxicos y sintácticos para java: **Java CC.** 

Se encuentra en la página: https://javacc.github.io/javacc/ Para ejecutar el proyecto debe realizarse lo siguiente: 

- Descargar el repositorio 
- Ir a la carpeta de tarjet 
- Ejecutar el archivo: **InterpreteJavaCC-jar-with-dependencies.jar** 

Al  iniciar  el  programa  mostrara  lo  siguiente: 

![Aspose Words 24f793e2-e978-47ce-8ebb-8e730edca042 001](https://user-images.githubusercontent.com/68717439/196808335-be6676a6-129c-4267-92c6-69b138238602.png)
![Aspose Words 24f793e2-e978-47ce-8ebb-8e730edca042 002](https://user-images.githubusercontent.com/68717439/196808359-e1f42d34-aa6b-49e7-a47f-d2a033287160.png)
![Aspose Words 24f793e2-e978-47ce-8ebb-8e730edca042 003](https://user-images.githubusercontent.com/68717439/196808369-992e4f41-8987-49b1-abe8-4c85d7bf69c2.png)

El programa cuenta con las funciones básicas las cuales son: 

- Nuevo 
- Abrir 
- Guardar 
- Guardar Como 

El área de texto superior sirve para escribir todo el código e interpretarlo, y el área te texto inferior es la consola de salida. 

El programa acepta las siguientes instrucciones: 

- Declaración de Variables 
- Asignación de Variables 
- Imprimir en consola 
- Ciclos 
- Condicional 
- Llamadas a funciones 
- Instrucciones retorno, detener y continuar. 

**Tipos de Datos**: 

El  programa  acepta  datos  enteros,  decimales,  cadenas  y  booleanos.  (int, double, cadena, boolean) 

**Expresiones**:  

El programa acepta Expresiones Aritmetica: sumas, restas, multiplicación y división (+, -, \*, /). Expresiones relacionales:  mayor, menos, mayor igual, menor igual, igual, diferente (>, <, >=, <=, ==, !=). Expresiones Logica: And, Or, Not (&&, ||, !). 

**Declaración de Variables.** 

La  declaración  puede  ser  de  ámbito  global  o  dentro  de  una  función.  La estructura para definir una variable es la siguiente: 

<Tipo> <ID>; 

<Tipo> <ID> = <Expresion>; 

**Ejemplos**: 

int variable1 = 50; 

double variable2; 

cadena miCadena = “Hola Mundo” boolean bandera = true; **Asignación** **de variables** 

La  declaración  puede  ser  de  ámbito  global  o  dentro  de  una  función.  La estructura en la siguiente: 

<Identificador> = <Expresion>; **Ejemplos**: 

miVariable = 25; **Funciones/Metodos:** 

Las funciones son bloques de códigos que se ejecutaran para que se ejecute una  llamada.  El  programa  debe  llamar  una  función  “Principal”.  Que  es básicamente  como  un  método  main.  Una  función  no  puede  tener  un identificador igual al de otra función. Pueden ser de cualquier tipo de dato incluyendo el tipo **void.** Este tipo no retornara ningún valor. No se puede declarar una función dentro de otra función. Los parámetros son del tipo: <Tipo> <Identificador>. Una función puede tener n parámetros o ninguno. 

Para declarar una función se utiliza la siguiente palabra reservada: **función**, y la sintaxis es: 

- función <Tipo> <Identificador> ( <Parametros> ) { <Instrucciones> } 

**Imprimir en consola:** 

Se utiliza la palabra reservada **imprimir**, la estructura es la siguiente: 

imprimir(<Expresion>); 

Todo lo que este dentro de paréntesis se imprimirá en la consola. **Retorno:** 

El retorno indica cual es el valor que una función va a regresar cuando sea llamada. Debe ser del mismo tipo del de la función. Solo es opcional en las funciones de tipo void, al declararlo la lectura de la función se detendrá. También pueden declararse dentro de una instrucción **si**. 

La sintaxis utiliza la palabra reservada **retorno** es la siguiente:  

retorno <Expresion>; 

retorno; 

**Si** 

Tiene el funcionamiento básico de un if-else. Y la sintaxis es la siguiente:  

Utiliza las palabras reservadas **si – sino:** 

si (<Expresion>) { <Instrucciones> } sino { <Instrucciones> } 

**Ciclos:** 

**Para:** 

Tiene el funcionamiento de un ciclo for. La sintaxis utiliza la palabra reservada **para**. La sintaxis es la siguiente:  

para ( <Declaracion>; <Expresion>; <Iteracion>) { <Instrucción> } 

Ejemplo: 

para (int i = 0; i < 10; ++){ 

imprimir(i); 

} 

**Mientras**: 

Tiene el funcionamiento de un ciclo mientras. La sintaxis utiliza la palabra reservada **mientras**. La sintaxis es la siguiente: 

mientras ( <Expresion> ) { <Instrucciones> } 

Ejercicio:  

mientras (variable < 5){ 

imprimir(variable); 

variable = variable + 1; } 

**Detener:** 

Detienen la ejecución de un ciclo. Solo puede ser declarada dentro de estos. Usa la palabra reservada: **detener**. 

para (int i = 0; i < 10; ++){ 

imprimir(i); 

si (i == 5){ 

detener; 

} 

} 

**Continuar**:  

Detiene la ejecución de la iteración de un ciclo y continua con la iteración siguiente. Solo puede ser declarada dentro de estos. Usa la palabra reservada: continuar. 

para (int i = 0; i < 10; ++){ si (i == 5){ 

continuar; 

} 

imprimir(i); 

}       **Llamadas:** 

Instrucción  declarada  para  ejecutar  una  función.  Puede  asignarse  a  una variable que sea del mismo tipo. 

Ejemplos: 

sumar(5, 5); 

int valorResta = resta(10, 5);  **Errores:** 

Los errores léxicos, sintácticos, y semánticos se mostraran en la consola de salida. 

![Aspose Words 24f793e2-e978-47ce-8ebb-8e730edca042 004](https://user-images.githubusercontent.com/68717439/196808458-bb1b7afd-59b5-4296-9ec8-11f839a04832.png)
![Aspose Words 24f793e2-e978-47ce-8ebb-8e730edca042 005](https://user-images.githubusercontent.com/68717439/196808473-794190a5-ceb4-4287-a456-a340db27c643.png)
![Aspose Words 24f793e2-e978-47ce-8ebb-8e730edca042 006](https://user-images.githubusercontent.com/68717439/196808478-f891dc01-91e3-4744-b668-688a60fb302a.png)



package ejemplos.tema3;

//para poder usar Map y ListaConPI
import librerias.estructurasDeDatos.modelos.*; 

//para poder usar TablaHash, la Implementacion de Map
import librerias.estructurasDeDatos.deDispersion.*; 

// CUESTION: ?Que sucede si, en lugar de los dos import, 
// se escribe: import java.util.*;?
import java.util.Locale; 
import java.util.Scanner; 

public class Test3Map {
    
    public static void main(String[] args) {
        
        // Por simplicidad, la frase no se lee de un fichero,  
        // sino que se lee de teclado como un String de Palabras 
        // separadas por blancos. Una frase (String) ejemplo seria: 
        // "vale, aunque es un poco rollo lo hago para que se vea como funciona el Map!! Se me ha olvidado escribir palabras repetidas vaya!!"

        // Lectura de la frase (String) a partir de la que se construye el Map
        Locale localEDA = new Locale("es", "US");
        Scanner teclado = new Scanner(System.in).useLocale(localEDA);
        System.out.println("Escriba palabras separadas por blancos:");
        String texto = teclado.nextLine();

        // Creacion del Map vacio ... 
        // ?Que Clave y Valor tiene cada Entrada de este Map? 
        // ?De que tipos son? 
        Map<String, Integer> m = new TablaHash<String, Integer>(texto.length());

        String[] palabrasDelTexto = texto.split(" ");
        
        /** Construcción del Map, 
         *  via insercion/actualizacion de sus Entradas, 
         *  a partir de la frase leida
         */ 
        for (int i = 0; i < palabrasDelTexto.length; i++) {
            String palabra = palabrasDelTexto[i].toLowerCase();
            Integer contador = m.recuperar(palabra);
            if (contador == null) { m.insertar(palabra, 1); }
            else { m.insertar(palabra, contador + 1); }
        }
        
        /** Listado de las Entradas del Map 
         *  que correspondan a palabras repetidas,
         *  mediante recorrido de la ListaConPI de las claves del Map, 
         *  recuperar sus valores, y mostrar las Entradas donde valor sea >= 2
         */
        ListaConPI<String> deClaves = m.claves();
        System.out.println("Palabras repetidas en el texto: ");
        for (deClaves.inicio(); !deClaves.esFin(); deClaves.siguiente()) {
            String palabra = deClaves.recuperar();
            Integer contador = m.recuperar(palabra);
            if (contador > 1) { System.out.println(palabra + ": " + contador); }
        } 
    } 
}

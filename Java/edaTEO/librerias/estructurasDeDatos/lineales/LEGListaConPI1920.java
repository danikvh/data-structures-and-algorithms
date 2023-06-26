package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

/** Ejercicio 19 y 20 */
//Extendemos de LEGListaConPIOrdenada para que insertar ya lo haga en orden
public class LEGListaConPI1920<E> extends LEGListaConPI<E> implements ListaConPI1920<E> {
    //SOLUCIÓN CLASE, SE IMPLEMENTA EN LA INTERFAZ PORQUE ES STATIC!!
    // /** método estático tal que, dadas dos ListasConPI genéricas, ambas sin 
     // * elementos repetidos y ordenados ascendentemente, elimine de dichas 
     // * listas todos los elementos que tengan en común y los devuelva almacenados en una cola.
       // */
    // public static <E extends Comparable<E>> Cola<E> eliminaComunStatic(ListaConPI<E> l1, ListaConPI<E> l2){
        // Cola<E> c = new ArrayCola<E>();
        // l1.inicio(); l2.inicio();
        // while(!l1.esFin() && !l2.esFin()) {
            // E e1 = l1.recuperar(); E e2 = l2.recuperar();
            // if (e1.compareTo(e2) == 0) { l1.eliminar(); l2.eliminar(); c.encolar(e1); }
            // else { 
                // if (e1.compareTo(e2) < 0) { l1.siguiente(); }
                // else { l2.siguiente(); }
            // }
        // }
        // return c;
    // }   
    
    //MI EJERCICIO, NO ES ESTÁTICO, NO SON ELEMENTOS SIN REPETIR!!!
    /** dadas dos ListaConPI genéricas, ambas CON elementos repetidos y
      * ordenados ascendentemente, elimine de dichas listas todos los elementos
      * que tengan en común y los devuelva almacenados en una cola.
       */
    public LEGCola<E> eliminaComun(ListaConPI<E> l){
        E aux;
        LEGCola<E> res = new LEGCola<E>();
        boolean elimina = false;
        this.inicio(); 
        for (int i = 0; i < this.talla(); i++) { //recorre lista this
            aux = recuperar(); //elemento en this a comparar
            l.inicio(); 
            for (int j = 0; j < l.talla(); j++) { //recorre lista l !!condición extra: aux.compareTo(l.recuperar()) > 0
                if (aux == l.recuperar()) {                  
                    l.eliminar();
                    elimina = true;
                } else { l.siguiente(); }
            }
            if (!elimina) { siguiente(); } //no se ha eliminado en l
            else { res.encolar(aux); eliminar(); elimina = false; } //se ha eliminado en l
        }
        return res;
    }
    

    
    
    //SOLUCIÓN CLASE, SE IMPLEMENTA EN LA INTERFAZ PORQUE ES STATIC!!
    // /** dados dos ListasConPI genéricas, compruebe si ambas listas contienen
     // * los mismos elementos pero en orden inverso.
     // */
    // public static <E> boolean capicuaStatic(ListaConPI<E> l1, ListaConPI<E> l2) {
        
    // }
    
    //MI EJERCICIO NO ES ESTÁTICO!!
    /** dados dos ListasConPI genéricas, compruebe si ambas listas contienen
     * los mismos elementos pero en orden inverso.
     */
    public boolean capicua(ListaConPI<E> l){
        if (this.talla() != l.talla()) { return false; }
        this.inicio();
        int j = talla();
        for (int i = 0; i < this.talla(); i++) {  
            l.inicio();
            for (int k = 0; k < j-1; k++) {
                l.siguiente();
            }
            if (recuperar() != l.recuperar()) { return false; } //se podria usar compareTo() extendiendo Comparable a E
            this.siguiente();
            j--;
        }
        return true;
    }
}

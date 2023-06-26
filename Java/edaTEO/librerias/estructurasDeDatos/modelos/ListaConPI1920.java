package librerias.estructurasDeDatos.modelos;

import librerias.estructurasDeDatos.modelos.*;
import librerias.estructurasDeDatos.lineales.*;

/**
 * Ejercicio 19 y 20
 */
public interface ListaConPI1920<E> extends ListaConPI<E> {
    /** dados dos ListasConPI genéricas, ambas sin elementos repetidos y
      * ordenados ascendentemente, elimine de dichas listas todos los elementos
      * que tengan en común y los devuelva almacenados en una cola.
       */
    Cola<E> eliminaComun(ListaConPI<E> l);
    
    //SOLUCIÓN CLASE, SE IMPLEMENTA EN LA INTERFAZ PORQUE ES STATIC!!
    /** dados dos ListasConPI genéricas, ambas sin elementos repetidos y
      * ordenados ascendentemente, elimine de dichas listas todos los elementos
      * que tengan en común y los devuelva almacenados en una cola.
       */
    public static <E extends Comparable<E>> Cola<E> eliminaComunStatic(ListaConPI<E> l1, ListaConPI<E> l2){
        Cola<E> c = new ArrayCola<E>();
        l1.inicio(); l2.inicio();
        while(!l1.esFin() && !l2.esFin()) {
            E e1 = l1.recuperar(); E e2 = l2.recuperar();
            if (e1.compareTo(e2) == 0) { l1.eliminar(); l2.eliminar(); c.encolar(e1); }
            else { 
                if (e1.compareTo(e2) < 0) { l1.siguiente(); }
                else { l2.siguiente(); }
            }
        }
        return c;
    }   
    
    /** dados dos ListasConPI genéricas, compruebe si ambas listas contienen
     * los mismos elementos pero en orden inverso.
     */
    boolean capicua(ListaConPI<E> l);
    
    //SOLUCIÓN CLASE, ITERATIVA, SE IMPLEMENTA EN LA INTERFAZ PORQUE ES STATIC!!
    /** dados dos ListasConPI genéricas, compruebe si ambas listas contienen
     * los mismos elementos pero en orden inverso.
     */
    public static <E> boolean capicuaStatic(ListaConPI<E> l1, ListaConPI<E> l2) {
        if (l1.talla() != l2.talla()) return false;
        Pila<E> p = new ArrayPila<E>();
        for (l2.inicio(); !l2.esFin(); l2.siguiente()) {
            p.apilar(l2.recuperar());
        }
        for (l1.inicio(); !l1.esFin(); l1.siguiente()) {
            E e1 = l1.recuperar();
            E e2 = p.desapilar();
            if (!e1.equals(e2)) return false;
        }
        return true;
    }
    
    //SOLUCIÓN RECURSIVA EN EXAMEN 25 JUNIO 2021
}

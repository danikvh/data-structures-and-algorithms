package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

public class LEGPila<E> implements Pila<E> {
    protected NodoLEG<E> tope;
    protected int talla;

    /** crea una Pila vacia **/
    public LEGPila() {
        /*COMPLETAR*/ //Completado
        tope = null;
        talla = -1;
    }
      
    /** inserta el Elemento e en una Pila, o lo situa en su tope **/
    public void apilar(E e) {
        /*COMPLETAR*/ //Completado
        tope = new NodoLEG<E>(e, tope);
        talla++;
    }
      
    /** SII !esVacia(): 
     * obtiene y elimina de una Pila el Elemento que ocupa su tope 
     */
    public E desapilar() {
        /*COMPLETAR Y CORREGIR*/ //Completado
        if (esVacia()) { return null; }
        E res = tope.dato;
        tope = tope.siguiente;
        talla--;
        return res;
    }
      
    /** SII !esVacia(): 
     * obtiene el Elemento que ocupa el tope de una Pila 
     */
    public E tope() {
        /*CORREGIR*/ //Completado
        if (esVacia()) { return null; }
        return tope.dato;
    }
      
    /** comprueba si una Pila esta vacia **/
    public boolean esVacia() {
        /*CORREGIR*/ //Completado
        if (talla < 0) { return true; }
        return false;
    }
      
    /** obtiene un String con los Elementos de una Pila en orden LIFO, 
     *  inverso al de insercion, 
     *  y con el formato que se usa en el estandar de Java. 
     *  Asi, por ejemplo, si se tiene una Pila con los Integer del 1 al 4, 
     *  en orden LIFO, toString devuelve [4, 3, 2, 1]; 
     *  si la Pila esta vacia, entonces devuelve [] 
     */
    public String toString() { 
        StringBuilder res = new StringBuilder();
        res.append("["); 
        /*COMPLETAR*/ //Completado
        NodoLEG<E> aux = tope;
        for (int i = 0; i < talla; i++) {
            res.append(aux.dato + ", ");
            aux = aux.siguiente;
        }
        if (esVacia()) { res.append("]"); }
        else { res.append(aux.dato + "]"); }
        return res.toString(); 
    }
}
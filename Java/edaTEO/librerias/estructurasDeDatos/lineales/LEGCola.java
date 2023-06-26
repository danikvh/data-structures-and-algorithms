package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

/**
 * Ejercicio 2
 */
public class LEGCola<E> implements Cola<E> {
    protected NodoLEG<E> principioC, finalC;
    protected int talla;
    
    public LEGCola(){
        principioC = null; 
        finalC = null;
        talla = -1;
    }
    
    /** inserta el Elemento e en una Cola, o lo situa en su final 
     */
    public void encolar(E e){
        NodoLEG<E> nuevo = new NodoLEG<E>(e);
        if (esVacia()) { principioC = nuevo; }
        else { finalC.siguiente = nuevo; }
        finalC = nuevo;
        talla++;
    }
    
    /** SII !esVacia(): 
     * obtiene y elimina de una Cola el Elemento que ocupa su principio 
     */
    public E desencolar(){
        if (esVacia()) { return null; }
        E e = principioC.dato;
        principioC = principioC.siguiente;
        if (principioC == null) { finalC = null; }
        talla--;
        return e;
    }
    
    // metodos Consultores del estado de una Cola
    /** SII !esVacia(): 
     * obtiene el Elemento que ocupa el principio de una Cola,
     * el primero en orden de insercion 
     */
    public E primero(){
        if (esVacia()) { return null; }
        return principioC.dato;
    }
    
    /** comprueba si una Cola esta vacia 
     */
    public boolean esVacia(){
        if (talla < 0) { return true; }
        return false;
    }
    
    /** obtiene un String con los Elementos de una Cola en orden FIFO, 
    *  o de insercion, y con el formato que se usa en el estandar de Java. 
    *  Asi, por ejemplo, si se tiene una Cola con los Integer del 1 al 4, 
    *  en orden FIFO, toString devuelve [1, 2, 3, 4]; 
    *  si la Cola esta vacia, entonces devuelve [] 
    */
    // OJO: se contempla la circularidad de elArray no solo usando el metodo
    // incrementar, sino contando el numero de Elementos desde 0 hasta talla-1
    public String toString() {
        // NOTA: se usa la clase StringBuilder, en lugar de String, 
        // por motivos de eficiencia
        StringBuilder res = new StringBuilder();
        res.append("[");
        NodoLEG<E> aux = principioC;
        for (int i = 0; i < talla; i++) {
            res.append(aux.dato + ", ");
            aux = aux.siguiente;
        }
        if (esVacia()) { res.append("]"); }
        else { res.append(aux.dato + "]"); }
        return res.toString();
    }
}

package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;

/** Ejercicio 12, 13, 14, 15 */

public class LEGListaConPIPlus<E> extends LEGListaConPI<E> implements ListaConPIPlus<E> {
    /** comprueba si el Elemento e esta en una Lista Con PI **/
    public boolean contiene(E e){
        inicio();
        while (!esFin()) {
            if (recuperar().equals(e)) { return true; }
            siguiente();
        }
        return false;
    }
    
    /** elimina la primera aparicion del Elemento e en una Lista Con PI 
     *  y devuelve true, o devuelve false si e no esta en la Lista
     */
    public boolean eliminarPrimero(E e){
        inicio();
        while (!esFin()) {
            if (recuperar().equals(e)) {
                eliminar();
                return true;
            }
            siguiente();
        }
        return false;
    }
    
    /** si el Elemento e esta en una Lista Con PI elimina su ultima aparicion
     *  y devuelve true, o devuelve false si e no esta en la Lista 
     */
    public boolean eliminarUltimo(E e){
        NodoLEG<E> ultimo = null;
        inicio();
        while (!esFin()) {
            if (recuperar().equals(e)) { ultimo = ant; }
            siguiente();
        }
        if (ultimo == null) { return false; }
        else {
            ant = ultimo;
            eliminar();
            return true;
        }
    }
    
    /** si el Elemento e esta en una Lista Con PI elimina todas sus apariciones,
     *  y devuelve true, o devuelve false si e no esta en la Lista 
     */
    public boolean eliminarTodos(E e){
        inicio();
        boolean res = false;
        while (!esFin()) { 
            if (recuperar().equals(e)) { 
                eliminar(); 
                res = true;
            }
            else { siguiente(); }
        }
        return res;
    }
    
    /** elimina todos los Elementos de una Lista Con PI **/
    public void vaciar(){
        
    }
    
    /** concatena una Lista Con PI con otra **/
    public void concatenar(ListaConPI<E> otra){
        this.fin();
        for (otra.inicio(); !otra.esFin(); otra.siguiente()) {
            this.insertar(otra.recuperar());
        }
    }
    
    /** desplaza todos los elementos de la lista una posición hacia la derecha,
     *  de forma que el último elemento deberá pasar a ser el primero
     */
    public void moverADerecha(){
        if (talla <= 1) { return; }
        for (inicio(); ant.siguiente != ult; siguiente()) {}
        ult.siguiente = pri.siguiente; //ultimo apunta a primero (ahora segundo)
        pri.siguiente = ult; //ultimo se convierte en primero
        ult = ant; //penultimo se convierte en ultimo
        ult.siguiente = null; //penultimo (ahora ultimo) apunta a null para ser ultimo
    }

    /** desplaza todos los elementos de la lista una posición hacia la izquierda,
     *  de forma que el primer elemento deberá pasar a ser el último 
     */
    public void moverAIzquierda(){
        if (talla <= 1) { return; }
        ult.siguiente = pri.siguiente;
        pri.siguiente = pri.siguiente.siguiente;
        ult = ult.siguiente;
        ult.siguiente = null;
    }

    /** invierte in-situ una Lista a partir de su PI **/
    public void invertirDesdePI(){
        
    }
}

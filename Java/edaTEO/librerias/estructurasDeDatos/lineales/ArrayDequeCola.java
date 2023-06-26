package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;
import java.util.*;


public class ArrayDequeCola<E> extends ArrayDeque<E> implements Cola<E> {
    protected ArrayDeque<E> elArray;    
    //protected int finalC;
    //protected int principioC;
    //protected int talla;
    protected static final int CAPACIDAD_POR_DEFECTO = 50;

    /** construye una Cola vacia **/
    @SuppressWarnings("unchecked")
    public ArrayDequeCola() {
        elArray = new ArrayDeque<E>(CAPACIDAD_POR_DEFECTO);
        //talla = -1; 
        //principioC = 0; 
        //finalC = 0;
    }

    /** inserta el Elemento e en una Cola, o lo situa en su final **/
    //  SII no hay suficiente espacio en elArray se duplica 
    //  el tamagno de elArray circular
    public void encolar(E e) {
        elArray.addLast(e); 
        //talla++;
    }

    /** SII !esVacia(): 
     *  obtiene y elimina de una Cola el Elemento que ocupa su principio **/ 
    // principio se incrementa circularmente 
    public E desencolar() {
        //talla--;
        return elArray.poll();
    }

    /** SII !esVacia(): 
     *  obtiene el Elemento que ocupa el principio de una Cola,  
     *  el primero en orden de insercion **/
    public E primero() { return elArray.peek(); }

    /** comprueba si una Cola esta vacia **/
    public boolean esVacia() { return elArray.size() == 0; }

    /** obtiene un String con los Elementos de una Cola en orden FIFO, 
     *  o de insercion, y con el formato que se usa en el estandar de Java. 
     *  Asi, por ejemplo, si se tiene una Cola con los Integer del 1 al 4, 
     *  en orden FIFO, toString devuelve [1, 2, 3, 4]; 
     *  si la Cola esta vacia, entonces devuelve [] 
     */
    // OJO: se contempla la circularidad de elArray no solo usando el metodo
    // incrementar, sino contando el numero de Elementos desde 0 hasta talla-1
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        ArrayDeque<E> aux = elArray.clone();
        for (int i = 0; i < elArray.size() - 1; i++){
            res.append(aux.peek() + ", ");
            aux.remove();
        }
        // NOTA: por cuestiones de formato, al terminar el bucle, 
        // agnadir el ultimo elemento al resultado;
        // en funcion de la talla, dicho elemento es 
        // el String elArray[aux].toString()+"]" o el String "]"
        if (elArray.size() - 1 >= 0) { res.append(aux.peek() + "]"); } 
        else { res.append("]"); }
        return res.toString();
    }
}

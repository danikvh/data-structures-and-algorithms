package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.Cola;

/** Ejercicio 17 */
public class LEGColaOrdenada<E extends Comparable<E>> extends LEGCola<E> implements Cola<E> {
    /** inserta el Elemento e en una Cola de manera ordenada 
     */
    public void encolar(E e){
        NodoLEG<E> nuevo = new NodoLEG<E>(e);
        NodoLEG<E> aux = principioC;
        if (esVacia()) {   //Caso 1: Cola vacía
            principioC = nuevo; 
            finalC = nuevo;
            talla++;
            return; 
        }
        for (int i = 0; i < talla; i++) {  //Caso 2: introducir en el medio
            if (e.compareTo(aux.dato) > 0 && e.compareTo(aux.siguiente.dato) <= 0) {
                nuevo.siguiente = aux.siguiente;
                aux.siguiente = nuevo;
                talla++;
                return;
            }
            aux = aux.siguiente;
        }
        finalC.siguiente = nuevo;  //Caso 3: introducir al final
        finalC = nuevo;
        talla++;
    }
}

package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;
import librerias.estructurasDeDatos.deDispersion.TablaHash;

/** Ejercicio 12, 13, 14, 15 */

public class LEGListaConPIPlusMap<E> extends LEGListaConPIPlus<E> implements ListaConPIPlusMap<E> {
    /** elimina los elementos repetidos de una ListaConPI, 
     *  dejando únicamente su 1ª aparición */
    public void eliminarRepetidos(){
        Map<E, E> aux = new TablaHash<E, E>(this.talla());
        this.inicio();
        while (!this.esFin()) {
            E i = this.recuperar(); //elemento de la ListaConPI
            E f = aux.recuperar(i); //tenemos el elemento en la Tabla?
            if (f == null) { //no está el elemento
                aux.insertar(i, f);
                this.siguiente();
            } 
            else { //sí está el elemento
                this.eliminar(); 
            } 
        }
    }
    
    /** elimina los elementos de una ListaConPI que están en otra **/
    public void diferencia(ListaConPI<E> otra){
        Map<E, E> aux = new TablaHash<E, E>(otra.talla()); //map vacío
        for (otra.inicio(); !otra.esFin(); otra.siguiente()) { //rellenamos el map
            E e = otra.recuperar();
            aux.insertar(e, e);
        }
        this.inicio();
        while (!this.esFin()) {
            E i = this.recuperar(); // elemento de la this.ListaConPI
            E f = aux.recuperar(i); // está el elemento en el map (otra.ListaConPI)?
            if (f == null) {
                this.siguiente();
            }
            else {
                this.eliminar();
            }
        }
    }
}

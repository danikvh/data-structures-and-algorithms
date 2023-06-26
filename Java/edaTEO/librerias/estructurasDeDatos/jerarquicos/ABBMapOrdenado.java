package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.modelos.MapOrdenado;
import librerias.estructurasDeDatos.modelos.*;
import librerias.estructurasDeDatos.lineales.*;

/**
 * Write a description of class ABBMapOrdenado here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ABBMapOrdenado<C extends Comparable<C>, V> implements MapOrdenado<C,V> {
    
    protected ABB<EntradaMap<C, V>> abb;
    
    public ABBMapOrdenado() {
        abb = new ABB<EntradaMap<C, V>>();
    }
    
    /** inserta la Entrada (c,v) en un Map y devuelve null; si ya
     *  existe una Entrada de Clave c en el Map, devuelve su valor 
     *  asociado y lo substituye por v (o actualiza la Entrada)
     */
    public V insertar(C c, V v) {
        EntradaMap<C, V> aux = abb.recuperar(new EntradaMap<>(c, null));
        abb.insertar(new EntradaMap<>(c, v));
        if (aux == null) { return null; }
        return aux.getValor();
    }
    
    /** elimina la Entrada con Clave c de un Map y devuelve su 
     *  valor asociado, null si no existe una Entrada con dicha clave
     */
    public V eliminar(C c) {
        EntradaMap<C, V> aux = abb.recuperar(new EntradaMap<>(c, null));
        abb.eliminar(aux);
        if (aux == null) { return null; }
        return aux.getValor();
    }
    
    /** devuelve el valor asociado a la clave c en un Map,
     *  null si no existe una Entrada con dicha clave
     */
    public V recuperar(C c) {
        EntradaMap<C, V> e = abb.recuperar(new EntradaMap<C, V>(c, null));
        if (e != null) { return e.getValor(); }
        return null;
    }
    
    /** comprueba si un Map esta vacio */
    public boolean esVacio() {
        return abb.esVacio();
    }
    
    /** devuelve la talla, o numero de Entradas, de un Map */
    public int talla() {
        return abb.talla();
    }
    
    /** devuelve una ListaConPI con las talla() Claves de un Map */
    public ListaConPI<C> claves() {
        ListaConPI<C> res = new LEGListaConPI<C>();
        EntradaMap<C, V> e = abb.recuperarMin();
        for (int i = 0; i < abb.talla(); i++) {
            res.insertar(e.getClave());
            e = abb.sucesor(e);
        }
        return res;
    }
    
    /** SII !esVacio(): 
     *  recupera la Entrada de Clave minima de un Map Ordenado */
    public EntradaMap<C, V> recuperarEntradaMin() {
        return abb.recuperarMin();
    }
    /** SII !esVacio(): recupera la Clave minima de un Map Ordenado */
    public C recuperarMin() {
        return abb.recuperarMin().getClave();
    }
   
    /** SII !esVacio(): 
     *  recupera la Entrada de Clave maxima de un Map Ordenado */
    public EntradaMap<C, V> recuperarEntradaMax() {
        return abb.recuperarMax();
    }
        
    /** SII !esVacio(): recupera la Clave maxima de un Map Ordenado */
    public C recuperarMax() {
        return abb.recuperarMax().getClave();
    }

    /** SII !esVacio(): recupera la Entrada siguiente a c
     *  segun el orden establecido entre claves */
    public EntradaMap<C, V> sucesorEntrada(C c) {
        return abb.sucesor(new EntradaMap<C, V>(c, null));
    }
    /** SII !esVacio(): recupera la clave siguiente a c
     *  segun el orden establecido entre claves */
    public C sucesor(C c) {
        return abb.sucesor(new EntradaMap<C, V>(c, null)).getClave();
    }
    
    /** SII !esVacio(): recupera la Entrada anterior a c
     *  segun el orden establecido entre claves */
    public EntradaMap<C, V> predecesorEntrada(C c) {
        return abb.predecesor(new EntradaMap<C, V>(c, null));
    }
    /** SII !esVacio(): recupera la clave anterior a c
     *  segun el orden establecido entre claves */
    public C predecesor(C c) {
        return abb.predecesor(new EntradaMap<C, V>(c, null)).getClave();
    }
    
    /** SII !esVacio(): 
     * elimina y devuelve la Entrada de Clave minima de un Map Ordenado */
    public EntradaMap<C, V> eliminarEntradaMin() {
        return abb.eliminarMin();
    }
    /** SII !esVacio(): 
     *  elimina y devuelve la Clave minima de un Map Ordenado */
    public C eliminarMin() {
        return abb.eliminarMin().getClave();
    }
    
}

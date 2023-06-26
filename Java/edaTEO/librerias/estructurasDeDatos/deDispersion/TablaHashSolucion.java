package librerias.estructurasDeDatos.deDispersion;

 

import librerias.estructurasDeDatos.modelos.Map;
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI; 

/**
 * Implementacion de una TablaHash Enlazada 
 * con Listas con PI y SIN REHASHING
 * 
 * AVISO: Implementacion incompleta de la clase. Incluye los metodos
 * recuperar, eliminar, insertar, localizar, claves
 */

public class TablaHashSolucion<C, V> implements Map<C, V> {    
    // Una Tabla Hash de Entradas de Clave de tipo C y Valor de tipo V ...
    
    /** El valor (float) del factor de carga de una Tabla Hash 
     *  (valor por defecto en la clase java.util.HashMap) 
     */
    public static final double FACTOR_DE_CARGA = 0.75;
    
    /** TIENE UN array de Listas Con PI de Tipo Generico EntradaHashLPI<C, V>:
     *  - elArray[h] representa una cubeta, 
     *    o lista de colisiones asociadas al indice Hash h
     *  - elArray[h] contiene la referencia a la Lista Con PI 
     *    donde se encuentran todas las 
     *    Entradas cuya Clave tiene un indice Hash h 
     */
    protected ListaConPI<EntradaHash<C, V>>[] elArray;
    
    /** TIENE UNA talla que representa el numero de Entradas 
     *  almacenadas en una Tabla Hash o, si se prefiere, en sus cubetas
     */
    protected int talla; 
    
    /** Devuelve el indice Hash de la Clave c de una Entrada, i.e. 
     *  la cubeta en la que se debe encontrar la Entrada de clave c
     *  *** SIN ESTE METODO NO SE TIENE UNA TABLA HASH, SOLO UN ARRAY ***
     */
    protected int indiceHash(C c) {
        int indiceHash = c.hashCode() % elArray.length;
        if (indiceHash < 0) indiceHash += elArray.length; 
        return indiceHash;
    }
    
    /** Crea una Tabla Hash vacia, con una capacidad (inicial) maxima  
     *  de tallaMaximaEstimada entradas y factor de carga 0.75
     */
    @SuppressWarnings("unchecked") 
    public TablaHashSolucion(int tallaMaximaEstimada) {
        int n = (int) (tallaMaximaEstimada / FACTOR_DE_CARGA);
        int capacidad = siguientePrimo(n);
        elArray = new LEGListaConPI[capacidad];
        for (int i = 0; i < elArray.length; i++) 
            elArray[i] = new LEGListaConPI<EntradaHash<C, V>>();
        talla = 0;
    }
    
    // Devuelve un numero primo MAYOR o IGUAL a n, i.e. el primo que sigue a n
    protected static final int siguientePrimo(int n) {
        if (n % 2 == 0) n++;
        for (; !esPrimo(n); n += 2); 
        return n;
    }
    
    // Comprueba si n es un numero primo
    protected static final boolean esPrimo(int n) {
        for (int i = 3; i * i <= n; i += 2) 
            if (n % i == 0) return false; // n NO es primo
        return true; // n SI es primo
    } 
    
    /** Comprueba si una Tabla Hash esta vacia, i.e. si tiene 0 entradas.
      */
    public boolean esVacio() { 
        return talla == 0; 
    }
    
    /** Devuelve la talla, o numero de entradas, de una Tabla Hash.
      */
    public int talla() { 
        return talla; 
    } 
    
    /** metodo auxiliar, invocado en las operaciones
     *  recuperar, eliminar, insertar
     */
    private ListaConPI<EntradaHash<C, V>> localizar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C, V>> cubeta = elArray[pos];
        for (cubeta.inicio(); 
             !cubeta.esFin() && !cubeta.recuperar().clave.equals(c); 
             cubeta.siguiente());
        return cubeta;
    }
    
    /** Devuelve el valor de la entrada con clave c de una Tabla Hash,
     *  o null si no hay una entrada con clave c en la Tabla
     */
    public V recuperar(C c) {
        V valor = null;
        // Busqueda en cubeta de la Entrada de clave c cuyo valor se quiere recuperar 
        ListaConPI<EntradaHash<C, V>> cubeta = localizar(c);
        // Resolucion de la Busqueda: SII esta la Entrada se recupera su valor
        if (!cubeta.esFin()) { valor = cubeta.recuperar().valor; }
        return valor;
    }
    
    /** Elimina la entrada con clave c de una Tabla Hash y devuelve
     *  su valor asociado, o null si no hay ninguna entrada con 
     *  clave c en la Tabla
     */
    public V eliminar(C c) {
        V valor = null;
        // Busqueda en cubeta de la Entrada de clave c a eliminar
        ListaConPI<EntradaHash<C, V>> cubeta = localizar(c);
        // Resolucion de la Busqueda: 
        // SII esta la Entrada se elimina, tras recuperar su valor
        if (!cubeta.esFin()) {
            valor = cubeta.recuperar().valor;
            cubeta.eliminar();
            talla--;
        }
        return valor;
    }
        
    /** Inserta la entrada (c, v)  a una Tabla Hash y devuelve  
     *  el antiguo valor asociado a c, o null si no hay ninguna 
     *  entrada con clave c en la Tabla
     */
    // NO HACE REHASHING. En la practica 3 se modificara este metodo
    // de forma que el rehashing se efectua cuando tras insertar una 
    // nueva entrada en la correspondiente cubeta (lista enlazada 
    // directa) de elArray, e incrementar la talla, ...
    // factorDeCarga() > FACTOR_DE_CARGA.
    // Ello equivale, b�sicamente, a que la talla actual 
    // supere la tallaMaximaEstimada.
    public V insertar(C c, V v) {
        V antiguoValor = null;
        // Busqueda en cubeta de la Entrada de clave c 
        ListaConPI<EntradaHash<C, V>> cubeta = localizar(c);
        // Resolucion de la busqueda: 
        // si la Entrada (c, v) ya existe se actualiza su valor, y sino se inserta
        if (cubeta.esFin()) { // si no esta, insercion efectiva de la Entrada (c, v)
            cubeta.insertar(new EntradaHash<C, V>(c, v));
            talla++;
            // EN LA PRACTICA 3: 
            // if (factorCarga() > FACTOR_DE_CARGA) rehashing();
        }
        else { // si ya esta, actualizar (el valor de la) Entrada y retornar el antiguo
            antiguoValor = cubeta.recuperar().valor;
            cubeta.recuperar().valor = v; 
        }
        return antiguoValor;
    }
    
    /** Devuelve una ListaConPI con las talla() claves de una Tabla Hash
     */
    public ListaConPI<C> claves() {
        ListaConPI<C> l = new LEGListaConPI<C>();
        for (ListaConPI<EntradaHash<C, V>> cubeta : elArray)            
            for (cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente())  
                l.insertar(cubeta.recuperar().clave);
        // Alternativamente:
        /*for (int i = 0; i < elArray.length; i++)
            for (elArray[i].inicio(); !elArray[i].esFin(); elArray[i].siguiente())
                l.insertar(elArray[i].recuperar().clave);
        */
        return l;
    }
}
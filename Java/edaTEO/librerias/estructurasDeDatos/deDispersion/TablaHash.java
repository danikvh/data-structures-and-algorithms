package librerias.estructurasDeDatos.deDispersion;


import librerias.estructurasDeDatos.modelos.Map;

// para implementar toClaves
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

/**
 * Implementacion de una TablaHash Enlazada 
 * con Listas con PI y SIN REHASHING
 */

public class TablaHash<C, V> implements Map<C, V> {
    
    // Una Tabla Hash de Entradas de Clave de tipo C y  Valor de tipo V ...
    
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
    protected ListaConPI<EntradaHash<C,V>>[] elArray;
    
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
        if (indiceHash < 0) { indiceHash += elArray.length; }
        return indiceHash;
    }    
    
    /** Crea una Tabla Hash vacia, con una capacidad (inicial) maxima  
     *  de tallaMaximaEstimada entradas y factor de carga 0.75
     */
    @SuppressWarnings("unchecked") 
    public TablaHash(int tallaMaximaEstimada) {
        int capacidad = siguientePrimo((int) (tallaMaximaEstimada / FACTOR_DE_CARGA));
        elArray = new LEGListaConPI[capacidad];
        for (int i = 0; i < elArray.length; i++) 
            elArray[i] = new LEGListaConPI<EntradaHash<C,V>>();
        talla = 0;
    }
    
    // Devuelve un numero primo MAYOR o IGUAL a n, i.e. el primo que sigue a n
     protected static final int siguientePrimo(int n) {
         if (n % 2 == 0) n++;
         for ( ; !esPrimo(n); n += 2); 
         return n;
     } 
     
     // Comprueba si n es un numero primo
     protected static final boolean esPrimo(int n) {
         for (int i = 3 ; i * i <= n; i += 2) 
            if (n % i == 0) return false; // n NO es primo
         return true; // n SI es primo
     } 
    
    /** Devuelve el valor de la entrada con clave c de una Tabla Hash,
     *  o null si no hay una entrada con clave c en la Tabla
     */
    public V recuperar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C,V>> cubeta = elArray[pos];
        V valor = null;
        /*COMPLETAR*/
        for (cubeta.inicio(); !cubeta.esFin() && !cubeta.recuperar().clave.equals(c); 
            cubeta.siguiente());
        if (!cubeta.esFin()) { valor = cubeta.recuperar().valor; }
        return valor;
    }
    
    /** Elimina la entrada con clave c de una Tabla Hash y devuelve
     *  su valor asociado, o null si no hay ninguna entrada con 
     *  clave c en la Tabla
     */
    public V eliminar(C c) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C,V>> cubeta = elArray[pos];
        V valor = null;
        /*COMPLETAR*/
        for (cubeta.inicio(); !cubeta.esFin() && !cubeta.recuperar().clave.equals(c); 
            cubeta.siguiente());
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
    // Ello equivale, básicamente, a que la talla actual 
    // supere la tallaMaximaEstimada.
    public V insertar(C c, V v) {
        int pos = indiceHash(c);
        ListaConPI<EntradaHash<C,V>> cubeta = elArray[indiceHash(c)];
        V antiguoValor = null;
        /*COMPLETAR*/
        for (cubeta.inicio(); !cubeta.esFin() && !cubeta.recuperar().clave.equals(c); 
            cubeta.siguiente());
        if (!cubeta.esFin()) {
            antiguoValor = cubeta.recuperar().valor;
            cubeta.recuperar().valor = v;
        } 
        else { 
            cubeta.insertar(new EntradaHash<C, V>(c, v));
            talla++; 
        }
        return antiguoValor;
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

    /** Devuelve una ListaConPI con las talla() claves de una Tabla Hash
     */
    public ListaConPI<C> claves() {
        ListaConPI<C> l = new LEGListaConPI<C>();
        /*COMPLETAR*/
        for (int i = 0; i < elArray.length; i++) {
            for (elArray[i].inicio(); !elArray[i].esFin(); elArray[i].siguiente()) {
                l.insertar(elArray[i].recuperar().clave);
            }
        }
        //Alternativamente
        /*for (ListaConPI<EntradaHash<C, V>> cubeta : elArray)            
            for (cubeta.inicio(); !cubeta.esFin(); cubeta.siguiente())  
                l.insertar(cubeta.recuperar().clave);
        */
        return l;
    }
    
    /** Devuelve el factor de carga actual de una Tabla Hash, i.e. la longitud
     *  media de sus cubetas
     */
    // RECUERDA: este metodo tiene 
    // un coste INDEPENDIENTE de la talla del problema
    // NO hace falta calcular con un bucle la longitud media de las cubetas!!!
    public final double factorDeCarga() {
        /*CAMBIAR / COMPLETAR*/
        return (talla / elArray.length);
    }
    
    /*******************************
     * SOLO PARA EJEMPLOS DE TEORIA
     *******************************/
    /** Devuelve un String con las Entradas de una Tabla Hash 
     */
    // RECUERDA: se usa la clase StringBuilder porque es mas eficiente
    public final String toString() {
        StringBuilder res = new StringBuilder();
        /*COMPLETAR*/
        res.append("[");
        for (int i = 0; i < elArray.length; i++) {
            res.append("Cubeta " + i + ": {"); //cubeta
            for (elArray[i].inicio(); !elArray[i].esFin(); elArray[i].siguiente()) { //elementos cubeta
                res.append(elArray[i].recuperar().clave + " | " +
                    elArray[i].recuperar().valor + ", ");
            }
            if (res.charAt(res.length() - 2) == ',') { //corregir último elemento de la cubeta
                res.deleteCharAt(res.length() - 1); res.deleteCharAt(res.length() - 1);
            }
            res.append("}, ");
        }
        res.deleteCharAt(res.length() - 1); res.deleteCharAt(res.length() - 1); //corregir
        res.append("]");
        return res.toString(); 
    }
    
    /** Ejercicio 6. 
     * Añade a la clase TablaHash un método que, dada una Entrada de clave c, 
     * devuelva el número de colisiones que provoca su localización. Por simplificar, 
     * suponer que no existe en la Tabla ninguna Entrada con clave c.*/
    public int numeroColisiones(C c) {
        return elArray[indiceHash(c)].talla();
    }
    
    /** Ejercicio 7.
     * Dado un determinado valor, devuelve una ListaConPI con todas las claves 
     * que tienen asociado dicho valor. */
    public ListaConPI<C> clavesConValor(V v) {
        ListaConPI<C> res = new LEGListaConPI<C>();
        /*COMPLETAR*/
        for (int i = 0; i < elArray.length; i++) {
            for (elArray[i].inicio(); !elArray[i].esFin(); elArray[i].siguiente()) {
                EntradaHash<C,V> aux = elArray[i].recuperar();
                if (aux.valor == v) {
                    res.insertar(elArray[i].recuperar().clave);
                }
            }
        }
        return res;    
    }
    
    /** Ejercicio 10 
     * Método que obtenga la moda de un array genérico v (devuelve el primer elemento de
     * v que se repite más veces). */
    public static <E> E modaDe(E[] v) {
        E moda = null;
        int max = 0;
        Map<E,Integer> aux = new TablaHash<E,Integer>(v.length);
        for (int i = 0; i < v.length; i++) {
            Integer frec = aux.recuperar(v[i]);
            if (frec == null) { frec = 1; }
            else { frec++; } 
            aux.insertar(v[i], frec);
            if (frec > max) {
                max = frec;
                moda = v[i];
            }
        }
        return moda;
    }
    
    /** Ejercicio 11
     * Método estático diferencia que devuelve el Map diferencia de m1 y m2, es decir un
     * map que contenga solo aquellas Entradas de m1 que no estén en m2. */
    public static <C,V> Map<C,V> diferencia(Map<C,V> m1, Map<C,V> m2) {
        Map<C,V> res = new TablaHash<C,V>(m1.talla());
        ListaConPI<C> claves = m1.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()) {
            C clave = claves.recuperar();
            if (m2.recuperar(clave) != m1.recuperar(clave)) { 
                res.insertar(clave, m1.recuperar(clave)); 
            }
        }
        return res;
    }
    
    /** Ejercicio Extra
     * Método estático que, dados un array v de Integer y un Integer e, devuelva un String
     * en el que se indique, con el formato que se muestra en el siguiente ejemplo, los pares
     * de elementos de v que sumen e.
     */
    public static String sumasDeE(Integer[] v, Integer e) {
        String res = "";
        //Map: clave = elemento de v, valor = posición de v
        Map<Integer, Integer> m = new TablaHash<Integer, Integer>(v.length);
        for (int j = 0; j < v.length; j++) {
            Integer i = m.recuperar(e - v[j]); //posicion
            if (i != null) {
                res += "v[" + i + "] + v[" + j + "] = " + v[i] + " + " + v[j] + " = " +
                    e + "\n";
            }
            Integer jRepetido = m.insertar(v[j], j);
            if (jRepetido != null) {
                return "Error: hay elementos repetidos";
            }
        }
        return res;
    }
}
package ejemplos.tema5;

import librerias.estructurasDeDatos.modelos.MapOrdenado;
import librerias.estructurasDeDatos.modelos.EntradaMap;
import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI; 
import librerias.estructurasDeDatos.jerarquicos.ABBMapOrdenado;

/**
 * class UsosMapOrdenado.
 * 
 * @author FTG 
 * @version 1.0
 */

public class UsosMapOrdenado {
    /** Diseñar un metodo estatico, generico e iterativo entradas 
     *  que devuelva una ListaConPI con las Entradas de un Map m 
     *  ordenadas ascendentemente. 
     */
    public static <C extends Comparable<C>, V> 
    ListaConPI<EntradaMap<C, V>> entradas(MapOrdenado<C, V> m) 
    {
        ListaConPI<EntradaMap<C, V>> l = new LEGListaConPI<EntradaMap<C, V>>();
        // COMPLETAR
        
        /** Obtener primera Entrada del Map Ordenado por claves,
         *  i.e. la Entrada de clave minima del Map 
         */
        EntradaMap<C, V> min = m.recuperarEntradaMin();
        
        /** Insertar primer elemento de la lista de Entradas 
         *  ordenada ascendentemente por clave 
         */
        l.insertar(min);
        
        /** Para obtener siguientes Entradas de la lista resultado,
         *  recorrer el Map Ordenado por claves 
         */
        for (int i = 1; i < m.talla(); i++) {
            /** Obtener siguiente Entrada del Map Ordenado por claves,
             *  i.e. el sucesor de la Entrada e 
             */
            min = m.sucesorEntrada(min.getClave());
            
            /** Insertar siguiente elemento de la lista de Entradas
             *  ordenada ascendentemente por clave 
             */
            l.insertar(min);
        }
        return l;
    }
    
    /** DiseÃ±ar un metodo estatico, generico e iterativo mapSort 
     *  que, con la ayuda de un MapOrdenado, 
     *  ordene los elementos (Comparable) de un array v.  
     */
    public static <C extends Comparable<C>> void mapSort(C[] v) {
        // COMPLETAR
        MapOrdenado<C, C> aux = new ABBMapOrdenado<C, C>();
        for (int i = 0; i < v.length; i++) {
            aux.insertar(v[i], v[i]);
        }
        C clave = aux.recuperarMin();
        v[0] = clave;
        for (int i = 1; i < v.length; i++) {
            clave = aux.sucesor(clave);
            v[i] = clave;
        }
    }
    
    /** Diseñar un metodo estatico e iterativo corregirTexto 
     *  que, dados un texto y un MapOrdenado de las palabras correctas 
     *  de un lenguaje dado, devuelva una ListaConPI con las palabras 
     *  incorrectas del texto en orden alfabético.
     *  Usar un Map Ordenado como EDA auxiliar.
     */
    public static <V> ListaConPI<String> corregirTexto(String texto, MapOrdenado<String, V> m){
        ListaConPI<String> res = new LEGListaConPI<>();
        MapOrdenado<String, String> aux = new ABBMapOrdenado<>();
        String[] palabrasDelTexto = texto.split(" ");
        for (int i = 0; i < palabrasDelTexto.length; i++) {
            String palabra = palabrasDelTexto[i].toLowerCase();
            if (m.recuperar(palabra) == null) {
                aux.insertar(palabra, palabra);
            }
        }
        while (!aux.esVacio()) {
             res.insertar(aux.eliminarMin());
        }
        return res;
    }
    
    /** Diseñar un metodo estatico, generico e iterativo hayDosQueSuman 
     *  que, dados un array v de enteros y un entero k, 
     *  determine si existen en v dos numeros cuya suma sea k. 
     *  Usar un Map Ordenado como EDA auxiliar.
     */
    public static boolean hayDosQueSuman(Integer[] v, int k) {
        // COMPLETAR
        MapOrdenado<Integer,Integer> aux = new ABBMapOrdenado<>();
        for (int i = 0; i < v.length; i++) {
            aux.insertar(v[0],v[0]);
        }
        Integer min = aux.recuperarMin();
        if (min > k/2) { return false; }
        Integer max = aux.recuperarMax();
        for (int i = 1; i < v.length; i++) {
            if (min + max == k) { return true; }
            else if (min + max < k) { min = aux.sucesor(min); }
            else { max = aux.predecesor(max); } //min + max > k
        }
        return false;
    }
}

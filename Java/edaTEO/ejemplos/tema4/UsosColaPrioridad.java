package ejemplos.tema4;

import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;

import java.util.Random;

/**
 * class UsosColaPrioridad.
 * 
 * @author FTG 
 * @version 1.0
 */

public class UsosColaPrioridad {
    
    /** Problema 1: 
     *  Disenyar un metodo estatico e iterativo cPSort 
     *  que, con la ayuda de una Cola de Prioridad, 
     *  ordene un array v de elementos Comparable. 
     */
    public static <E extends Comparable<E>> void cPSort(E[] v) {
        // COMPLETAR
        ColaPrioridad<E> aux = new MonticuloBinario<E>();
        for (int i = 0; i < v.length; i++) {
            aux.insertar(v[i]);
        }
        for (int i = 0; i < v.length; i++) { v[i] = aux.eliminarMin(); }
    }
    
    /** Problema 2:
     *  Disenyar un metodo estatico, generico e iterativo cPFusionar 
     *  que devuelva una ListaConPI con los datos de 2 Colas de Prioridad dadas, 
     *  cP1 y cP2, ordenados ascendentemente. 
     *  El metodo no puede usar ninguna EDA auxiliar para calcular su resultado 
     *  y, ademas, cP1 y cP2 deben quedar vacias al concluir su ejecucion.
     */
    public static <E extends Comparable<E>> ListaConPI<E> cPFusionar(
        ColaPrioridad<E> cP1, ColaPrioridad<E> cP2) 
    {
        // COMPLETAR
        ListaConPI<E> res = new LEGListaConPI<E>();
        while (!cP1.esVacia() && !cP2.esVacia()) {
            if (cP1.recuperarMin().compareTo(cP2.recuperarMin()) < 0) {
                res.insertar(cP1.eliminarMin());
            }
            else {
                res.insertar(cP2.eliminarMin());
            }
        }
        while (!cP1.esVacia()) { res.insertar(cP1.eliminarMin()); }
        while (!cP2.esVacia()) { res.insertar(cP2.eliminarMin()); }
        return res;
    }
    
    /** Problema 3:
     *  Disenyar un metodo estatico e iterativo cPEsLineal 
     *  que determine si un conjunto de valores reales se ajusta (aprox.) 
     *  a una funcion lineal creciente usando el siguiente algoritmo: 
     *  comprobar si la diferencia entre todo par de valores consecutivos, 
     *  en orden ascendente, esta acotada por un epsilon dado. 
     */
    public static boolean cPEsLineal(ColaPrioridad<Double> cP, double epsilon) {
        // COMPLETAR
        Double elem1 = cP.eliminarMin();
        while (!cP.esVacia()) {
            Double elem2 = cP.eliminarMin();
            if ((elem2 - elem1) > epsilon) { return false; }
            elem1 = elem2;
        }
        return true;
    }
    
    /** Problema 4:
     *  Disenyar un metodo estatico, generico e iterativo cPTopK 
     *  que, dado un array de datos v y un entero k, 
     *  devuelva una Cola de Prioridad con los k mejores (Top K) datos de v. 
     *  El metodo debe tener un coste O(X log k), siendo X la longitud de v.
     */
    public static <E extends Comparable<E>> ColaPrioridad<E> cPTopK(
        E[] v, int k) 
    {
        // COMPLETAR
        ColaPrioridad<E> res = new MonticuloBinario<E>(k + 2);
        for (int i = 0; i < v.length; i++) {
            res.insertar(v[i]);
            if (i >= k) { res.eliminarMin(); }
        }
        return res;
    }
    
    //Comprobar
    public static void main(String[] args) {
        //Problema 2
        ColaPrioridad<Integer> h1 = new MonticuloBinario<Integer>(10);
        ColaPrioridad<Integer> h2 = new MonticuloBinario<Integer>(10);
        Random r = new Random();
        for (int i = 1; i <= 10; i++) {
            Integer e = new Integer((r.nextInt(10) + 1) * i);
            h1.insertar(e);
            e = new Integer((r.nextInt(10) + 1) * i);
            h2.insertar(e);
        }
        System.out.println("Problema 2: ");
        System.out.println(cPFusionar(h1,h2));
        
        //Problema 3
        ColaPrioridad<Double> h3 = new MonticuloBinario<Double>(10);
        ColaPrioridad<Double> h4 = new MonticuloBinario<Double>(10);
        for (int i = 1; i <= 10; i++) {
            Double e = new Double(r.nextDouble() * 10);
            h3.insertar(e);
        }
        for (int i = 1; i <= 10; i++) {
            h4.insertar(h3.eliminarMin());
        }
        System.out.println("Problema 3: ");
        System.out.println(h4);
        System.out.println(cPEsLineal(h4,2));
        
        //Problema 4
        Integer[] arr = new Integer[20];
        System.out.print("[");
        for (int i = 0; i < 20; i++) {
            Integer e = new Integer((r.nextInt(10) + 1) * i);
            System.out.print(e + ", ");
            arr[i] = e;
        }
        System.out.println("]");
        System.out.println("Problema 4: ");
        System.out.println(arr);
        System.out.println(cPTopK(arr, 4));
    }
}
package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;

import java.io.*;
import java.util.*;

/** 
 *  Clase GrafoNoDirigido
 *  
 *  implementacion de un grafo No Dirigido (Ponderado o no) 
 *  mediante Listas de Adyacencia
 *  
 *  un grafo No Dirigido ES UN Grafo Dirigido tal que 
 *  si la Arista (i, j) está presente en la Lista de Adyacencia de i 
 *  entonces también lo está la Arista (j, i) en la de j
 */

public class GrafoNoDirigido extends GrafoDirigido { 
    /** Construye un grafo No Dirigido vacio con numVertices. 
     *  @param numVertices  Numero de vertices del grafo vacio
     */
    public GrafoNoDirigido(int numVertices) { 
        super(numVertices); 
        esDirigido = false;
    }
    
    /** Si no esta, inserta la arista (i, j) en un grafo 
     *  No Dirigido y No Ponderado; 
     *  por tanto, tambien inserta la arista (j, i).
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     */ 
    public void insertarArista(int i, int j) {
        insertarArista(i, j, 1);
    }
    
    /** Si no esta, inserta la arista (i, j) de peso p en un grafo 
     *  No Dirigido y Ponderado; 
     *  por tanto, tambien inserta la arista (j, i) de peso p.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @param p    Peso de (i, j)
     */ 
    public void insertarArista(int i, int j, int p) {
        if (!existeArista(i, j)) { 
            elArray[i].insertar(new Adyacente(j, p)); 
            elArray[j].insertar(new Adyacente(i, p));
            numA++; 
        }
    }
    
    /** Ejemplo 3, pagina 12, tema 6 */
    public int gradoEntrada(int i) {
        return gradoSalida(i);
    }
    
    /** Actividad 2 a) **/
    public int getVerticeReceptivo() {  //O(|V| + |E|)
        /// Como grados de salida y entrada de un vértice son iguales
        for (int i = 0; i < numV; i++) {
            if (elArray[i].talla() == numV - 1) {
                return i;
            }
        }
        return -1;
    }
    
    /** Actividad 2 b) **/
    public boolean esSumidero(int v) { 
        return false;
    }
    
    /** Actividad 2 c) **/
    public int getSumideroU() { 
        return -1;
    }
    
    /** Actividad 2 d) **/
    public int getFuenteU() {  
        return -1;
    }
    
    /** Actividad 2 e) **/
    //Mi método
    public boolean esCompleto() {
        return 2 * numA == numV * (numV - 1);
    }
    
    /** Actividad 4 **/
    //Implementación DFS
    public boolean esConexo() { 
        ordenVisita = 0;
        visitados = new int[numVertices()];
        esConexo(0);
        if (ordenVisita == numV) { return true; }
        return false;
    } 
    
    protected void esConexo(int v) { 
        ordenVisita++; 
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) esConexo(w);
        }  
    }
    
    /** Actividad 5 **/
    public String toStringCC() {
        String s = "";
        int total = 0;
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices(); v++) {  
            if (visitados[v] == 0) { 
                s = s + "[";
                s = toStringCC(v, s); 
                s = s.substring(0, s.length() - 2);
                s = s + "] ";
                total++;
            }
        }
        return total + " " + s;
    } 
    
    protected String toStringCC(int v, String s) { 
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) { s = toStringCC(w, s); }
        }
        return s + v + ", ";
    } 
    
    /** Actividad 6 **/
    //DFS
    public String[] spanningTree() {
        String[] res = new String[numVertices() - 1]; //nº aristas
        ordenVisita = 0;
        visitados = new int[numVertices()];
        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0) { spanningTree(i, res); }
        }
        if (res[res.length - 1] == null) { return null; }
        return res;
    }
    
    protected void spanningTree(int v, String[] res) {
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();         
            if (visitados[w] == 0) {
                res[ordenVisita] = "(" + v + "," + w + ")";
                ordenVisita++;
                spanningTree(w, res);
            }
        }
    }
    
    //Arbol recubrimiento BFS Practica 6
    public String[] spanningTreeV2() {
        String[] res = new String[numVertices() - 1]; //nº aristas
        ordenVisita = 0;
        visitados = new int[numVertices()];
        q = new ArrayCola<Integer>();
        
        spanningTreeV2(0, res);
        
        if (ordenVisita != numVertices() - 1) { return null; }
        return res;
    }
    
    protected void spanningTreeV2(int v, String[] res) {
        visitados[v] = 1;
        q.encolar(v);
        while (!q.esVacia()) {
            int u = q.desencolar().intValue();
            ListaConPI<Adyacente> l = adyacentesDe(u);
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                int w = l.recuperar().getDestino();         
                if (visitados[w] == 0) {
                    res[ordenVisita++] = "(" + u + "," + w + ")";
                    visitados[w] = 1;
                    q.encolar(w);
                    //break; //para no repetir vértice
                }
            }
        }
    }
    
    
    /** TESTS **/
    public static void main(String[] args) {
        GrafoNoDirigido grafo1 = new GrafoNoDirigido(5);
        grafo1.insertarArista(0,1); grafo1.insertarArista(3,4);
        grafo1.insertarArista(1,2); 
        
        GrafoNoDirigido grafo2 = new GrafoNoDirigido(5);
        grafo2.insertarArista(0,1); grafo2.insertarArista(3,4);
        grafo2.insertarArista(1,2); grafo2.insertarArista(2,3);
        grafo2.insertarArista(1,4); grafo2.insertarArista(2,4);
        
        System.out.println("Grafo 1: " + grafo1.toString());
        System.out.println("5): toStringCC:");
        System.out.println("Grafo 1: " + grafo1.toStringCC());
        
        System.out.println("6): Spanning Tree:");
        System.out.println("Grafo 1: " + grafo1.spanningTree());
        System.out.println("Grafo 2: " + Arrays.toString(grafo2.spanningTree()));
        System.out.println("Grafo 2 V2: " + Arrays.toString(grafo2.spanningTreeV2()));
    }
}
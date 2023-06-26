package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.modelos.*;
import librerias.estructurasDeDatos.lineales.*;

/** Clase abstracta Grafo
 *
 *  Base de la jerarquia Grafo, que define el comportamiento de un grafo.
 *  No es una interfaz porque incluye el codigo de aquellas operaciones de un grafo 
 *  que son independientes tanto de su tipo como de su implementacion.
 */

public abstract class Grafo { 
    // atributos "auxiliares"
    protected boolean esDirigido; // Indica si el grafo es dirigido o no
    protected int[] visitados;    // Nodos visitados para los recorridos
    protected int ordenVisita;    // Orden de visita de los nodos en los recorridos
    protected Cola<Integer> q;    // Cola para el recorrido BFS
    
    /** Crea un grafo vacio, Dirigido si esDirigido es true
     *  o No Dirigido en caso contrario.
     * 
     *  @param esDirigido Indica el tipo del grafo, Dirigido o No
     */
    public Grafo(boolean esDirigido) { this.esDirigido = esDirigido; }
    
    /** Comprueba si un grafo es o no Dirigido.
     *
     *  @return boolean true si el grafo es Dirgido y false si es No Dirigido
     */
    public boolean esDirigido() { return esDirigido; }
    
    /** Devuelve el numero de vertices de un grafo.
     *  @return int numero de vertices de un grafo
     */
    public abstract int numVertices();
    
    /** Devuelve el numero de aristas de un grafo.
     *  @return int numero de aristas de un grafo
     */
    public abstract int numAristas();
    
    /** Comprueba si la arista (i,j) esta en un grafo.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @return boolean true si (i,j) esta y false en caso contrario
     */
    public abstract boolean existeArista(int i, int j);
    
    /** Devuelve el peso de la arista (i,j) de un grafo, 
     *  0 si dicha arista no esta en el grafo.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @return double Peso de la arista (i,j), 0 si no existe.
     */
    public abstract double pesoArista(int i, int j);
    
    /** Si no estï¿½, inserta la arista (i, j) en un grafo no Ponderado.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     */
    public abstract void insertarArista(int i, int j);
    
    /** Si no estï¿½, inserta la arista (i, j) de peso p en un grafo Ponderado.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @param p    Peso de la arista (i,j)
     */
    public abstract void insertarArista(int i, int j, double p);

    /** Devuelve una Lista Con PI que contiene los adyacentes al vertice i de un grafo.
     *  @param i Vertice del que se obtienen los adyacentes
     *  @return ListaConPI con los vertices adyacentes a i
     */
    public abstract ListaConPI<Adyacente> adyacentesDe(int i);
       
    /** Devuelve un String 
     *  con cada uno de los vertices de un grafo y sus adyacentes, 
     *  en orden de insercion 
     *  @return  String que representa a un grafo
     */        
    public String toString() {
        String res = "";  
        for (int i = 0 ; i < numVertices(); i++) {
            res += "Vertice: " + i;
            ListaConPI<Adyacente> l = adyacentesDe(i);
            if (l.esVacia()) { res += " sin Adyacentes "; }
            else { res += " con Adyacentes "; } 
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                res +=  l.recuperar() + " ";  
            }
            res += "\n";  
        }
        return res;      
    }  
    
    /** Actividad 3 **/
    //Mi método
    public int[] finDelDFS () {
        int[] res = new int[numVertices()]; 
        ordenVisita = numVertices() - 1;
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices(); v++) {  
            if (visitados[v] == 0) finDelDFS(v, res);
        }
        return res;
    }

    protected void finDelDFS(int v, int[] res) { 
        res[ordenVisita] = v; 
        ordenVisita--; 
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) finDelDFS(w, res);
        }
    }  

    
    //Método boletín
    public int[] finDelDFSV2 () {
        int[] res = new int[numVertices()]; 
        ordenVisita = 0;
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices(); v++) {  
            if (visitados[v] == 0) finDelDFSV2(v, res);
        }
        return res;
    }


    protected void finDelDFSV2(int v, int[] res) { 
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) finDelDFSV2(w, res);
        }
        res[ordenVisita] = v;
        ordenVisita++;
    }
    
    //Ejercicio 1 Plus 
    // Usar BFS y no DFS ya que hay que introducir ordenadamente //
    //CARENCIAS
    public String verticesCercanos(int v, int n) {
        String res = "";
        visitados = new int[numVertices()];
        int cont = 0;
        q = new ArrayCola<Integer>();
        visitados[v] = 1;
        q.encolar(v);
        res += "["; 
        while (!q.esVacia()) {
            int u = q.desencolar();
            cont++;
            if (cont <= n) { 
                ListaConPI<Adyacente> l = adyacentesDe(u);
                for (l.inicio(); !l.esFin(); l.siguiente()) {
                    int w = l.recuperar().getDestino();
                    if (visitados[w] == 0) {
                        res += "(" + w + ", " + cont + ") ";
                        visitados[w] = 1;
                    }
                }
            }
        }
        return res + "]";
    }
    
    //Solución Boletín V1
    public String verticesCercanosV2(int v, int n) {
        double[] distanciaMin = new double[numVertices()];
        for (int i = 1; i < numVertices(); i++) {
            distanciaMin[i] = Double.POSITIVE_INFINITY;
        }
        distanciaMin[v] = 0;
        q = new ArrayCola<Integer>();
        q.encolar(v);
        String res = "[";
        while (!q.esVacia()) {
            int u = q.desencolar();
            if (distanciaMin[u] < n) {
                ListaConPI<Adyacente> l = adyacentesDe(u);
                for (l.inicio(); !l.esFin(); l.siguiente()) {
                    int w = l.recuperar().getDestino();
                    if (distanciaMin[w] == Double.POSITIVE_INFINITY) {
                        distanciaMin[w] = distanciaMin[u] + 1;
                        res += "(" + w + ", " + (int)distanciaMin[w] + ") ";
                        q.encolar(w);
                    }
                }
            }
        }
        return res + "]";
    }
    
    //Ejercicio 2 Plus 
    public int maxVerticesCC() {
        int res = -1;
        visitados = new int[numVertices()];
        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0) {
                int verticesCCv = maxVerticesCC(i);
                if (verticesCCv > res) { res = verticesCCv; }
            }
        }
        return res + 1;
    }
    
    protected int maxVerticesCC(int v) {
        int res = 0;
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) {
                res += 1 + maxVerticesCC(w);
            }
        }
        return res;
    }
    
    //Ejercicio 3 Plus
    public int aristasHA() {
        int res = 0;
        visitados = new int[numVertices()];
        for (int i = 0; i < numVertices(); i++) {
            if (visitados[i] == 0) { res += aristasHA(i); }
        }
        return res;
    }
    
    protected int aristasHA(int v) {
        int res = 0;
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) {
               res += aristasHA(w);
            } else if (visitados[w] == 1) {
                res++;
            }
        }
        visitados[v] = 2;
        return res;
    }
    
    //Ejercicio 4 Plus
    public boolean colorDFS() {
        visitados = new int[numVertices()];
        boolean[] colores = new boolean[numVertices()];
        colores[0] = true;
        visitados[0] = 1;
        return colorDFS(0, colores);
    }
    
    protected boolean colorDFS(int v, boolean[] colores) {
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) {
                colores[w] = !colores[v];
                visitados[w] = 1;
                if (!colorDFS(w,colores)) { return false; }
            } else if (colores[w] != !colores[v]) { return false; }
        }
        return true;
    }
    
    public boolean colorBFS() {
        visitados = new int[numVertices()];
        boolean[] colores = new boolean[numVertices()];
        colores[0] = true;
        visitados[0] = 1;
        q = new ArrayCola<Integer>();
        q.encolar(0);
        while (!q.esVacia()) {
            int u = q.desencolar();
            ListaConPI<Adyacente> l = adyacentesDe(u);
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                int w = l.recuperar().getDestino();
                if (visitados[w] == 0) {
                    colores[w] = !colores[u];
                    visitados[w] = 1;
                    q.encolar(w);
                } else if (colores[w] != !colores[u]) {
                    return false;
                }
            }
        }
        return true;
    }
}
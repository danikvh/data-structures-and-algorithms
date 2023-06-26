package librerias.estructurasDeDatos.grafos;

import librerias.estructurasDeDatos.modelos.ListaConPI; 
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.estructurasDeDatos.modelos.ColaPrioridad;
import librerias.estructurasDeDatos.jerarquicos.MonticuloBinario;

import java.util.Arrays;

/** 
 *  Clase GrafoDirigido
 *  
 *  implementacion de un grafo Dirigido (Ponderado o no) 
 *  mediante Listas de Adyacencia
 */

public class GrafoDirigido extends Grafo {     
    protected int numV; 
    protected int numA;
    protected ListaConPI<Adyacente>[] elArray;
    
    /** Construye un grafo Dirigido vacio con numVertices. 
     *  @param numVertices  Numero de vertices del grafo vacio
     */
    @SuppressWarnings("unchecked")
    public GrafoDirigido(int numVertices) {
        super(true);
        numV = numVertices; 
        numA = 0;
        elArray = new ListaConPI[numVertices]; 
        for (int i = 0; i < numV; i++) 
            elArray[i] = new LEGListaConPI<Adyacente>();
    }
    
    /**
     *  Devuelve el numero de vertices de un grafo. 
     *  @return  int Numero de vertices de un grafo
     */
    public int numVertices() { return numV; }
     
    /**
     *  Devuelve el numero de aristas de un grafo.
     *  @return  int Numero de aristas de un grafo
     */
    public int numAristas() { return numA; }
    
    /** Devuelve una Lista Con PI que contiene 
     *  los adyacentes al vertice i de un grafo.
     *  @param i Vertice del que se obtienen los adyacentes
     *  @return ListaConPI con los vertices adyacentes a i
     */
    public ListaConPI<Adyacente> adyacentesDe(int i) { 
        return elArray[i]; 
    }
    
    /** Comprueba si la arista (i,j) esta en un grafo.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @return boolean true si (i,j) esta y false en caso contrario
     */
    public boolean existeArista(int i, int j) {
        ListaConPI<Adyacente> l = elArray[i]; 
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            if (l.recuperar().getDestino() == j) { return true; }
        } 
        return false;   
    }
    
    /** Devuelve el peso de la arista (i,j) de un grafo, 
     *  0 si dicha arista no esta en el grafo.
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @return double Peso de la arista (i,j), 0 si no existe.
     */
    public double pesoArista(int i, int j) {
        ListaConPI<Adyacente> l = elArray[i];
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            if (l.recuperar().getDestino() == j) {
                return l.recuperar().getPeso();
            }
        }
        return 0.0;
    }
    
    /** Si no esta, inserta la arista (i, j) en un grafo no Ponderado 
     *  (al principio de la Lista de adyacentes a i).
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     */    
    public void insertarArista(int i, int j) {
        insertarArista(i, j, 1);
    }

    /** Si no esta, inserta la arista (i, j) de peso p en un grafo Ponderado 
     *  (al principio de la Lista de adyacentes a i).
     *  @param i    Vertice origen
     *  @param j    Vertice destino
     *  @param p    Peso de (i, j)
     */ 
    public void insertarArista(int i, int j, double p) {
        if (!existeArista(i, j)) { 
            elArray[i].insertar(new Adyacente(j, p)); 
            numA++; 
        }
    }

    /** Ejemplo 1, pagina 11, tema 6 */
    public int gradoSalida(int i) { 
        return elArray[i].talla(); 
    }

    /** Ejemplo 2, pagina 11, tema 6 */
    public int gradoSalida() { 
        int gradoMax = gradoSalida(0);
        for (int i = 1; i < numV; i++) { 
            int grado = gradoSalida(i); 
            if (grado > gradoMax) gradoMax = grado;
        }
        return gradoMax;
    }

    /** Ejemplo 3, pagina 12, tema 6 */
    public int gradoEntrada(int i) { 
        int grado = 0;
        for (int j = 0;  j < numV;  j++) 
            if (existeArista(j, i)) grado++;  
        return grado;
    }
    
    /** Ejemplo 4, pagina 15, tema 6 */    
    private int[] getArrayGradosEntrada() {
        // crear e inicializar el array de contadores
        int[] gradoEntrada = new int[numV];
        for (int i = 0; i < numV; i++) {
            // actualizar el contador del grado de Entrada
            // de cada vertice de la Lista adyacentesDe(i) 
            ListaConPI<Adyacente> l = elArray[i];
            for ( l.inicio(); !l.esFin(); l.siguiente()) {
                gradoEntrada[l.recuperar().getDestino()]++;
            }
        }
        return gradoEntrada;
    }
    
    public int gradoEntrada() { 
        int[] gradoEntrada = getArrayGradosEntrada();
        // para todo i : 0 <= i < numV : 
        // gradoEntrada[i] es el grado de Entrada de i
        int gradoMax = gradoEntrada[0];
        for (int i = 1; i < numV; i++) { 
            int grado = gradoEntrada[i]; 
            if (grado > gradoMax) gradoMax = grado; 
        }
        return gradoMax;
    }
    
    /** paginas 28 y 29, tema 6 */
    public int[] toArrayDFS() {
        int[] res = new int[numVertices()]; 
        ordenVisita = 0;
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices(); v++) {  
            if (visitados[v] == 0) toArrayDFS(v, res);
        }
        return res;
    } 
    
    protected void toArrayDFS(int v, int[] res) { 
        res[ordenVisita] = v; 
        ordenVisita++; 
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) toArrayDFS(w, res);
        }
    }  
    
    /** paginas 34 y 35, tema 6 */
    public int[] toArrayBFS() {
        int[] res = new int[numVertices()]; 
        visitados = new int[numVertices()];
        ordenVisita = 0; 
        q = new ArrayCola<Integer>(); 
        for (int v = 0; v < numVertices(); v++) { 
            if (visitados[v] == 0) toArrayBFS(v, res); 
        }
        return res;
    }  
    
    protected void toArrayBFS(int v, int[] res) { 
        visitados[v] = 1; 
        res[ordenVisita++] = v; 
        q.encolar(v);
        while (!q.esVacia()) {
            int u = q.desencolar(); 
            ListaConPI<Adyacente> l = adyacentesDe(u);
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                int w = l.recuperar().getDestino();
                if (visitados[w] == 0) { 
                    visitados[w] = 1; 
                    res[ordenVisita++] = w; 
                    q.encolar(w);
                }
            } 
        }
    }
    
    /** paginas 40, 41 y 42, tema 6 */
    // atributos "auxiliares"
    protected double[] distanciaMin; 
    protected int[] caminoMin; 
    protected static final double INFINITO = Double.POSITIVE_INFINITY; 
    
    public void caminosMinimosSinPesos(int v) {
        caminoMin = new int[numVertices()]; 
        distanciaMin = new double[numVertices()];
        for (int i = 0; i < numVertices(); i++) { 
            distanciaMin[i] = INFINITO;
            caminoMin[i] = -1;        
        } 
        distanciaMin[v] = 0;
        q = new ArrayCola<Integer>(); 
        q.encolar(v);
        while (!q.esVacia()) {
            int u = q.desencolar(); 
            ListaConPI<Adyacente> l = adyacentesDe(u);
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                int w = l.recuperar().getDestino();
                if (distanciaMin[w] == INFINITO) { 
                    distanciaMin[w] = distanciaMin[u] + 1; 
                    caminoMin[w] = u; 
                    q.encolar(w);
                }
            }
        }
    }  

    /** paginas 45 y 46, tema 6 */
    
    /** SII v != w AND 0 <= v < numVertices() AND  0 <= w < numVertices() 
     *  devuelve una ListaConPI con los vÃ©rtices del camino mÃ­nimo 
     *  SIN pesos entre v y w, o una lista vacÃ­a si tal camino no existe 
     */
    public ListaConPI<Integer> caminoMinimoSinPesos(int v, int w) { 
        caminosMinimosSinPesos(v);
        return decodificarCaminoHasta(w);
    }
    
    // SII 0 <= w < numVertices()
    // devuelve una ListaConPI con los vÃ©rtices del camino hasta w 
    protected ListaConPI<Integer> decodificarCaminoHasta(int w) {
        ListaConPI<Integer> res = new LEGListaConPI<Integer>();
        if (distanciaMin[w] != INFINITO) {
            res.insertar(w);
            for (int v = caminoMin[w]; v != -1; v = caminoMin[v]) {
                res.inicio();
                res.insertar(v);
            }
        }        
        return res;
    }

    /** paginas 47, 53 y 54, tema 6 */
    
    /** SII v != w AND 0 <= v < numVertices() AND  0 <= w < numVertices() 
     *  devuelve una ListaConPI con los vÃ©rtices del camino 
     *  mÃ­nimo CON pesos entre v y w, o una lista vacÃ­a si
     *  tal camino no existe */
    public ListaConPI<Integer> caminoMinimo(int v, int w) { 
        dijkstra(v);
        return decodificarCaminoHasta(w);
    }
    
    public void dijkstra(int u) {
        distanciaMin = new double[numVertices()]; 
        caminoMin = new int[numVertices()];        
        for (int i = 0; i < numVertices(); i++) {
            distanciaMin[i] = INFINITO; 
            caminoMin[i] = -1; 
        }
        distanciaMin[u] = 0;
        visitados = new int[numVertices()];
        ColaPrioridad<VerticeCoste> qP;
        qP = new MonticuloBinario<VerticeCoste>(); 
        qP.insertar(new VerticeCoste(u, 0));
        // mientras haya vertices por explorar
        while (!qP.esVacia()) { 
            // siguiente vertice a explorar es el de menor distancia
            int v = qP.eliminarMin().codigo;
            if (visitados[v] == 0) { // evitar repeticiones
                visitados[v] = 1; 
                // recorrer vertices adyacentes de v
                ListaConPI<Adyacente> l = adyacentesDe(v);
                for (l.inicio(); !l.esFin(); l.siguiente()) {
                    int  w = l.recuperar().destino;
                    double costeW = l.recuperar().peso;
                    // Â¿ mejor forma de alcanzar w es a traves de v ?
                    if (distanciaMin[w] > distanciaMin[v] + costeW) {
                        distanciaMin[w] = distanciaMin[v] + costeW; 
                        caminoMin[w] = v;
                        qP.insertar(new VerticeCoste(w, distanciaMin[w]));  
                    }
                }
            }
        }
    }
    
    /** pagina 60, tema 6 */
    /* SII el Grafo es un DAG */
    public int[] ordenTopologicoDFS() {
        int[] res = new int[numVertices()]; 
        ordenVisita = 0;
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices(); v++) {
            if (visitados[v] == 0) ordenTopologicoDFS(v, res);  
        }
        return res;
    }
    
    protected void ordenTopologicoDFS(int v, int[] res) { 
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) ordenTopologicoDFS(w, res);         
        }
        visitados[v] = 2;
        res[numVertices() - 1 - ordenVisita] = v; 
        ordenVisita++;
    }  

    /** pagina 64, tema 6 */
    // SII el Grafo es un Digrafo ...
    public boolean hayCicloDFS() {
        boolean ciclo = false; 
        visitados = new int[numVertices()];
        for (int v = 0; v < numVertices() && !ciclo; v++) 
            if (visitados[v] == 0) ciclo = hayAristaHADFS(v); 
        return ciclo;
    }
    
    protected boolean hayAristaHADFS(int v) { 
        boolean aristaHA = false; 
        visitados[v] = 1;
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin() && !aristaHA; l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) aristaHA = hayAristaHADFS(w); 
            else if (visitados[w] == 1) aristaHA = true;          
        }
        visitados[v] = 2;
        return aristaHA;
    }  

    /** paginas 65 y 66, tema 6 */
    // SII el Grafo es Dirigido ...
    public int[] hayCicloyOrdenTopologicoDFS() {
        int[] res = new int[numVertices()]; 
        ordenVisita = 0; 
        visitados = new int[numVertices()]; 
        boolean ciclo = false; 
        for (int v = 0; v < numVertices() && !ciclo; v++) {
            if (visitados[v] == 0) ciclo = hayCicloyOrdenTopologicoDFS(v, res); 
        }
        if (!ciclo) return res; 
        return null; 
    }
    
    protected boolean hayCicloyOrdenTopologicoDFS(int v, int[] res) { 
        boolean aristaHA = false; 
        visitados[v] = 1; 
        ListaConPI<Adyacente> l = adyacentesDe(v);
        for (l.inicio(); !l.esFin() && !aristaHA; l.siguiente()) {
            int w = l.recuperar().getDestino();
            if (visitados[w] == 0) {
                aristaHA = hayCicloyOrdenTopologicoDFS(w, res);
            }
            else if (visitados[w] == 1) {
                aristaHA = true;
            }
        }
        visitados[v] = 2;
        res[numVertices() - 1 - ordenVisita] = v; 
        ordenVisita++;
        return aristaHA;
    }  
    
    /** Actividad 1 a) **/
    //Mi solución
    public int grado(int v) {   // O(|V| + |E|)
        return gradoEntrada(v) + gradoSalida(v);
    }
    
    public int grado() {   // O(|V| + |E|))
        int gradoEntrada = 0;        
        int gradoSalida = 0;
        for (int i = 0; i < numV; i++) {
            gradoSalida += gradoSalida(i);
            ListaConPI<Adyacente> l = elArray[i];
            gradoEntrada += l.talla();
        }
        return gradoEntrada + gradoSalida;
    }
    
    
    //Solución boletín
    public int gradoV2() {
        //PASO 1: almacenar en gradoV[i] el grado de cada vértice i
        int[] gradoV = getArrayGrados();
        //PASO 2: calcular el (primer) máximo del array gradoV
        return maximo(gradoV);
    }
    
    protected int[] getArrayGrados() {
        int[] grados = new int[numV];
        for (int i = 0; i < numV; i++) {
            ListaConPI<Adyacente> l = elArray[i];
            //Actualizar grado vértice i
            //con su grado de salida
            grados[i] += l.talla();
            //Actualizar grado vértices **adyacentes** a i
            //con "parte" de su grado de entrada
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                grados[l.recuperar().getDestino()]++;
            }
        }
        return grados;
    }
    
    protected int maximo(int[] v) {
        int max = v[0];
        for (int i = 1; i < v.length; i++) {
            if (v[i] > max) { max = v[i]; }
        }
        return max;
    }
    
    /** Actividad 1 b) **/
    public double aristaMayorPeso() {  // O(|V| + |E|)
        double res = -1;
        for (int i = 0; i < numV; i++) {
            ListaConPI<Adyacente> l = elArray[i];
            for (l.inicio(); !l.esFin(); l.siguiente()) {
                double a = l.recuperar().getPeso();
                if (a > res) { res = a; }
            }
        }
        return res;
    }
    
    /** Actividad 1 c) **/
    //Mi método
    public boolean esRegular() {  // O((|V| + |E|) + |V|)
        //Grados de entrada
        int[] gradoEntrada = getArrayGradosEntrada();
        int grado = gradoEntrada[0] + gradoSalida(0);
        for (int i = 1; i < numV; i++) {
            if (gradoEntrada[i] + gradoSalida(i) != grado) {
                return false;
            }
        }
        //Coste O((|V| + |E|)^2)
        //int grado = grado(0);    
        //for (int i = 1; i < numV; i++) {
        //    if (grado(i) != grado) {
        //        return false;
        //    }
        //}
        return true;
    }
    
    
    //Método Boletín
    public boolean esRegularV2() {
        //PASO 1: calcular el grado vértices EFICIENTEMENTE
        int[] gradoV = getArrayGrados();
        //PASO 2: comprobar si es Regular. Búsqueda del 1er vértice
        //de grado distinto a los demás, ej., al grado vértice 0
        int gradoV0 = gradoV[0];
        for (int i = 1; i < numV; i++) {
            if (gradoV[i] != gradoV0) { return false; }
        }
        return true;
    }
    
    /** Actividad 2 a) **/
    public int getVerticeReceptivo() {  //O(|V| + |E|)
        //Paso 1: Cálculo del grado de entrada de cada vértice
        int[] gradoEntrada = getArrayGradosEntrada(); 
        //Paso 2: Buscar vértice i tal que gradoEntrada[i] = numV ? 1
        for (int i = 0; i < numV; i++) {
            if (gradoEntrada[i] == numV - 1) {
                return i;
            }
        }
        return -1;
    }
    
    /** Actividad 2 b) **/
    //Mi método
    public boolean esSumidero(int v) {  //O(|V| + |E|)
        if (gradoSalida(v) > 0) { return false; }
        for (int i = 0;  i < numV;  i++) 
            if (existeArista(i, v)) { return true; } 
        return false;
    }
    
    //Método boletín
    public boolean esSumideroV2(int v) {
        return gradoEntrada(v) > 0 && gradoSalida(v) == 0;
    }
    
    /** Actividad 2 c) **/
    public int getSumideroU() {  //O((|V| + |E|) + |V|)
        int[] gradoEntrada = getArrayGradosEntrada();
        for (int i = 0; i < numV; i++) {
            if (gradoSalida(i) == 0 && 
                gradoEntrada[i] == numV - 1) { return i; }
        }
        return -1;
    }
    
    /** Actividad 2 d) **/
    public int getFuenteU() {  //O((|V| + |E|) + |V|)
        int[] gradoEntrada = getArrayGradosEntrada();
        for (int i = 0; i < numV; i++) {
            if (gradoEntrada[i] == 0 &&
                gradoSalida(i) == numV - 1) { return i; }
        }
        return -1;
    }
    
    /** Actividad 2 e) **/
    //Mi método
    public boolean esCompleto() {
        //Un grafo completo es un grafo simple donde cada par de vï¿½rtices distintos 
        //están conectados por una única arista (bidireccional en el caso de GD)
        // int[] gradoEntrada = getArrayGradosEntrada();
        // for (int i = 0; i < numV; i++) {
            // if (gradoSalida(i) < numV - 1) { return false; }
            // if (gradoEntrada[i] < numV - 1) { return false; }
        // }
        //Un grafo simple es completo si cada uno de sus vï¿½rtices es adyacente al resto
        for (int i = 0; i < numV; i++) {
            if (adyacentesDe(i).talla() < numV - 1) { return false; }
        }
        return true;
    }
    
    
    //Método boletín
    public boolean esCompletoV2() {
        return numA == numV * (numV - 1);
    }
    
    
    /** TESTS **/
    public static void main(String[] args) {
        GrafoDirigido grafo1 = new GrafoDirigido(5);
        grafo1.insertarArista(0,1,5); grafo1.insertarArista(0,4,6);
        grafo1.insertarArista(1,2,3); grafo1.insertarArista(2,2,4);
        grafo1.insertarArista(3,2,1); grafo1.insertarArista(4,2,2);
        
        GrafoDirigido grafo2 = new GrafoDirigido(4);
        grafo2.insertarArista(0,1,5); grafo2.insertarArista(2,3,2);
        
        GrafoDirigido grafo3 = new GrafoDirigido(4);
        grafo3.insertarArista(0,1,5); grafo3.insertarArista(2,1,2);
        grafo3.insertarArista(3,1,3); grafo3.insertarArista(0,3,2);
        grafo3.insertarArista(1,3,3); grafo3.insertarArista(2,3,2);
        grafo3.insertarArista(1,0,2);
        
        GrafoDirigido grafo4 = new GrafoDirigido(3);
        grafo4.insertarArista(0,1,5); grafo4.insertarArista(2,1,2);
        grafo4.insertarArista(0,2,2);
        
        GrafoDirigido grafo5 = new GrafoDirigido(3);
        grafo5.insertarArista(0,1,5); grafo5.insertarArista(0,2,2);
        grafo5.insertarArista(1,0,5); grafo5.insertarArista(1,2,2);
        grafo5.insertarArista(2,0,5); grafo5.insertarArista(2,1,2);
        
        
        System.out.println("******* TESTS *******");
        
        System.out.println("Grado de vértice:");
        System.out.println("Grafo 1, vértice 2 de grado 5, resultado: " + grafo1.grado(2));
        System.out.println("Grado del grafo:");
        System.out.println("Grafo 1 de grado 12, resultado: " + grafo1.grado());
        
        System.out.println("Grado de vértice (Boletín):");
        System.out.println("Grafo 1, vértice 2 de grado 5, resultado: " + grafo1.grado(2));
        System.out.println("Grado del grafo:");
        System.out.println("Grafo 1 de grado 5, resultado: " + grafo1.gradoV2());
        
        System.out.println("Arista mayor peso:");
        System.out.println("Grafo 1 es 6, resultado: " + grafo1.aristaMayorPeso());
        
        System.out.println("Es regular:");
        System.out.println("Grafo 1 no es regular, resultado: " + grafo1.esRegular());
        System.out.println("Grafo 2 es regular, resultado: " + grafo2.esRegular());
        
        System.out.println("2 a): Vï¿½rtice receptivo:");
        System.out.println("Grafo 3 vï¿½rtice receptivo 1, resultado: " + grafo3.getVerticeReceptivo());
        
        System.out.println("2 b): Vertice sumidero:");
        System.out.println("Grafo 3 vertice 1 no, resultado: " + grafo3.esSumidero(1));
        System.out.println("Grafo 2 vertice 1 si, resultado: " + grafo2.esSumidero(1));
        
        System.out.println("2 c): Sumidero universal:");
        System.out.println("Grafo 3 no hay, resultado: " + grafo3.getSumideroU());
        System.out.println("Grafo 4 vertice 1, resultado: " + grafo4.getSumideroU());
        
        System.out.println("2 e): Grafo completo:");
        System.out.println("Grafo 3 no es, resultado: " + grafo3.esCompleto());
        System.out.println("Grafo 5 si es, resultado: " + grafo5.esCompleto());
        
        System.out.println("3): finDelDFS:");
        System.out.println("Grafo 1 DFS, resultado: " + Arrays.toString(grafo1.toArrayDFS()));
        System.out.println("Grafo 1 finDelDFS, resultado: " + Arrays.toString(grafo1.finDelDFS()));
        System.out.println("Grafo 1 finDelDFSV2, resultado: " + Arrays.toString(grafo1.finDelDFSV2()));
    }
}
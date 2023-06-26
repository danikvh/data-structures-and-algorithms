package librerias.estructurasDeDatos.jerarquicos; 
import  librerias.estructurasDeDatos.modelos.*; 

public class MonticuloBinario<E extends Comparable<E>> 
    implements ColaPrioridad<E> {
        
    protected static final int C_P_D = 11; 
    protected E[] elArray; 
    protected int talla;
  
    /** crea un Heap vacio */
    public MonticuloBinario() { this(C_P_D); }
    
    /** crea una Cola de Prioridad (CP) vacia con capacidad inicial n */
    @SuppressWarnings("unchecked")
    public MonticuloBinario(int n) { 
        elArray = (E[]) new Comparable[n];
        talla = 0;
    }
  
    /** comprueba si un Heap es vacio en Theta(1) */
    public boolean esVacia() { return talla == 0; }
      
    /** devuelve el minimo de un Heap en Theta(1) */
    public E recuperarMin() { return elArray[1]; }

    /** inserta e en un Heap */
    public void insertar(E e) {
        if (talla == elArray.length - 1) duplicarArray();
        // PASO 1. Buscar la posicion de insercion ordenada de e
        // (a) Preservar la Propiedad Estructural
        int posIns = ++talla; 
        
        // (b) Preservar la Propiedad de Orden: reflotar 
        posIns = reflotar(e, posIns); 
        /*
        while (posIns > 1 && e.compareTo(elArray[posIns / 2]) < 0) { 
            elArray[posIns] = elArray[posIns / 2]; 
            posIns = posIns / 2;
        }
        */
        // PASO 2. Insertar e en su posicion de insercion ordenada
        elArray[posIns] = e;
    }
    
    protected int reflotar(E e, int posIns) {
        while (posIns > 1 && e.compareTo(elArray[posIns / 2]) < 0) { 
            elArray[posIns] = elArray[posIns / 2]; 
            posIns = posIns / 2;
        }
        return posIns;
    }

    @SuppressWarnings("unchecked")
    protected void duplicarArray() {
        E[] nuevo = (E[]) new Comparable[elArray.length * 2];
        System.arraycopy(elArray, 1, nuevo, 1, talla);
        elArray = nuevo;
    }  
  
    /** recupera y elimina el minimo de un Heap */
    public E eliminarMin() {
        E elMinimo = elArray[1];
        // PASO 1. Borrar el minimo del Heap
        // (a) Preservar la Propiedad Estructural: 
        //     borrar Por Niveles el minimo
        elArray[1] = elArray[talla--];
        // (b) Preservar la Propiedad de Orden:
        //     buscar la posicion de insercion ordenada de elArray[1] 
        // PASO 2. Insertar elArray[1] en su posicion ordenada
        hundir(1); 
        return elMinimo;
    }
  
    protected void hundir(int pos) {
        int posActual = pos; 
        E aHundir = elArray[posActual]; 
        int hijo = posActual * 2; 
        boolean esHeap = false; 
        while (hijo <= talla && !esHeap) {
            if (hijo < talla && 
                elArray[hijo + 1].compareTo(elArray[hijo]) < 0) {
                hijo++;
            }
            if (elArray[hijo].compareTo(aHundir) < 0) {
                elArray[posActual] = elArray[hijo];
                posActual = hijo;  
                hijo = posActual * 2; 
            } 
            else { esHeap = true; }
        }
        elArray[posActual] = aHundir;
    }

    /** obtiene un String con los datos de una CP ordenados Por Niveles 
     *  y con el formato que se usa en el estandar de Java (entre corchetes
     *  cuadrados y separando cada elemento del anterior mediante una coma 
     *  seguida de un espacio en blanco); si la CP esta vacia el String 
     *  resultado es []
     */
    public String toString() { 
      // NOTA: se usa la clase StringBuilder, en lugar de String, 
      // por motivos de eficiencia
        StringBuilder res = new StringBuilder();
        if (talla == 0) return res.append("[]").toString();
        int i = 1;
        res.append("[");
        while (i < talla) res.append(elArray[i++] + ", ");
        res.append(elArray[i].toString() + "]"); 
        return res.toString();
    }
    
    /** devuelve el numero de hojas de un Heap en Theta(1) */
    public int contarHojas() { 
        return talla - (talla / 2);
    }
    
    /** devuelve el maximo de un Heap tras talla/2 compareTo */
    public E recuperarMax() { 
        int pos = talla / 2 + 1;
        E max = elArray[pos];
        while (pos <= talla) {
            if (elArray[pos].compareTo(max) > 0) {
                max = elArray[pos];
            } 
            pos++;
        }
        return max;
    }
    
    public void introducir(E e) {
        if (talla == elArray.length - 1) { duplicarArray(); }
        elArray[++talla] = e;
    }
    
    public void arreglar() { arreglar(1); }
    
    protected void arreglar(int i) {
        if  (i <= talla / 2) { //si no es una Hoja
            if (2 * i <= talla) { arreglar(2 * i); }
            if (2 * i + 1 <= talla) { arreglar(2 * i + 1); } 
            hundir(i); 
        }
    }
    
    /*  Restablece la propiedad de orden de un Heap */ 
    //  hunde Por-Niveles y Descendente los nodos Internos 
    //  de elArray, pues las Hojas ya son Heaps
    public void arreglarIterativo() {
        for (int i = talla / 2; i > 0; i--) {
            hundir(i);
        }
    } 

    /** Ejercicio 5 */
    public boolean hayMenoresQue(E e) {
        if (talla == 0) { return false; }
        return elArray[1].compareTo(e) < 0;
    }
    
    /** Ejercicio 6 */
    public boolean hayMayoresQue(E e) {
        // Alternativamente: //return this.recuperarMax().compareTo(e) > 0;
        int pos1aHoja = talla / 2 + 1;
        for (int i = pos1aHoja; i <= talla; i++) {
            if (elArray[i].compareTo(e) > 0) { return true; }
        }
        return false;
    }
    
    /** Ejercicio 7 */
    public boolean estaEn(E e) {
        MonticuloBinario<E> copia = new MonticuloBinario<E>(talla);
        for (int i = 0; i < copia.elArray.length; i++) {
            copia.elArray[i] = elArray[i];
            copia.talla++;
        }
        while (!this.esVacia()) {
            E aux = copia.eliminarMin();
            if (aux == e) { return true; }
            if (aux.compareTo(e) > 0) { return false; }
        }
        return false;
    }
    
    //Versión Recursiva, Solución en Ejercicios
    public boolean estaEnRecursiva(E e) {
        return estaEnRecursiva(e, 1);
    }
    
    protected boolean estaEnRecursiva(E e, int i) {        
        if (i > talla || elArray[i].compareTo(e) < 0) { return false; }
        if (elArray[i].compareTo(e) == 0) { return true; }
        return estaEnRecursiva(e, 2 * i) || estaEnRecursiva(e, 2 * i + 1);
    }
    
    /** Ejercicio 8 */
    public void borrarHojasEnRango(E x, E y) {
        //PASO 1: Recorrer SOLO las hojas del Heap,
        // borrar por niveles las que están en [x, y]
        for (int i = talla; i > talla / 2; i--) {
            if (elArray[i].compareTo(y) <= 0 &&
                elArray[i].compareTo(x) >= 0) {
                if (i < talla) { 
                    elArray[i] = elArray[talla]; 
                    talla--;
                }
            }
        }
        // PASO 2: ¿Es Heap tras borrar las hojas en [x,y]?
        // Si no lo es, "arreglar" elArray
        arreglar();
    }
    
    /** Ejercicio 9 **/
    public E eliminar(int k) {
        E res = elArray[k];
        //PASO 1: borrar k-ésimo nodo del AB Completo "por niveles"
        elArray[k] = elArray[talla--];
        //PASO 2: en un Heap, ¿k es la posición correcta de su
        //último elemento? Si no lo es, encontrarla
        /* COSTE ?(n) */
        arreglar();
        return res;
    }
    
    //Versión más eficiente
    public E eliminar2(int k) {
        E res = elArray[k];
        //PASO 1: borrar k-ésimo nodo del AB Completo "por niveles"
        elArray[k] = elArray[talla--];
        //PASO 2: en un Heap, ¿k es la posición correcta de su
        //último elemento? Si no lo es, encontrarla
        /* COSTE O(log x) y ?(1) */
        // ¿reflotar k-ésimo?
        int pos = k;
        E compK = elArray[k];
        pos = reflotar(compK, pos);
        elArray[pos] = compK;
        //o ¿hundir k-ésimo
        if (pos == k) { hundir(pos); }
        return res;
    }
    
    /** Ejercicio 10 **/
    public int igualesAlMinimo() {
        return igualesAlMinimo(1);
    }
    
    protected int igualesAlMinimo(int i) {
        if (i > talla || elArray[i].compareTo(elArray[1]) > 0) { return 0; }
        return 1 + igualesAlMinimo(2 * i) + igualesAlMinimo(2 * i + 1);
    }
    
    /** Ejercicio 11 **/
    public static <E extends Comparable<E>> boolean esHeap(E[] v) {
        for (int i = v.length - 1; i > 1; i--) {
            if (v[i/2].compareTo(v[i]) > 0) { return false; }
        }
        return true;
    }
    
    /** Ejercicio 12 **/
    public int menoresQue(E e) {
        return menoresQue(e, 1);
    }
    
    protected int menoresQue(E e, int i) {
        if (i > talla || elArray[i].compareTo(e) >= 0) { return 0; }
        return 1 + menoresQue(e, 2 * i) + menoresQue(e, 2 * i + 1);
    }
    
    /** Ejercicio 13 **/
    public E eliminar1aHoja() {
        int pos = talla / 2 + 1; // posición primera hoja
        return eliminar2(pos);
    }
    
    /** Ejercicio 14 **/
    public static <E extends Comparable<E>> void heapSort(E[] v) {
        for (int i = v.length / 2; i >= 0; i--) {
            hundirMax(v, i, v.length);
        }
        // v[0...v.length-1] es un Montículo Maximal
        for (int i = v.length - 1; i > 0; i--) {
            // v[0...i] es un Montículo, zona problema
            //v [i+1...v.length-1] está ordenado
            // de forma creciente con los datos mayores de v
            intercambiar(v, 0, i); // intercambiar v[0] y v[i]
            hundirMax(v, 0, i);
            // i forma parte de la zona ordenada
        }
    }
    
    private static <E extends Comparable<E>> void hundirMax(E[] v,
        int hueco, int fin) {
        int hijo = hueco * 2 + 1;
        E aux = v[hueco];
        boolean esHeap = false;
        while (hijo < fin && !esHeap) {
            if (hijo != fin - 1 && v[hijo + 1].compareTo(v[hijo]) > 0) {
                hijo++;
            }
            if (v[hijo].compareTo(aux) > 0) {
                v[hueco] = v[hijo];
                hueco = hijo;
                hijo = hueco * 2 + 1;
            } else { esHeap = true; }
        }
        v[hueco] = aux;
    }
    
    private static <T extends Comparable <T>> void intercambiar(
        T[] v, int i, int d) 
    {
        T aux = v[i];
        v[i] = v[d];
        v[d] = aux; 
    }
}
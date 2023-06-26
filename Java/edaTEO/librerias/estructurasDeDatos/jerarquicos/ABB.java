package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.lineales.ArrayCola; 
import librerias.excepciones.ElementoNoEncontrado;
import java.util.NoSuchElementException;
import librerias.estructurasDeDatos.modelos.*;
import librerias.estructurasDeDatos.lineales.*;

/** Clase ABB<E> que, en base a la identificacion AB-Nodo, representa un
 *  Arbol Binario mediante un enlace a su Nodo Raiz. Para poder utilizarla 
 *  como Implementacion eficiente de Cola de Prioridad y Map Ordenado, 
 *  sus caracteristicas son las siguientes: 
 *  1.- El tipo de sus Elementos es E extends Comparable<E>
 *  2.- Como unico ATRIBUTO, protected para la Herencia, 
 *      TIENE UN NodoABB<E> raiz
 *      OJO: talla NO es un atributo de la clase, sino de sus Nodos
 *           Precisamente por ello, esta clase implementa un ABB con Rango 
 *  3.- Tiene un unico constructor, el de ABB vacio, sin parametros
 *  4.- Todos sus metodos publicos son muy simples: basicamente, contienen
 *      una invocacion al metodo homonimo recursivo sobre su Nodo Raiz 
 *      (son metodos guia o lanzadera)
 *      Las caracteristicas de cualquiera de estos metodos recursivos son:
 *      4.1. Su modificador de visibilidad es protected y no private; 
 *      4.2. Tiene, al menos, como PARAMETRO FORMAL el Nodo actual sobre  
 *           el que se aplica, PERO NO ES ESTATICO; al invocar su ejecucion  
 *           desde el metodo publico, en su llamada mas alta, el valor del 
 *           Nodo actual es siempre this.raiz
 *      4.3. En general, devuelven el Nodo del ABB resultado de su aplicacion;
 *           Por ejemplo: recuperar(e) devuelve el Nodo de actual que contiene
 *           a e, el elemento a recuperar; insertar(e) devuelve el Nodo Raiz
 *           del ABB que se obtiene al insertar el elemento e en el Nodo actual
 *           
 * @param <E>, tipo de los elementos del ABB, Comparable por definicion 
 *
 **/

public class ABB<E extends Comparable<E>> {

    // para representar al AB subyacente, un ABB TIENE UN
    protected NodoABB<E> raiz;

    // METODOS CON LA MISMA EFICIENCIA QUE EN UN AB *****

    /** crea un ABB vacio, con 0 elementos */
    @SuppressWarnings("unchecked")
    public ABB() { raiz = null; }

    /** comprueba si un ABB esta vacio */
    public boolean esVacio() { return raiz == null; }

    /** devuelve la altura de un ABB, -1 si esta vacio **/
    public int altura() { return altura(raiz); }
    // devuelve la altura del Nodo actual (Recorrido en Post-Orden 
    // con caso base Nodo vacio explicito)
    protected int altura(NodoABB<E> actual) {
        if (actual == null) { return -1; }
        return Math.max(altura(actual.izq), altura(actual.der)) + 1; 
    }

    // SII ABB CON RANGO: METODO MAS EFICIENTE QUE EN UN AB *****

    /** devuelve el tamanyo de un ABB, o numero de Nodos que lo componen */
    public int talla() { 
        // el tamanyo de un ABB es el de su Nodo Raiz
        return talla(raiz); 
    }
    // devuelve el tamanyo del Nodo actual de un ABB 
    protected int talla(NodoABB<E> actual) {
        if (actual == null) { return 0; }
        return actual.talla; 
    }

    // SII ABB EQUILIBRADO: METODOS MAS EFICIENTES QUE EN UN AB  *****

    /** devuelve la primera aparicion en Pre-Orden de e en un ABB, 
     *  null si e no es un Dato del ABB */
    public E recuperar(E e) {
        NodoABB<E> res = recuperar(e, raiz);
        if (res == null) { return null; }
        return res.dato; 
    }
    // devuelve el primer Nodo en Pre-Orden de actual que contiene a e,
    // o null si no existe tal Nodo (caso base Implicito actual == null)
    protected NodoABB<E> recuperar(E e, NodoABB<E> actual) {
        NodoABB<E> res = actual;
        if (actual != null) {
            int resC = actual.dato.compareTo(e);
            if (resC > 0)      { res = recuperar(e, actual.izq); }
            else if (resC < 0) { res = recuperar(e, actual.der); }
            // else NO hacer nada porque res se ha inicializado a actual
        }
        return res; 
    }

    /** actualiza el Dato e de un ABB: si e no esta en el ABB lo inserta  
     *  y si esta lo sobrescribe */
    public void insertar(E e) { 
        raiz = insertar(e, raiz); 
    }
    // devuelve el Nodo que resulta de actualizar el Dato e de actual
    protected NodoABB<E> insertar(E e, NodoABB<E> actual) {
        NodoABB<E> res = actual; 
        if (actual != null) {    
            int resC = actual.dato.compareTo(e);
            if (resC > 0)      { res.izq = insertar(e, actual.izq); }
            else if (resC < 0) { res.der = insertar(e, actual.der); }
            else               { res.dato = e; }
            /** Actualizacion (a-posteriori) de la talla de los Nodos en el 
            //  Camino de Insercion: solo la insercion de un nuevo Nodo en
            //  actual incrementa en 1 su talla, pues si e ya estaba en el 
            //  Nodo actual (resC == 0) su talla no se vera modificada 
            //  (las tallas de sus Hijos seran las mismas que antes de la 
            //  insercion)
             */
            res.talla = 1 + talla(res.izq) + talla(res.der);
        }
        else { res = new NodoABB<E>(e); } /** RECUERDA: al crearlo, res.talla = 1 */
        return res; 
    }

    /** elimina el Dato e de un ABB */
    public void eliminar(E e) { 
        raiz = eliminar(e, raiz); 
    }
    // devuelve el Nodo que resulta de eliminar el Dato e de actual, null
    // si e no es un elemento de actual
    protected NodoABB<E> eliminar(E e, NodoABB<E> actual) {
        NodoABB<E> res = actual; 
        if (actual != null) {
            int resC = actual.dato.compareTo(e);
            if (resC > 0)      { res.izq = eliminar(e, actual.izq); }
            else if (resC < 0) { res.der = eliminar(e, actual.der); }
            else { // si resC == 0 eliminar el Nodo actual, pues contiene a e:
                /** CASO 1: actual es una Hoja o solo tiene un Hijo, por lo 
                //  que se puede devolver directamente como resultado la nueva
                //  Raiz del Nodo actual: respectivamente, un Nodo vacio (null),
                //  de talla 0, o el Nodo Raiz del unico Hijo de actual, de  
                //  talla 1 menos que la de actual 
                //  IMPORTANTE: no hace falta actualizar la talla del nodo  
                //  resultado; tras devolverlo se actualizara la de su padre
                //  MEJOR CASO de eliminar: e es el Dato del Nodo Raiz (actual)
                //  de un ABB que solo tiene un Hijo (de cualquier tamanyo) 
                 */
                if (actual.izq == null) { return actual.der; } 
                else if (actual.der == null) { return actual.izq; }
                else { 
                    /** CASO 2: actual tiene 2 Hijos, por lo que borrarlo equivale
                    //  a cambiar su dato por el minimo de su Hijo Derecho Y eliminar
                    //  dicho minimo
                    //  PEOR CASO de eliminar: e es el Dato del Nodo Raiz (actual) de
                    //  un ABB que tiene 2 Hijos 
                     */
                    res.dato = recuperarMin(actual.der).dato; 
                    /** IMPORTANTE: al eliminarMin se actualiza 
                    //  la talla de actual.der
                     */
                    res.der = eliminarMin(actual.der); 
                } 
            }
            /** Actualizacion (a-posteriori) de la talla de los Nodos en el 
            //  Camino de Borrado: solo el borrado de un Nodo de actual
            //  decrementa en 1 la talla de su Hijo Derecho o Izquierdo
             */
            res.talla = 1 + talla(res.izq) + talla(res.der);
        }
        return res; 
    }

    /** SII !esVacio(): devuelve el minimo de un ABB */
    public E recuperarMin() { 
        return recuperarMin(raiz).dato; 
    }
    // SII actual != null: devuelve el Nodo que contiene el minimo de actual
    // (Recorrido Pre-Orden del Hijo Izquierdo de actual, caso base implicito)
    protected NodoABB<E> recuperarMin(NodoABB<E> actual) {
        NodoABB<E> res = actual;
        if (actual.izq != null)  { res = recuperarMin(actual.izq); }
        return res;
    }

    /** SII !esVacio(): elimina y devuelve el minimo de un ABB */
    public E eliminarMin() {
        E res = recuperarMin();
        raiz = eliminarMin(raiz);
        return res; 
    }
    // SII actual != null: devuelve el Nodo resultante de eliminar el minimo de
    // actual (Recorrido PreOrden del Hijo Izq de actual, caso base implicito)
    protected NodoABB<E> eliminarMin(NodoABB<E> actual) {
        NodoABB<E> res = actual;
        if (actual.izq != null)  {
            res.izq = eliminarMin(actual.izq);
            res.talla--; // o res.talla = 1 + talla(res.izq) + talla(res.der);
        }
        else { res = actual.der; }
        return res;
    }

    /** SII !esVacio(): devuelve el sucesor de e en un ABB, null si no esta */
    public E sucesor(E e) {
        NodoABB<E> res = sucesor(e, raiz);
        if (res == null) { return null; }
        return res.dato; 
    }
    // SII actual != null: devuelve el Nodo de actual que contiene al sucesor  
    // de e, null si no esta
    protected NodoABB<E> sucesor(E e, NodoABB<E> actual) {
        NodoABB<E> res = null; 
        if (actual != null) {    
            int resC = actual.dato.compareTo(e);
            if (resC > 0) { 
                res = sucesor(e, actual.izq); // va a la izq 
                // vuelve de la izq, donde siempre esta el sucesor
                if (res == null) { res = actual; } // actualiza sucesor
            }
            else { 
                res = sucesor(e, actual.der); // va a la der 
                // vuelve de la der, luego el sucesor no varia
            }
        }
        return res; 
    }

    /** SII !esVacio() AND 1 <= k <= talla():  
     *  devuelve el k-esimo minimo de un ABB */
    public E seleccionar(int k) { 
        return seleccionar(k, raiz).dato; 
    }
    // SII actual != null AND 1 <= k <= talla(actual): devuelve el Nodo de  
    // actual que contiene su k-esimo minimo (Busqueda con garantia de exito)
    protected NodoABB<E> seleccionar(int k, NodoABB<E> actual) {
        int tallaI = talla(actual.izq);
        if  (k == tallaI + 1) { return actual; }
        else if (k <= tallaI) { return seleccionar(k, actual.izq); }
        else { return seleccionar(k - tallaI - 1, actual.der); }
    }

    /** metodo recuperar, version iterativa */
    public E recuperarI(E e) {
        NodoABB<E> aux = raiz; 
        while (aux != null) { 
            int resC = aux.dato.compareTo(e);   
            if (resC == 0)     { return aux.dato; }
            else if (resC > 0) { aux = aux.izq;   }
            else               { aux = aux.der;   }
        }
        return null; 
    }

    /** metodo eliminarMin, version iterativa */
    // SII actual != null
    protected NodoABB<E> eliminarMinI(NodoABB<E> actual) {
        NodoABB<E> aux = actual;
        NodoABB<E> padreAux = null;
        while (aux.izq != null) {
            aux.talla--; 
            padreAux = aux; 
            aux = aux.izq;    
        }
        if (padreAux == null) { actual = actual.der; } // minimo en Raiz!!
        else                  { padreAux.izq = aux.der; }
        return actual;
    }

    /** metodo eliminarMin, segunda version iterativa */
    // SII actual != null: devuelve el Nodo actual tras eliminar su minimo,
    // copiando el dato que este contiene en nodoMin (paso por referencia)
    protected NodoABB<E> eliminarMin(NodoABB<E> actual, NodoABB<E> nodoMin) {
        NodoABB<E> aux = actual;
        NodoABB<E> padreAux = null;
        while (aux.izq != null) {
            aux.talla--; 
            padreAux = aux; 
            aux = aux.izq;    
        }
        nodoMin.dato = aux.dato;
        if (padreAux == null) { actual = actual.der; }    
        else                  { padreAux.izq = aux.der; }
        return actual;
    }
    // SII !esVacio() 
    public E eliminarMin2() { 
        NodoABB<E> nodoMin = new NodoABB<E>(null);
        raiz = eliminarMin(raiz, nodoMin);
        return nodoMin.dato;
    }

    /** devuelve un String con los Datos de un ABB en Post-Orden */
    public String toStringPostOrden() {
        StringBuilder res = new StringBuilder().append("[");
        if (raiz != null) { toStringPostOrden(raiz, res); }
        return res.append("]").toString();
    }
    // SII actual != null: actualiza res con los Datos del Nodo actual  
    // en Post-Orden (Recorrido Post-Orden con caso base Nodo Hoja implicito)
    protected void toStringPostOrden(NodoABB<E> actual, StringBuilder res) {
        if (actual.izq != null) {
            toStringPostOrden(actual.izq, res); 
            res.append(", ");
        }
        if (actual.der != null) {
            toStringPostOrden(actual.der, res);
            res.append(", ");
        }
        res.append(actual.dato.toString());
    }

    /** devuelve un String con los Datos de un ABB en Pre-Orden */
    public String toStringPreOrden() { 
        StringBuilder res = new StringBuilder().append("[");
        if (raiz != null) { toStringPreOrden(raiz, res); }
        return res.append("]").toString();
    } 
    // SII actual != null: actualiza res con los Datos del Nodo actual  
    // en Pre-Orden (Recorrido Pre-Orden con caso base Nodo Hoja implicito)
    protected void toStringPreOrden(NodoABB<E> actual, StringBuilder res) {
        res.append(actual.dato.toString()); 
        if (actual.izq != null) {
            res.append(", ");
            toStringPreOrden(actual.izq, res); 
        }
        if (actual.der != null) {
            res.append(", ");
            toStringPreOrden(actual.der, res);
        }
    }

    /** devuelve un String con los Datos de un ABB en In-Orden */
    public String toStringInOrden() {  
        StringBuilder res = new StringBuilder().append("[");
        if (raiz != null) { toStringInOrden(raiz, res); }
        return res.append("]").toString();
    }
    // SII actual != null: actualiza res con los Datos del Nodo actual  
    // en In-Orden (Recorrido In-Orden con caso base Nodo Hoja implicito)
    protected void toStringInOrden(NodoABB<E> actual, StringBuilder res) {
        if (actual.izq != null) {
            toStringInOrden(actual.izq, res); 
            res.append(", ");
        }
        res.append(actual.dato.toString()); 
        if (actual.der != null) {
            res.append(", ");
            toStringInOrden(actual.der, res);
        }
    }

    /** devuelve un String con los Datos de un ABB ordenados Por Niveles */
    //  Recorrido ITERATIVO de la Cola que contiene, en cada iteracion, 
    //  los Nodos del ABB que aun quedan por visitar
    public String toStringPorNiveles() {
        if (this.raiz == null) { return "[]"; }
        StringBuilder res = new StringBuilder().append("[");
        Cola<NodoABB<E>> q = new ArrayCola<NodoABB<E>>();
        q.encolar(this.raiz);
        while (!q.esVacia()) {
            NodoABB<E> actual = q.desencolar();
            res.append(actual.dato.toString());
            // Por eficiencia, cada dato de res, ultimo incluido,  
            // se separa del siguiente con ", "
            res.append(", ");
            //Actualizacion de la Cola de Nodos a visitar
            if (actual.izq != null) { q.encolar(actual.izq); }
            if (actual.der != null) { q.encolar(actual.der); }
        }
        // Por eficiencia, para borrar el ultimo ", " 
        // de res se resta 2 a su longitud actual
        res.setLength(res.length() - 2);
        return res.append("]").toString();
    }  

    /** Ejercicio 5
    /** a) coste antes del cambio: T(n) ?(n) se recorre todas las ramas
     * de todo el arbol en el peor caso 
     * c) coste tras el cambio: T(n) ?(log n)
     * 
     */
    public int enQueNivel(E e) {
        if (raiz == null) { return -1; }
        return enQueNivel(e, raiz, 0);
    }

    // SII actual != null
    protected int enQueNivel(E e, NodoABB<E> actual, int nivelActual) { 
        if (actual == null) { return -1; }
        int comp = actual.dato.compareTo(e);
        if (comp == 0) { return nivelActual; }
        else { 
            if (comp > 0) {
                return enQueNivel(e, actual.izq, nivelActual + 1);  
            }
            else {
                return enQueNivel(e, actual.der, nivelActual + 1);
            }  
        }
    }

    /** Ejercicio 6
    /** a) coste antes del cambio: T(n) ?(n) se recorre todas las ramas
     * de todo el arbol
     * b) coste tras el cambio: T(n) ?(log n)
     * 
     */
    protected int contarMayoresQue(E e, NodoABB<E> actual) {
        int res = 0;
        if (actual != null) {
            int comp = actual.dato.compareTo(e);
            if (comp == 0) { res += talla(actual.der); 
            } else if (comp < 0) {
                res += contarMayoresQue(e, actual.der);
            } else {
                res += 1 + talla(actual.der) + contarMayoresQue(e, actual.izq);
            }
        }
        return res;  
    }
    //Versión mia mala, el caso de derecha se puede resumir con talla(actual.der)
    protected int contarMayoresQueMAL(E e, NodoABB<E> actual) {
        int res = 0;
        if (actual != null) {
            //si es más grande puede estar en los dos hijos
            if (actual.dato.compareTo(e) > 0) {
                res += 1; 
                res += contarMayoresQue(e, actual.izq);
                res += contarMayoresQue(e, actual.der);
            } //si no, vamos por la derecha (potencialmente más grande)
            else {
                res += contarMayoresQue(e, actual.der);
            }
        }
        return res;  
    }

    // Ejercicio 7
    public ListaConPI<E> toListaConPI() {
        ListaConPI<E> res = new LEGListaConPI<E>();
        E min = recuperarMin();
        for (int i = 0; i < talla(); i++) {
            res.insertar(min);
            min = sucesor(min); 
        }
        return res;
    }
    //Versión 2 de soluciones
    public ListaConPI<E> toListaConPIV2() {
        ListaConPI<E> res = new LEGListaConPI<E>();
        if (raiz != null) { toListaConPIV2(raiz, res); }
        return res;
    }
    //SII actual != null: actualiza res con los datos del Nodo actual en In-Orden
    // (recorrido in-orden con caso base Nodo Hoja implícito)
    protected void toListaConPIV2(NodoABB<E> actual, ListaConPI<E> res) {
        if (actual.izq != null) {
            toListaConPIV2(actual.izq, res);
        }
        res.insertar(actual.dato);
        if (actual.der != null) {
            toListaConPIV2(actual.der, res);
        }
    }

    //MI METODO EXTRA 
    /** SII !esVacio(): devuelve el máximo de un ABB */
    public E recuperarMax() { 
        return recuperarMax(raiz).dato; 
    }
    // SII actual != null: devuelve el Nodo que contiene el minimo de actual
    // (Recorrido Pre-Orden del Hijo Izquierdo de actual, caso base implicito)
    protected NodoABB<E> recuperarMax(NodoABB<E> actual) {
        NodoABB<E> res = actual;
        if (actual.der != null)  { res = recuperarMax(actual.der); }
        return res;
    }

    /** SII !esVacio(): devuelve el predecesor de e en un ABB, null si no esta */
    public E predecesor(E e) {
        NodoABB<E> res = predecesor(e, raiz);
        if (res == null) { return null; }
        return res.dato; 
    }
    // SII actual != null: devuelve el Nodo de actual que contiene al predecesor  
    // de e, null si no esta
    protected NodoABB<E> predecesor(E e, NodoABB<E> actual) {
        NodoABB<E> res = null; 
        if (actual != null) {    
            int resC = actual.dato.compareTo(e);
            if (resC < 0) { 
                res = predecesor(e, actual.der); // va a la der 
                // vuelve de la der, donde siempre esta el predecesor
                if (res == null) { res = actual; } // actualiza sucesor
            }
            else { 
                res = predecesor(e, actual.izq); // va a la izq 
                // vuelve de la izq, luego el predecesor no varia 
            }
        }
        return res; 
    }

    // Ejercicio 9
    public String datosEnNivel(int k) {
        StringBuilder res = new StringBuilder().append("[");
        res = datosEnNivel(raiz, k, 0, res);
        res.setLength(res.length() - 1);
        return res.append("]").toString();
    }
    // SII actual != null
    protected StringBuilder datosEnNivel(NodoABB<E> actual, int k, int nivelActual, 
    StringBuilder res) { 
        if (actual != null) {
            if (nivelActual == k) { return res.append(actual.dato + " "); }
            else { 
                datosEnNivel(actual.izq, k, nivelActual + 1, res);  
                datosEnNivel(actual.der, k, nivelActual + 1, res);
            }
        }
        return res;
    }
    //Versión 2 de las soluciones con NivelActual
    public String datosEnNivelV2(int k) {
        return "[" + datosEnNivelV2(raiz, k, 0) + "]";
    }
    // SII 0 <= nivelActual <= k <= altura()
    protected String datosEnNivelV2(NodoABB<E> actual, int k, int nivelActual) { 
        String res = "";
        if (actual != null) {
            if (nivelActual == k) { res = actual.dato.toString(); }
            else { 
                res = datosEnNivelV2(actual.izq, k, nivelActual + 1) + "," + 
                datosEnNivelV2(actual.der, k, nivelActual + 1);
            }
        }
        return res;
    }
    //Versión 3 de las soluciones sin NivelActual
    public String datosEnNivelV3(int k) {
        return "[" + datosEnNivelV3(raiz, k) + "]";
    }
    // SII 0 <= k <= altura
    protected String datosEnNivelV3(NodoABB<E> actual, int k) { 
        String res = "";
        if (actual != null) {
            if (k == 0) { res = actual.dato.toString(); }
            else { 
                res = datosEnNivelV3(actual.izq, k - 1) + "," + 
                datosEnNivelV3(actual.der, k - 1);
            }
        }
        return res;
    }

    //Ejercicio 10
    public int alturaDeEquilibrado() throws NoSuchElementException {
        for (int i = 0; i < altura(); i++) { //El ultimo nivel no hace falta comprobarlo
            if (alturaDeEquilibrado(raiz, 0, i) != Math.pow(2, i)) {
                throw new NoSuchElementException();
            }
        }
        return altura();
    }
    protected int alturaDeEquilibrado(NodoABB<E> actual, int nivelActual,  
    int nivel) {
        if(actual != null) {
            if (nivelActual == nivel) { return 1; }
            else { 
                return 0 + alturaDeEquilibrado(actual.izq, nivelActual + 1, nivel)
                + 0 + alturaDeEquilibrado(actual.der, nivelActual + 1, nivel);  
            }
        }
        return 0;
    }
    //Versión 2 soluciones
    public int alturaDeEquilibradoV2() throws NoSuchElementException {
        return alturaDeEquilibradoV2(raiz);
    }
    protected int alturaDeEquilibradoV2(NodoABB<E> actual) 
        throws NoSuchElementException {
        if (actual == null) { return -1; }
        else {
            int alturaIzq = alturaDeEquilibradoV2(actual.izq);
            int alturaDer = alturaDeEquilibradoV2(actual.der);
            int diff = Math.abs(alturaIzq - alturaDer);
            if (diff > 1) { throw new NoSuchElementException("..."); }
            return 1 + Math.max(alturaIzq, alturaDer);
        }
    }

    //Actividad 11
    public E padreDe(E e) {
        if (raiz != null && raiz.dato.compareTo(e) == 0) return null;
        return padreDe(raiz, e);
    }
    protected E padreDe(NodoABB<E> actual, E e) {
        if (actual != null) {
            if ((actual.izq != null && actual.izq.dato.compareTo(e) == 0) || 
                (actual.der != null && actual.der.dato.compareTo(e) == 0)) { 
                return actual.dato; 
            } else {
                int resC = actual.dato.compareTo(e);
                if (resC > 0) {
                    return padreDe(actual.izq, e);
                } else { //resC < 0
                    return padreDe(actual.der, e);
                }
            }
        }
        return null;
    }
    
    public E hermanoDe(E e) {
        if (raiz != null && raiz.dato.equals(0)) return null;
        return hermanoDe(raiz, e);
    }
    protected E hermanoDe(NodoABB<E> actual, E e) {
        if (actual != null) {
            if ((actual.izq != null && actual.izq.dato.equals(e) && actual.der != null)) {
                return actual.der.dato;
            }
            if ((actual.der != null && actual.der.dato.equals(e) && actual.izq != null)) {
                return actual.izq.dato;
            }
            int resC = actual.dato.compareTo(e);
            if (resC > 0) {
                return hermanoDe(actual.izq, e);
            } else { //resC < 0
                return hermanoDe(actual.der, e);
            }
        }
        return null;
    }

    
    /** Testear métodos */
    public static void main (String[] args) {
        ABB m1 = new ABB(); //ejemplo pag. 13
        m1.insertar(5); m1.insertar(2); m1.insertar(1); m1.insertar(3);
        m1.insertar(9); m1.insertar(7); m1.insertar(10); m1.insertar(8);

        //Actividad 5
        System.out.println("5: " + m1.enQueNivel(5));
        System.out.println("2: " + m1.enQueNivel(2));
        System.out.println("1: " + m1.enQueNivel(1));
        System.out.println("3: " + m1.enQueNivel(3));
        System.out.println("7: " + m1.enQueNivel(7));
        System.out.println("9: " + m1.enQueNivel(9));
        System.out.println("8: " + m1.enQueNivel(8));
        System.out.println("14: " + m1.enQueNivel(14));

        //Actividad 6
        System.out.println("Mayores que 6: " + m1.contarMayoresQue(
                6, m1.raiz));
        System.out.println("Mayores que 9: " + m1.contarMayoresQue(
                9, m1.raiz));
        System.out.println("Mayores que 2: " + m1.contarMayoresQue(
                2, m1.raiz));
        System.out.println("Mayores que 1: " + m1.contarMayoresQue(
                1, m1.raiz));

        //Actividad 7
        System.out.println("ListaConPI: " + m1.toListaConPI());

        //Predecesor
        System.out.println("Predecesor de 7: " + m1.predecesor(7));
        System.out.println("Predecesor de 4: " + m1.predecesor(4));
        System.out.println("Predecesor de 9: " + m1.predecesor(9));
        System.out.println("Predecesor de 11: " + m1.predecesor(11));

        //Actividad 9
        System.out.println("Datos en el nivel 1: " + m1.datosEnNivel(1));
        System.out.println("Datos en el nivel 2: " + m1.datosEnNivel(2));
        System.out.println("Datos en el nivel 3: " + m1.datosEnNivel(3));

        ABB m2 = new ABB(); //es equilibrada
        m2.insertar(7); m2.insertar(2); m2.insertar(9); m2.insertar(1);
        m2.insertar(5);
        ABB m3 = new ABB(); //no es equilibrada
        m3.insertar(7); m3.insertar(2); m3.insertar(9); m3.insertar(1);
        m3.insertar(5); m3.insertar(3);
        //Actividad 10
        System.out.println("m2 equilibrado de altura: " + 
            m2.alturaDeEquilibrado());
        //System.out.println("m3 no equilibrado: " + m3.alturaDeEquilibrado());
        
        //Actividad 11
        System.out.println("Padre de 2: " + m2.padreDe(2));
        System.out.println("Padre de 9: " + m2.padreDe(9));
        System.out.println("Padre de 6: " + m2.padreDe(6));
        System.out.println("Padre de 1: " + m2.padreDe(1));
        System.out.println("Padre de 7: " + m2.padreDe(7));
        
        System.out.println("Hermano de 2: " + m2.hermanoDe(2));
        System.out.println("Hermano de 9: " + m2.hermanoDe(9));
        System.out.println("Hermano de 6: " + m2.hermanoDe(6));
        System.out.println("Hermano de 1: " + m2.hermanoDe(1));
        System.out.println("Hermano de 5: " + m2.hermanoDe(5));
        System.out.println("Hermano de 7: " + m2.hermanoDe(7));
    }
}  
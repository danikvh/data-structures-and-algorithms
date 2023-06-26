package aplicaciones.primitiva;

 
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPIOrdenada;

/** 
 * ApuestaPrimitiva: representa una apuesta aleatoria de La Primitiva, 
 * o combinacion de 6 numeros distintos entre el 1 y el 49 elegidos  
 * de forma aleatoria.
 * 
 * @version Febrero 2019
 */

public class ApuestaPrimitiva {
    
    // Una Primitiva TIENE UNA Lista con PI que almacena
    // una combinacion de 6 numeros de La Primitiva
    private ListaConPI<NumeroPrimitiva> combinacion;
    
    /**
     * Crea una ApuestaPrimitiva, o una combinacion de  
     * seis numeros aleatorios con valores distintos y   
     * en el intervalo [1, 49].
     * 
     * @param ordenada Un boolean que indica si la combinacion,  
     *                 sus 6 numeros, debe estar ordenada Asc.
     *                 (true) o no (false).           
     */
    public ApuestaPrimitiva(boolean ordenada) {      
        /* COMPLETAR */
        NumeroPrimitiva n;
        if (ordenada) { combinacion = new LEGListaConPIOrdenada<NumeroPrimitiva>(); }
        else { combinacion = new LEGListaConPI<NumeroPrimitiva>(); }
        //Gid
        while(combinacion.talla() != 6) {
            n = new NumeroPrimitiva();
            if (posicionDe(n) == -1) { combinacion.insertar(n); }
        }
        //Mi solución
        //for (int i = 0; i < 6; i++) {
        //    do { n = new NumeroPrimitiva();
        //    } while (posicionDe(n) >= 0);
        //    combinacion.insertar(n);
        //}
    }
    
    /**
     * Devuelve la posicion del numero n en una ApuestaPrimitiva, 
     * o -1 si n no forma parte de la combinacion. 
     * IMPORTANTE: se asume que el primer elemento de una combinacion 
     * esta en su posicion 0 y el ultimo en la 5.
     * 
     * @param n un int
     * @return  la posicion de n en una combinacion, un valor int
     *          en el intervalo [0, 5] si n esta en la combinacion      
     *          o -1 en caso contrario
     */
    protected int posicionDe(NumeroPrimitiva n) {
        /* COMPLETAR */
        int contador = 0;
        combinacion.inicio();
        while (!combinacion.esFin()) {
            if (combinacion.recuperar().equals(n)) { return contador; } //Usar equals, no ==
            contador++;
            combinacion.siguiente();
        }
        return -1;
    }
    
    /**
     * Devuelve el String que representa una ApuestaPrimitiva en el formato
     * texto que muestra el siguiente ejemplo: "16, 25, 28, 49, 9, 20"
     * 
     * @return el String con la ApuestaPrimitiva en el formato texto dado. 
     */
    public String toString() {
        /* COMPLETAR */
        String res = "";
        combinacion.inicio();
        for (int i = 0; i < combinacion.talla() - 1; i++) {
            res = res + combinacion.recuperar().toString() + ", ";
            combinacion.siguiente();
        }
        res = res + combinacion.recuperar().toString();
        return res;
    }
}

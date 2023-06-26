package librerias.estructurasDeDatos.lineales;

/**
 * Implementación de LEGListaConPIOrdenada mía
 */
public class LEGListaConPIOrdenada<E extends Comparable<E>> extends LEGListaConPI<E>
{
    public void insertar(E e) {
        this.inicio();
        while (!this.esFin() && this.recuperar().compareTo(e) < 0){
            this.siguiente(); 
        }
        super.insertar(e);
    }
}

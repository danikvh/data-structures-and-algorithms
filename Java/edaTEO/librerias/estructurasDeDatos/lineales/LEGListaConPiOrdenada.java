package librerias.estructurasDeDatos.lineales;


/**
 * Write a description of class LEGListaConPiOrdenada here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LEGListaConPiOrdenada<E extends Comparable<E>> extends LEGListaConPI<E>
{
    public void insertar(E aIns) {
        this.inicio();
        while (!this.esFin() && this.recuperar().compareTo(aIns) < 0){
            this.siguiente(); 
        }
        super.insertar(aIns);
    }
}

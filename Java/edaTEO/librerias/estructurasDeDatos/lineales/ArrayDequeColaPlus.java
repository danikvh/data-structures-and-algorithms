package librerias.estructurasDeDatos.lineales;

import librerias.estructurasDeDatos.modelos.*;
import java.util.*;


public class ArrayDequeColaPlus<E> extends ArrayDequeCola<E> implements ColaPlus<E> {
    public final int talla() {
        return this.size();
    }
}

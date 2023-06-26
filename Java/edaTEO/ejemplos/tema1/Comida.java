package ejemplos.tema1;


/** Ejercicio 16 */
public class Comida implements Comparable<Comida> {
    private double calorias;
    private int minutos;
    public Comida(double c, int m) {
        calorias = c;
        minutos = m;
    }
    
    public int compareTo(Comida otra) {
        if (this.calorias < otra.calorias) { return -1; 
        } else if (otra.calorias < this.calorias) { return 1;
        } else if (this.minutos < otra.minutos) {return -1;
        } else if (otra.minutos < this.minutos) {return 1;
        } else { return 0; }
    }
}

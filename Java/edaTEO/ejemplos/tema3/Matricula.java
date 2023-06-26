package ejemplos.tema3;

 

public class Matricula {   
    private int numeros; 
    private String letras;  

    public Matricula(int n, String l) { numeros = n; letras = l; }   

    public int getNumeros() { return numeros; }   

    public String getLetras() { return letras; }   

    public String toString() { return "Matricula " + numeros + " " + letras; }
    
    // ANYADIR LOS METODOS A PARTIR DE AQUI
    public boolean equals(Object o) {
        return o instanceof Matricula &&
            this.getNumeros() == ((Matricula) o).getNumeros() &&
            this.getLetras().equals(((Matricula) o).getLetras());
    }
    
    public int hashCode() {
        return (letras + numeros).hashCode();
    }
}
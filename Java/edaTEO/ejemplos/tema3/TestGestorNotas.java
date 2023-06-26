package ejemplos.tema3;

 
import librerias.excepciones.*;

public class TestGestorNotas {
    public static void main(String[] args) throws Exception {
        GestorNotas g = new GestorNotas(30);
        // COMPLETAR
        Estudiante dani = new Estudiante("Dani","Ciencias");
        Estudiante adri = new Estudiante("Adriana","Historia");
        
        g.guardarNota("Dani","Ciencias", 8);
        g.guardarNota("Adriana","Historia", 10);
        g.guardarNota("Dani","Ciencias", 6);
        
        Thread.sleep(5000);
        
        // COMPLETAR
        System.out.println(g.consultarNota("Dani", "Ciencias"));
        System.out.println(g.consultarNota("Adriana","Historia"));
    }
}

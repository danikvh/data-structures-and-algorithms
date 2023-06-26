package librerias.excepciones;

 

public class NotaNoEncontrada extends Exception {
    public NotaNoEncontrada(String mensaje) {
        super(mensaje);
    }    
    public NotaNoEncontrada() { 
        super();
    }
}
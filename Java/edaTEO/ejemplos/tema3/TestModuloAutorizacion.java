package ejemplos.tema3;

 
import librerias.excepciones.*;

public class TestModuloAutorizacion {
    public static void main(String[] args) throws Exception {
        ModuloAutorizacion mA = new ModuloAutorizacion(10);
        // COMPLETAR
        mA.registrarUsuario("Daniel", "der");
        mA.registrarUsuario("Elbb", "culodegamba");
        
        Thread.sleep(5000);
        
        // COMPLETAR
        System.out.println(mA.estaAutorizado("Daniel", "der"));
        System.out.println(mA.estaAutorizado("Elbb", "culodegamba"));
        System.out.println(mA.estaAutorizado("Antonio", "pepe"));
        
        // COMPLETAR
        System.out.println(mA.fechaAcceso("Daniel", "der"));
        System.out.println(mA.fechaAcceso("Elbb", "culodegamba"));
        System.out.println(mA.fechaAcceso("Antonio", "pepe"));
    }
}
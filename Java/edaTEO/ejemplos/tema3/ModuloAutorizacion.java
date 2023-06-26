package ejemplos.tema3;

 

import librerias.estructurasDeDatos.modelos.*; 
import librerias.estructurasDeDatos.deDispersion.*;
import librerias.excepciones.*;
import java.util.Date;

public class ModuloAutorizacion {
    private Map<Usuario, Date> m;
    
    public ModuloAutorizacion(int n) {
        // COMPLETAR
        m = new TablaHash<Usuario, Date>(n);
    }
    
    public Date fechaAcceso(String nombre, String pwd) {
        // COMPLETAR 
        ListaConPI<Usuario> claves = m.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()) {
            Usuario userbase = claves.recuperar();
            String nombase = userbase.getNombre();
            if (nombre == nombase) { return m.recuperar(userbase); }
        }
        return null;
    }
    
    public void registrarUsuario(String nombre, String pwd) 
        throws UsuarioExistente {
        // COMPLETAR
        ListaConPI<Usuario> claves = m.claves();
        for (claves.inicio(); !claves.esFin() && claves.recuperar().getNombre() != nombre; 
            claves.siguiente());
        if (!claves.esFin()) {
            throw new UsuarioExistente();
        }
        Date ahora = new Date();
        m.insertar(new Usuario(nombre,pwd), ahora);       
    }
    
    public boolean estaAutorizado(String nombre, String pwd) {
        // COMPLETAR 
        ListaConPI<Usuario> claves = m.claves();
        for (claves.inicio(); !claves.esFin(); claves.siguiente()) {
            Usuario userbase = claves.recuperar();
            String nombase = userbase.getNombre();
            if (nombre == nombase) { return true; }
        }
        return false;
    }
}
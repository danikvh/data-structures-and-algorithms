package ejemplos.tema3;

 

public class Usuario {
    private String nombre;
    private String password;

    public Usuario(String nombre, String password) {
        this.nombre = nombre;
        this.password = password;
    }
    
    // ANYADIR LOS METODOS A PARTIR DE AQUI
    public String getNombre() {
        return this.nombre;
    }
    
    public void setPassword(String pass) {
        this.password = pass;
    }
}
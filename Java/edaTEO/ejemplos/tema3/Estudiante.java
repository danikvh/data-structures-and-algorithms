package ejemplos.tema3;

public class Estudiante {
    private String alumno;
    private String asignatura;

    public Estudiante(String alumno, String asignatura) {
        this.alumno = alumno;
        this.asignatura = asignatura;
    }
    
    public String getAlum() {
        return this.alumno;
    }
    
    public String getAsig() {
        return this.asignatura;
    }
}

package ejemplos.tema3;

import librerias.estructurasDeDatos.modelos.*; 
import librerias.estructurasDeDatos.deDispersion.*;
import librerias.excepciones.*;

/**
 * Write a description of class GestorNotas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GestorNotas
{   
    private Map<Estudiante, Double> m;
    
    public GestorNotas(int n) {
        m = new TablaHash<Estudiante, Double>(n);
    }
    
    public void guardarNota(String alumno, String asignatura, double nota) {
        ListaConPI<Estudiante> claves = m.claves();
        for (claves.inicio(); !claves.esFin() && !(claves.recuperar().getAlum() == alumno && 
        claves.recuperar().getAsig() == asignatura); claves.siguiente());
        if (!claves.esFin()) {
            Estudiante aux = claves.recuperar();
            m.insertar(aux, nota);
        }
        m.insertar(new Estudiante(alumno, asignatura), nota);
    }
    
    public double consultarNota(String alumno, String asignatura) throws 
        NotaNoEncontrada {
        ListaConPI<Estudiante> claves = m.claves();
        for (claves.inicio(); !claves.esFin() && !(claves.recuperar().getAlum() == alumno && 
        claves.recuperar().getAsig() == asignatura); claves.siguiente());
        if (claves.esFin()) { throw new NotaNoEncontrada(); }
        return m.recuperar(claves.recuperar());
    }
    
    //No tiene en cuenta las asignaturas, supone Estudiante como solo nombre
    public static Map<Estudiante, Double> obtenerAprobados(Map<Estudiante, Double> m){
        Map<Estudiante, Double> aprobados = new TablaHash<Estudiante, Double>(m.talla());
        ListaConPI<Estudiante> l = m.claves();
        for (l.inicio(); !l.esFin(); l.siguiente()) {
            Estudiante estudiante = l.recuperar();
            Double nota = m.recuperar(estudiante);
            if (nota >= 5.0) {
                aprobados.insertar(estudiante, nota);
                m.eliminar(estudiante);
            }
        }
        return aprobados;
    }
}

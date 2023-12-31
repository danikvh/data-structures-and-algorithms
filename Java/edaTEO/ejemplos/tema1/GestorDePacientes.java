package ejemplos.tema1;

import librerias.estructurasDeDatos.lineales.*;
import librerias.estructurasDeDatos.modelos.*; //importar Cola y ColaPlus

/** Ejercicio 4 y 7 */
public class GestorDePacientes {
    private ColaPlus<Paciente> q;  //Cola -> ColaPlus para talla
    private double horaCita;
    private static final int MAXIMO_DIARIO_PACIENTES = 40;
    private static final double HORA_INICIO_CONSULTA = 9.00;
    private static final double TIEMPO_MEDIO_VISITA = 0.15;
    
    public GestorDePacientes() {
        q = new ArrayColaPlus<Paciente>(); //ArrayCola -> ArrayColaPlus para talla
        horaCita = HORA_INICIO_CONSULTA;
    }

    public String darCita(Paciente x) {
        String res = "Espere un momento; consulto si le pueden atender magnana ... ";
        boolean aceptado = (q.talla() <= MAXIMO_DIARIO_PACIENTES); //q.talla -> q.talla() para llamar al m�todo
        if (!aceptado) res += "\nLo siento. Magnana no podemos atenderle";
        else{
            q.encolar(x); 
            res += "\nConfirmado, le esperamos magnana a las " 
                + String.format("%2.2f", horaCita);
            horaCita += TIEMPO_MEDIO_VISITA;
            if (horaCita - Math.round(horaCita) < 0.0) 
                horaCita = Math.round(horaCita);
        }
        return res;
    }

    public String toString() {
        return "Historiales de sus " + q.talla() 
            + " Pacientes de magnana en orden de visita\n" + q;
    }                    
}
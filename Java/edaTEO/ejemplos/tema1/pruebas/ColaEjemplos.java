package ejemplos.tema1.pruebas;

import  librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.lineales.LEGCola;
import librerias.estructurasDeDatos.lineales.LEGColaOrdenada;
import librerias.estructurasDeDatos.lineales.ArrayDequeCola;

/**
 * Ejercicio 1
 */
public class ColaEjemplos
{
    public static void main(String[] args){
        LEGCola cola1 = new LEGCola();
        System.out.println("Cola 1: " + cola1);
        cola1.encolar(1);
        System.out.println("Cola 1, encolando 1: " + cola1);
        cola1.encolar(2);
        System.out.println("Cola 1, encolando 1 y 2: " + cola1);
        cola1.desencolar();
        System.out.println("Cola 1, desencola 1: " + cola1);
        cola1.desencolar();
        System.out.println("Cola 1, desencola 2: " + cola1);
        cola1.desencolar();
        System.out.println("Cola 1, desencola null: " + cola1);
        System.out.println("Primero Cola 1: " + cola1.primero());
        cola1.encolar(1);
        System.out.println("Cola 1, encolando 1: " + cola1);
        System.out.println("Primero Cola 1: " + cola1.primero());
        
        ArrayDequeCola<Integer> cola2 = new ArrayDequeCola<>();
        System.out.println("Cola 2: " + cola2);
        cola2.encolar(1);
        System.out.println("Cola 2, encolando 1: " + cola2);
        cola2.encolar(2);
        System.out.println("Cola 2, encolando 1 y 2: " + cola2);
        System.out.println(cola2.desencolar());
        System.out.println("Cola 2, desencola 1: " + cola2);
        cola2.desencolar();
        System.out.println("Cola 2, desencola 2: " + cola2);
        cola2.desencolar();
        System.out.println("Cola 2, desencola null: " + cola2);
        System.out.println("Primero Cola 2: " + cola2.primero());
        cola2.encolar(1);
        System.out.println("Cola 2, encolando 1: " + cola2);
        System.out.println("Primero Cola 2: " + cola2.primero());
        
        
        /** Ejercicios LEGColaOrdenada */
        LEGColaOrdenada cola3 = new LEGColaOrdenada();
        cola3.encolar(1);
        System.out.println("Cola 3, encolando 1: " + cola3);
        cola3.encolar(2);
        System.out.println("Cola 3, encolando 2: " + cola3);
        cola3.encolar(7);
        System.out.println("Cola 3, encolando 7: " + cola3);
        cola3.encolar(4);
        System.out.println("Cola 3, encolando 4: " + cola3);
        cola3.encolar(3);
        System.out.println("Cola 3, encolando 3: " + cola3);
        cola3.encolar(9);
        System.out.println("Cola 3, encolando 9: " + cola3);
        cola3.encolar(7);
        System.out.println("Cola 3, encolando 7: " + cola3);
    }
}

package ejemplos.tema1.pruebas;

import  librerias.estructurasDeDatos.modelos.Pila;
import librerias.estructurasDeDatos.lineales.ArrayPila;
import librerias.estructurasDeDatos.lineales.LEGPila;

/**
 * Ejercicio 1
 */
public class PilaEjemplos
{
    public static void main(String[] args){
        ArrayPila pila1 = new ArrayPila();
        System.out.println("Pila 1: " + pila1);
        pila1.apilar(1);
        System.out.println("Pila 1, apilando 1: " + pila1);
        pila1.apilar(2);
        System.out.println("Pila 1, apilando 1 y 2: " + pila1);
        pila1.desapilar();
        System.out.println("Pila 1, desapila 2: " + pila1);
        pila1.desapilar();
        System.out.println("Pila 1, desapila 1: " + pila1);
        pila1.desapilar();
        System.out.println("Pila 1, desapila null: " + pila1);
        System.out.println("Tope Pila 1: " + pila1.tope());
        pila1.apilar(1);
        System.out.println("Pila 1, apilando 1: " + pila1);
        System.out.println("Tope Pila 1: " + pila1.tope());
        
        LEGPila pila2 = new LEGPila();
        System.out.println("Pila 2: " + pila2);
        pila2.apilar(1);
        System.out.println("Pila 2, apilando 1: " + pila2);
        pila2.apilar(2);
        System.out.println("Pila 2, apilando 1 y 2: " + pila2);
        pila2.desapilar();
        System.out.println("Pila 2, desapila 2: " + pila2);
        pila2.desapilar();
        System.out.println("Pila 2, desapila 1: " + pila2);
        pila2.desapilar();
        System.out.println("Pila 2, desapila null: " + pila2);
        System.out.println("Tope Pila 2: " + pila2.tope());
        pila2.apilar(1);
        System.out.println("Pila 2, apilando 1: " + pila2);
        System.out.println("Tope Pila 2: " + pila2.tope());
    }
}

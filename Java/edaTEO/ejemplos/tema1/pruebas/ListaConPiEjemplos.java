package ejemplos.tema1.pruebas;

import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.LEGListaConPIPlus;
import librerias.estructurasDeDatos.lineales.LEGColaOrdenada;
import librerias.estructurasDeDatos.lineales.LEGListaConPI1920;
import librerias.estructurasDeDatos.lineales.LEGCola;

/**
 * Write a description of class ListaConPiEjemplos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ListaConPiEjemplos
{    
    public static void main(String[] args){
        /** Página 41, Cuestiones 1 y 2 */
        LEGListaConPI lista1 = new LEGListaConPI();
        lista1.insertar(1);
        lista1.insertar(2);
        lista1.insertar(3);
        lista1.insertar(4);
        lista1.insertar(5);
        lista1.insertar(6);
        System.out.println("Lista sin modificar: " + lista1);
        
        //lista1.eliminar(); //da error porque el Punto de Interés apunta al final (fuera de la lista)
        
        System.out.println("Lista al final?: " + lista1.esFin());
        
        lista1.inicio();
        
        
        /** Página 42, Cuestión 3 */
        while ((int)lista1.recuperar() != 5) { lista1.siguiente(); }
        
        lista1.eliminar(); lista1.insertar(new Integer(4)); 
        System.out.println(lista1);
        
        LEGListaConPI lista2 = new LEGListaConPI();
        lista1.insertar(1);
        lista2.insertar(new Integer(4));
        lista2.insertar(new Integer(6));
        System.out.println(lista2);
        lista2.inicio();
        lista2.eliminar(); 
        System.out.println(lista2);
        
        
        /** Ejercicios ListaConPiPlus */
        LEGListaConPIPlus lista3 = new LEGListaConPIPlus();
        lista3.insertar(1);
        lista3.insertar(2);
        lista3.insertar(2);
        lista3.insertar(5);
        lista3.insertar(3);
        lista3.insertar(4);
        lista3.insertar(5);
        lista3.insertar(6);
        lista3.insertar(4);
        lista3.insertar(5);
        System.out.println("Lista sin modificar: " + lista3);

        System.out.println("Lista3 contiene 2: " + lista3.contiene(2));
        System.out.println("Lista3 contiene 8: " + lista3.contiene(8));
        
        lista3.eliminarPrimero(2); lista3.eliminarPrimero(2);
        System.out.println("Lista tras eliminar los 2 doses: " + lista3);
        
        lista3.eliminarUltimo(4);
        System.out.println("Lista tras eliminar ultimo 4: " + lista3);
        
        lista3.eliminarTodos(5);
        System.out.println("Lista tras eliminar todos los 5: " + lista3);
        
        LEGListaConPIPlus lista4 = new LEGListaConPIPlus();
        lista4.insertar(1);
        lista4.insertar(2);
        lista4.insertar(2);
        lista3.concatenar(lista4);
        System.out.println("Lista 4: " + lista4);
        System.out.println("Lista 3 tras concatenar con lista 4: " + lista3);
        
        lista3.moverADerecha();
        System.out.println("Lista 3 tras mover a la derecha: " + lista3);
        lista3.moverAIzquierda();
        System.out.println("Lista 3 tras mover a la izquierda: " + lista3);
        
        
        /** Ejercicio 19 y 20 */
        LEGListaConPI1920 lista5 = new LEGListaConPI1920();
        lista5.insertar(1);
        lista5.insertar(2);
        lista5.insertar(3);
        lista5.insertar(4);
        lista5.insertar(5);
        lista5.insertar(6);
        System.out.println("Lista 5 sin modificar: " + lista5);
        
        LEGListaConPI1920 lista6 = new LEGListaConPI1920();
        lista6.insertar(1);
        lista6.insertar(5);
        lista6.insertar(12);
        lista6.insertar(25);
        lista6.insertar(48);
        System.out.println("Lista 6 sin modificar: " + lista6);
        
        LEGCola res = lista5.eliminaComun(lista6);
        System.out.println("Cola elementos en común: " + res);
        System.out.println("Lista 5 sin elementos en común: " + lista5);
        System.out.println("Lista 6 sin elementos en común: " + lista6);
        
        
        LEGListaConPI1920 lista7 = new LEGListaConPI1920();
        lista7.insertar(1);
        lista7.insertar(2);
        lista7.insertar(3);
        lista7.insertar(4);
        LEGListaConPI lista8 = new LEGListaConPI();
        lista8.insertar(4);
        lista8.insertar(3);
        lista8.insertar(2);
        lista8.insertar(1);
        LEGListaConPI1920 lista9 = new LEGListaConPI1920();
        lista9.insertar(4);
        lista9.insertar(3);
        lista9.insertar(5245);
        lista9.insertar(1);
        System.out.println("Lista 7 sin modificar: " + lista7);
        System.out.println("Lista 8 sin modificar: " + lista8);
        System.out.println("Lista 9 sin modificar: " + lista9);
        System.out.println("Lista 7 y 8 capicua: " + lista7.capicua(lista8));
        System.out.println("Lista 7 y 8 capicua: " + lista7.capicua(lista9));
    }
}

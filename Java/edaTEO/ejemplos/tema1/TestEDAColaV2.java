package ejemplos.tema1;
import librerias.estructurasDeDatos.modelos.ColaPlus;
import librerias.estructurasDeDatos.lineales.ArrayColaPlus; //importar Cola y ArrayCola

/** Ejercicio 7 Versión mejor, Plus */
public class TestEDAColaV2 {
  public static void main(String[] args) {      
      ColaPlus<Integer> q = new ArrayColaPlus<Integer>(); //especificar tipo <Integer>
      System.out.println("Creada una Cola con " + q.talla() //talla es protected
          +" Integer, q = " + q.toString());
      q.encolar(new Integer(10)); 
      q.encolar(new Integer(20)); 
      q.encolar(new Integer(30));
      System.out.println("La Cola de Integer actual es q = " + q.toString());
      System.out.println("Usando otros metodos para mostrar sus Datos el resultado es ...");
      String datosQ = "";
      while (!q.esVacia()) {
          Integer primero = q.primero();
          if (primero.equals(q.desencolar())) datosQ += primero + " "; 
          else datosQ += "ERROR ";
      }
      System.out.println(" el mismo, " + datosQ 
          + ", PERO q se vacia, q = " + q.toString());
  }
}

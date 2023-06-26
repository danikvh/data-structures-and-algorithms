package ejemplos.tema1;
import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.lineales.ArrayCola; //importar Cola y ArrayCola

/** Ejercicio 3 Versión chapuza, con int TallaQ */
public class TestEDACola {
  public static void main(String[] args) {      
      Cola<Integer> q = new ArrayCola<Integer>(); //especificar tipo <Integer>
      int TallaQ = 0;
      System.out.println("Creada una Cola con " + /*q.talla*///talla es protected
          TallaQ + " Integer, q = " + q.toString());
      q.encolar(new Integer(10)); TallaQ++; 
      q.encolar(new Integer(20)); TallaQ++; 
      q.encolar(new Integer(30)); TallaQ++; 
      System.out.println("La Cola de Integer actual es q = " + q.toString());
      System.out.println("Usando otros metodos para mostrar sus Datos el resultado es ...");
      String datosQ = "";
      while (!q.esVacia()) {
          Integer primero = q.primero();
          if (primero.equals(q.desencolar())) datosQ += primero + " "; 
          else datosQ += "ERROR ";
          TallaQ--;
      }
      System.out.println(" el mismo, " + datosQ 
          + ", PERO q se vacia, q = " + q.toString());
  }
}
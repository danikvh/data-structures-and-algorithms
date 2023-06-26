package ejemplos.tema1.gestionFigurasV0;

import ejemplos.tema1.lasFigurasV0.*; // para usar la clase Circulo
import java.util.*; // para usar la clase Scanner
import java.awt.geom.*; // para usar la clase Point2D.Double

public class TestCirculo{
    public static void main(String args[]){
        Circulo c1 = new Circulo(); System.out.println(c1.toString());
        Circulo c2 = new Circulo(4.0, "Rojo", new Point2D.Double(0.0,3.0));
        System.out.println(c2.toString());
        Scanner teclado = new Scanner(System.in).useLocale(new Locale("es", "US"));
        Circulo c3 = Circulo.leer(teclado); System.out.println(c3.toString());
        if ( c3.equals(c2) ) c3 = null; else c2 = c3;
        double radioC1 = c1.getRadio(); if ( radioC1 > 3.0 ) c1.setRadio(3.0);
        System.out.println(" Área de c1 = "+String.format(new Locale("US"), "%.2f", c1.area()));
        if ( c1.equals(new Circulo()) ) c2 = c1;
    }
}

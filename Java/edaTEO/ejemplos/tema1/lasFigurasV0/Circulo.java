package ejemplos.tema1.lasFigurasV0;

import java.util.*; 
import java.awt.geom.*;

public class Circulo{
    private double radio; private String color; private Point2D.Double centro;
    private static final double RADIO_POR_DEFECTO = 3.0;
    private static final String COLOR_POR_DEFECTO = "rojo";
    private static final Point2D.Double CENTRO_POR_DEFECTO = new Point2D.Double();
    
    /** crea un C�rculo de radio r, color c y centro en el Punto p*/
    public Circulo(double radio, String color, Point2D.Double centro){
        this.radio = radio; this.color = color; this.centro = centro;
    }

    /** crea un C�rculo est�ndar de radio 3.0, color rojo y centro en el origen*/
    public Circulo(){ this(RADIO_POR_DEFECTO, COLOR_POR_DEFECTO, CENTRO_POR_DEFECTO);}

    /** consulta el radio de un C�rculo*/
    public double getRadio(){return this.radio;}

    /** consulta el color de un C�rculo*/
    public String getColor(){return this.color;}

    /** consulta el Centro de un C�rculo*/
    public Point2D.Double getCentro(){return this.centro;}

    /** actualiza el radio de un C�rculo a nuevoRadio*/
    public void setRadio(double nuevoRadio){ this.radio = nuevoRadio;}

    /** actualiza el color de un C�rculo a nuevoColor*/
    public void setColor(String nuevoColor){ this.color = nuevoColor;}

    /** actualiza el Centro de un C�rculo a nuevoCentro*/
    public void setCentro(Point2D.Double nuevoCentro){this.centro = nuevoCentro;}

    /** calcula el  �rea de un C�rculo*/
    public double area(){ return Math.PI * radio * radio;}

    /** calcula el per�metro de un C�rculo*/
    public double perimetro(){return 2 * Math.PI * radio;}

    /** obtiene el String que representa a un C�rculo*/
    public String toString(){
        String res = "C�rculo de radio "+String.format(new Locale("US"),"%.2f", this.radio);
        return res+", color "+color+" y centro "+centro.toString();
    }

    /** indica si un C�rculo es igual a x, i.e. si tiene el mismo radio y color que x*/
    public boolean equals(Object x){
        return (this.radio == ((Circulo)x).radio && this.color.equals(((Circulo)x).color));
    }

    /** obtiene un Circulo leyendo de teclado los valores de sus componentes*/
    public static Circulo leer(Scanner teclado){
        Circulo res = new Circulo();
        System.out.println("********Introduzca las componentes del C�rculo*********\n");
        System.out.println("�radio?"); res.radio = teclado.nextDouble();
        System.out.println("�color?"); res.color = teclado.next();
        System.out.println("�coordenada x del centro?"); double x = teclado.nextDouble();
        System.out.println("�coordenada y del centro?"); double y = teclado.nextDouble();
        res.centro = new Point2D.Double(x,y);
        return res;
    }
}

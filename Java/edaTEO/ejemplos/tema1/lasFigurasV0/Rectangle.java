package ejemplos.tema1.lasFigurasV0;

import java.util.*; 
import java.awt.geom.*;

public class Rectangle{
    private double base, altura; private String color; private Point2D.Double centro;
    private static final double BASE_POR_DEFECTO = 3.0;
    private static final double ALTURA_POR_DEFECTO = 2.0;
    private static final String COLOR_POR_DEFECTO = "rojo";
    private static final Point2D.Double CENTRO_POR_DEFECTO = new Point2D.Double();
    
    /** crea un Rectángulo de base b, altura h, color c y centro en el Punto p*/
    public Rectangle(double base, double altura, String color, Point2D.Double centro){
        this.base = base; this.altura = altura; this.color = color; this.centro = centro;
    }

    /** crea un Rectángulo estándar de base 3.0, altura 2.0, color rojo y centro en el origen*/
    public Rectangle(){ this(BASE_POR_DEFECTO, ALTURA_POR_DEFECTO, COLOR_POR_DEFECTO, CENTRO_POR_DEFECTO);}

    /** consulta la base de un Rectángulo*/
    public double getBase(){return this.base;}

    /** consulta la altura de un Rectángulo*/
    public double getAltura(){return this.altura;}
    
    /** consulta el color de un Rectángulo*/
    public String getColor(){return this.color;}

    /** consulta el Centro de un Rectángulo*/
    public Point2D.Double getCentro(){return this.centro;}

    /** actualiza la base de un Rectángulo a nuevoBase*/
    public void setBase(double nuevoBase){ this.base = nuevoBase;}

    /** actualiza la base de un Rectángulo a nuevoBase*/
    public void setAltura(double nuevoAltura){ this.altura = nuevoAltura;}
    
    /** actualiza el color de un Rectángulo a nuevoColor*/
    public void setColor(String nuevoColor){ this.color = nuevoColor;}

    /** actualiza el Centro de un Rectángulo a nuevoCentro*/
    public void setCentro(Point2D.Double nuevoCentro){this.centro = nuevoCentro;}

    /** calcula el  área de un Rectángulo*/
    public double area(){ return base * altura;}

    /** calcula el perímetro de un Rectángulo*/
    public double perimetro(){return 2 * (base + altura);}

    /** obtiene el String que representa a un Rectángulo*/
    public String toString(){
        String res = "Rectángulo de base "+String.format(new Locale("US"),"%.2f", this.base);
        return res+", color "+color+" y centro "+centro.toString();
    }

    /** indica si un Rectángulo es igual a x, i.e. si tiene el mismo base y color que x*/
    public boolean equals(Object x){
        return (this.base == ((Rectangle)x).base && this.color.equals(((Rectangle)x).color));
    }

    /** obtiene un Rectangle leyendo de teclado los valores de sus componentes*/
    public static Rectangle leer(Scanner teclado){
        Rectangle res = new Rectangle();
        System.out.println("********Introduzca las componentes del Rectángulo*********\n");
        System.out.println("¿base?"); res.base = teclado.nextDouble();
        System.out.println("¿color?"); res.color = teclado.next();
        System.out.println("¿coordenada x del centro?"); double x = teclado.nextDouble();
        System.out.println("¿coordenada y del centro?"); double y = teclado.nextDouble();
        res.centro = new Point2D.Double(x,y);
        return res;
    }
}

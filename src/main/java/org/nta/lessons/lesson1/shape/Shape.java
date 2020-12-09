package org.nta.lessons.lesson1.shape;

public abstract class Shape {
  public abstract double area();

  public static void main(String[] args) {
    Shape triangle = new Triangle(2,3,4);
    System.out.println(triangle.area());
    Shape circle = new Circle(2);
    System.out.println(circle.area());
    Shape rect = new Rect(2,3);
    System.out.println(rect.area());
    Shape square = new Square(2);
    System.out.println(square.area());
  }
}

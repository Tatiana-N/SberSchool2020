package org.nta.lessons.lesson1.shape;

public class Circle extends Shape {
  private int radius;

  public Circle(int radius) {
    this.radius = radius;
  }

  @Override
  public double area() {
    return Math.PI * radius * radius;
  }
}

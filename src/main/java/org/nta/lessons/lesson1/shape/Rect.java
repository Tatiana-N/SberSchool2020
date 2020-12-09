package org.nta.lessons.lesson1.shape;

public class Rect extends Shape {
  private int width;
  private int high;

  public Rect(int width, int high) {
    this.width = width;
    this.high = high;
  }

  @Override
  public double area() {
    return width * high;
  }
}

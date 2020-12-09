package org.nta.lessons.lesson1.shape;

public class Triangle extends Shape {
  private double firstSide;
  private double secondSide;
  private double thirdSide;

  public Triangle(int firstSide, int secondSide, int thirdSide) {
    this.firstSide = firstSide;
    this.secondSide = secondSide;
    this.thirdSide = thirdSide;
  }

  @Override
  public double area() {
    double p = (firstSide + secondSide + thirdSide)/2;
    return Math.sqrt(p * (p - firstSide) * (p - secondSide)* (p - thirdSide));
  }
}

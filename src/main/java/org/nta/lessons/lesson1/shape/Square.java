package org.nta.lessons.lesson1.shape;

public class Square extends Rect {
  private int side;

  public Square( int side) {
    super(side, side);
    this.side = side;
  }
}

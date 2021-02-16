package org.nta.lessons.lesson15.refactoring;

public class Position {
  private int x;
  private int y;

  public Position(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Position changeX(int x){
   setX(getX()+x);
    return this;
  }
  public Position changeY(int y){
    setY(getY()+y);
    return this;
  }
}

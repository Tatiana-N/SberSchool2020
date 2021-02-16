package org.nta.lessons.lesson15.refactoring;

public interface Tractor {
  void move(Command command);

  Position getField();

  Position getPosition();

  Orientation getOrientation();

  void setOrientation(Orientation orientation);
}

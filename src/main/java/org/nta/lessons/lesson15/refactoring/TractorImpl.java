package org.nta.lessons.lesson15.refactoring;

public class TractorImpl implements Tractor {

  Position field = new Position(5, 5);
  Orientation orientation = Orientation.NORTH;
  Position position = new Position(0, 0);

  public void move(Command command) {
    command.execute();
  }

  @Override
  public Position getField() {
    return field;
  }


  @Override
  public Position getPosition() {
    return position;
  }

  @Override
  public Orientation getOrientation() {
    return orientation;
  }

  @Override
  public void setOrientation( Orientation orientation) {
    this.orientation = orientation;
  }

}

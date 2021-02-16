package org.nta.lessons.lesson15.refactoring;

public class MoveCommand implements Command {
  Tractor tractor;

  public MoveCommand(Tractor tractor) {
    this.tractor = tractor;
  }

  @Override
  public void execute() {
    tractor.getOrientation().move(tractor.getPosition());
    if (tractor.getPosition().getX() > tractor.getField().getX() || tractor.getPosition().getY() > tractor.getField().getY()) {
      throw new TractorInDitchException();
    }
  }
}

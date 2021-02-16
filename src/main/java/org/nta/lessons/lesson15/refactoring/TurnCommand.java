package org.nta.lessons.lesson15.refactoring;

public class TurnCommand implements Command {
  Tractor tractor;

  public TurnCommand(Tractor tractor) {
    this.tractor = tractor;
  }

  @Override
  public void execute() {
    tractor.setOrientation(tractor.getOrientation().turn());
  }
}

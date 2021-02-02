package org.nta.lessons.lesson12;

public class FailedMethodCall extends RuntimeException {
  public FailedMethodCall() {
    super("Метод завершен с ошибкой");
  }
}

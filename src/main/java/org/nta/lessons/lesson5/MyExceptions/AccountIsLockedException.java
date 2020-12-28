package org.nta.lessons.lesson5.MyExceptions;

public class AccountIsLockedException extends Exception {
  public AccountIsLockedException(String message) {
    super(message);
  }
}

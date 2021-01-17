package org.nta.lessons.lesson5.MyExceptions;

public class IncorrectAmountOfMoney extends Exception{
  public IncorrectAmountOfMoney(String message) {
    super(message + "\n Введите сумму кратную 100");
  }
}

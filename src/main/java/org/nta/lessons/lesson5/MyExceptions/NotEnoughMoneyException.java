package org.nta.lessons.lesson5.MyExceptions;

public class NotEnoughMoneyException extends Exception{
  public NotEnoughMoneyException(String message) {
    super(message + " \n Введите сумму менее или равную остатку денег на Вашем счёте");
  }
}

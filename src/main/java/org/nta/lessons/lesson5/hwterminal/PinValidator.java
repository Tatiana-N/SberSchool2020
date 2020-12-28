package org.nta.lessons.lesson5.hwterminal;

import java.util.Scanner;

public class PinValidator {
  private final int pin;
  private final BankAccount bankAccount;
  private int[] pinDigits;

  public PinValidator(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
    this.pin = bankAccount.getPin();
    pinDigits = Integer.toString(pin).chars().map(c -> c - '0').toArray();
  }



  boolean checkPinCode(PinValidator pinValidator,Scanner scanner) {
    int numberOfTry = 0;
    int countPinCodeDigits = 0;
      System.out.println("Ввод осуществляется по одной цифре");
      while (numberOfTry < 3) {
        while (countPinCodeDigits < 4) {
          try {
            if (Integer.parseInt(scanner.nextLine()) == pinDigits[countPinCodeDigits]) {
              countPinCodeDigits++;
            } else {
              System.out.println("Введен неверный пин-код");
              numberOfTry++; countPinCodeDigits = 0;
              break;
            }
          } catch (NumberFormatException e) {
            System.out.println("Введена не цифра");
          }
      }
        if (countPinCodeDigits == 4) {
          return true;
        }
      }
      if (countPinCodeDigits != 4) {
        bankAccount.setAccountIsBlocked(true);
      }
  return false;}
}

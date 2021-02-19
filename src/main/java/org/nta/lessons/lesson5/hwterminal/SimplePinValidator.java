package org.nta.lessons.lesson5.hwterminal;

import org.nta.lessons.lesson5.hwterminal.api.PinValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class SimplePinValidator implements PinValidator {
  private int pin;
  @Autowired
  private BankAccount bankAccount;

  public void setBankAccount(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
    this.pin = bankAccount.getPin();
    pinDigits = Integer.toString(pin).chars().map(c -> c - '0').toArray();
  }

  private int[] pinDigits;

  public SimplePinValidator(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
    this.pin = bankAccount.getPin();
    pinDigits = Integer.toString(pin).chars().map(c -> c - '0').toArray();
  }

  public SimplePinValidator() {
  }

  @Override
  public boolean checkPinCode(Scanner scanner) {
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
            numberOfTry++;
            countPinCodeDigits = 0;
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
    bankAccount.setAccountIsBlocked(true);
    return false;
  }
}

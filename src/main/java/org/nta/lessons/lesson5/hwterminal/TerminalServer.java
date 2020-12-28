package org.nta.lessons.lesson5.hwterminal;

import org.nta.lessons.lesson5.MyExceptions.AccountIsLockedException;
import org.nta.lessons.lesson5.MyExceptions.IncorrectAmountOfMoney;
import org.nta.lessons.lesson5.MyExceptions.NotEnoughMoneyException;

import java.util.Scanner;

public class TerminalServer {
  BankAccount bankAccount;
  boolean connectWithAccount = false;
  boolean initializationIsOk = false;

  public TerminalServer(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
    initializationOfAccount();
  }

  public boolean tryConnecting() {
    try {
      connectWithAccount = !bankAccount.isAccountIsBlocked();
    } catch (AccountIsLockedException e) {
      connectWithAccount = false;
      System.out.println(e.getMessage());
    }
    return connectWithAccount;
  }

  public void initializationOfAccount() {
    if (tryConnecting()) {
      if (!initializationIsOk) {
        PinValidator pinValidator = new PinValidator(bankAccount);
        if (pinValidator.checkPinCode(pinValidator, new Scanner(System.in))) {
          initializationIsOk = true;
        }
      }
    }
  }

  double getBalance() {
    if(!initializationIsOk){
      initializationOfAccount();//проверка соединения
    }
    if (connectWithAccount && initializationIsOk) {
      return bankAccount.getBalance();
    }
    return 0;
  }

  boolean putMoney(double money) {
    if(!initializationIsOk){
      initializationOfAccount();//проверка соединения
    }
    if(initializationIsOk && connectWithAccount){
     try {
       return bankAccount.addBalance(money);
     } catch (IncorrectAmountOfMoney e){
       // e.printStackTrace();
       System.out.println(e.getMessage());
     }
    }
    return false;
  }

  boolean getMoney(double money) {
    if(!initializationIsOk){
      initializationOfAccount();//проверка соединения
    }
    if(initializationIsOk && connectWithAccount){
      try {
        return bankAccount.getMoneyFromBalance(money);
      } catch (NotEnoughMoneyException | IncorrectAmountOfMoney e) {
        // e.printStackTrace();
        System.out.println(e.getMessage());
      }
    }
    return false;
  }
}

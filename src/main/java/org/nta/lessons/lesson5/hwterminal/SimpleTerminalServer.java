package org.nta.lessons.lesson5.hwterminal;

import lombok.Getter;
import lombok.Setter;
import org.nta.lessons.lesson5.MyExceptions.AccountIsLockedException;
import org.nta.lessons.lesson5.MyExceptions.IncorrectAmountOfMoney;
import org.nta.lessons.lesson5.MyExceptions.NotEnoughMoneyException;
import org.nta.lessons.lesson5.hwterminal.api.PinValidator;
import org.nta.lessons.lesson5.hwterminal.api.TerminalServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
@Getter
@Setter
public class SimpleTerminalServer implements TerminalServer {
  @Autowired
  BankAccount bankAccount;
  boolean connectWithAccount = false;
  boolean initializationIsOk = false;

  public SimpleTerminalServer(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  public SimpleTerminalServer() {
  }


  public void start() {
    initializationOfAccount();
  }

  @Override
  public boolean tryConnecting() {
    try {
      connectWithAccount = !bankAccount.isAccountIsBlocked();
    } catch (AccountIsLockedException e) {
      connectWithAccount = false;
      System.out.println(e.getMessage());
    }
    return connectWithAccount;
  }

  @Override
  public void initializationOfAccount() {
    if (tryConnecting()) {
      if (!initializationIsOk) {
        PinValidator pinValidator = new SimplePinValidator(bankAccount);
        if (pinValidator.checkPinCode(new Scanner(System.in))) {
          initializationIsOk = true;
        }
      }
    }
  }

  @Override
  public double getBalance() {
    if (!initializationIsOk) {
      initializationOfAccount();//проверка соединения
    }
    if (connectWithAccount && initializationIsOk) {
      System.out.println("Ваш балланс составляет : ");
      return bankAccount.getBalance();
    }
    return 0;
  }

  @Override
  public boolean putMoney(double money) {
    if (!initializationIsOk) {
      initializationOfAccount();//проверка соединения
    }
    if (initializationIsOk && connectWithAccount) {
      try {
        return bankAccount.addBalance(money);
      } catch (IncorrectAmountOfMoney e) {
        // e.printStackTrace();
        System.out.println(e.getMessage());
      }
    }
    return false;
  }

  @Override
  public boolean getMoney(double money) {
    if (!initializationIsOk) {
      initializationOfAccount();//проверка соединения
    }
    if (initializationIsOk && connectWithAccount) {
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

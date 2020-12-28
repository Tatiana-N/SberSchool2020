package org.nta.lessons.lesson5.hwterminal;


import org.nta.lessons.lesson5.MyExceptions.AccountIsLockedException;
import org.nta.lessons.lesson5.MyExceptions.IncorrectAmountOfMoney;
import org.nta.lessons.lesson5.MyExceptions.NotEnoughMoneyException;

public class BankAccount {
  private String accountOwner;
  private double balance;
  private int pin;
  private boolean accountIsBlocked = false;
  private int timer;

  public BankAccount(String accountOwner, double balance, int pin) {
    this.accountOwner = accountOwner;
    this.balance = balance;
    this.pin = pin;
  }

  public boolean addBalance(double money) throws IncorrectAmountOfMoney {
    if (money % 100 == 0) {
      this.balance += money;
      return true;
    } else {
      throw new IncorrectAmountOfMoney("Некорректная сумма");
    }
  }

  public boolean getMoneyFromBalance(double money) throws NotEnoughMoneyException, IncorrectAmountOfMoney {
    if (money % 100 == 0) {
      if (balance >= money) {
        this.balance -= money;
        return true;
      }
      throw new NotEnoughMoneyException("На Вашем счёте недостаточно средств");
    } else {
      throw new IncorrectAmountOfMoney("Некорректная сумма");
    }
  }


  public String getAccountOwner() {
    return accountOwner;
  }

  public double getBalance() {
    return balance;
  }

  public int getPin() {
    return pin;
  }

  public void setPin(int pin) {
    this.pin = pin;
  }

  public boolean isAccountIsBlocked() throws AccountIsLockedException {
    if (accountIsBlocked) {
      throw new AccountIsLockedException("Аккаунт заблокирован еще " + timer + "сек");
    }
    return false;
  }

  public void setAccountIsBlocked(boolean accountIsBlocked) {
    this.accountIsBlocked = accountIsBlocked;
    if (accountIsBlocked) {
      timer = 10;
      Blocker blocker = new Blocker(this);
      Thread blockerThread = new Thread(blocker);
      blockerThread.start();
    }
  }

  class Blocker implements Runnable {
    BankAccount bankAccount;

    public Blocker(BankAccount bankAccount) {
      this.bankAccount = bankAccount;
    }


    @Override
    public void run() {
      bankAccount.timer = 10;
      while (timer > 0) {
        try {
          Thread.sleep(1000);
          bankAccount.timer--;
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      bankAccount.setAccountIsBlocked(false);
    }
  }
}

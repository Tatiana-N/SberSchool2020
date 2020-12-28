package org.nta.lessons.lesson5.test;

import org.nta.lessons.lesson5.hwterminal.BankAccount;
import org.nta.lessons.lesson5.hwterminal.TerminalImpl;
import org.nta.lessons.lesson5.hwterminal.TerminalServer;

import java.util.Scanner;

public class TestTerminalWork {
  public static void main(String[] args) throws InterruptedException {
    BankAccount bankAccount1 = new BankAccount("Tom", 3000, 1234);
    BankAccount bankAccount2 = new BankAccount("Diana", 300, 8765);
    BankAccount bankAccount3 = new BankAccount("Tom", 20000, 4289);
    Scanner scanner1 = new Scanner(System.in);
    TerminalImpl terminal = new TerminalImpl(new TerminalServer(bankAccount1));
    terminal.checkAccount();
    Thread.sleep(2000);
//    System.out.println("");
   System.out.println(terminal.checkAccount());
//    BankAccount bankAccount19 = new BankAccount("Tom", 3000, 1234);
//    terminal.putMoney(200);
//   Thread.sleep(2000);
//    System.out.println(terminal.checkAccount());
    Thread.sleep(2000);
    terminal.withdrawMoney(300);
 //   System.out.println(terminal.checkAccount());
    Thread.sleep(5000);
    terminal.withdrawMoney(3000);
    Thread.sleep(2000);
   System.out.println(terminal.checkAccount());
//    Scanner scanner2 = new Scanner(System.in);
//    TerminalImpl terminal2 = new TerminalImpl(new TerminalServer(bankAccount2));
//    terminal2.putMoney(20);
//    TerminalImpl terminal3 = new TerminalImpl(new TerminalServer(bankAccount3));
//    terminal2.withdrawMoney(20);
  }
}

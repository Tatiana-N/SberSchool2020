package org.nta.lessons.lesson5.test;

import org.junit.Test;
import org.nta.lessons.lesson5.hwterminal.BankAccount;
import org.nta.lessons.lesson5.hwterminal.TerminalImpl;
import org.nta.lessons.lesson5.hwterminal.TerminalServer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;


public class JunitTest {
  @Test
  public void getAccount() throws InterruptedException {
    BankAccount bankAccount1 = new BankAccount("Tom", 3000, 1234);
    String pin = "2\n2\n2\n";
    InputStream is = new ByteArrayInputStream(pin.getBytes());
    System.setIn(is);
    TerminalImpl terminal = new TerminalImpl(new TerminalServer(bankAccount1));
    terminal.putMoney(200);
    Thread.sleep(5000);
    terminal.withdrawMoney(300);
    Thread.sleep(5000);
    String pin2 = "1\n2\n3\n4\n";
    InputStream is2 = new ByteArrayInputStream(pin2.getBytes());
    System.setIn(is2);
    System.out.println(terminal.checkAccount());
  }

  @Test
  public void putMoney() {

    BankAccount bankAccount1 = new BankAccount("Tom", 3000, 1234);
    String pin = "1\n2\n3\n4\n";
    InputStream is = new ByteArrayInputStream(pin.getBytes());
    System.setIn(is);
    TerminalImpl terminal = new TerminalImpl(new TerminalServer(bankAccount1));
    assertEquals(terminal.checkAccount(), 3000.0, 0.0);
    terminal.putMoney(200);
    assertEquals(terminal.checkAccount(), 3200.0, 0.0);
    System.out.println("Внесение некорректной суммы");
    terminal.putMoney(99);
    assertEquals(terminal.checkAccount(), 3200.0, 0.0);

  }

  @Test
  public void getMoney() {

    BankAccount bankAccount1 = new BankAccount("Tom", 3000, 1234);
    String pin = "1\n2\n3\n4\n";
    InputStream is = new ByteArrayInputStream(pin.getBytes());
    System.setIn(is);
    TerminalImpl terminal = new TerminalImpl(new TerminalServer(bankAccount1));
    assertEquals(terminal.checkAccount(), 3000.0, 0.0);
    System.out.println("Успешное снятие суммы");
    terminal.withdrawMoney(200);
    assertEquals(terminal.checkAccount(), 2800.0, 0.0);
    System.out.println("Снятие некорректной суммы");
    terminal.withdrawMoney(20);
    assertEquals(terminal.checkAccount(), 2800.0, 0.0);
    System.out.println("Снятие суммы превышающей баланс");
    terminal.withdrawMoney(3000);
    assertEquals(terminal.checkAccount(), 2800.0, 0.0);

  }
}

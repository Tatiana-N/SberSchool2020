package org.nta.lessons.lesson17.spring;

import org.junit.Test;
import org.nta.lessons.lesson17.spring.Config.Config;
import org.nta.lessons.lesson17.spring.ConfigAvto.ConfigAvto;
import org.nta.lessons.lesson5.hwterminal.TerminalImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class TestTerminalWithSpring {


  @Test
  public void getAccount() throws InterruptedException {
    ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
    // ApplicationContext context = new GenericXmlApplicationContext("config.xml");
    //ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    TerminalImpl terminal = context.getBean(TerminalImpl.class);
    String pin = "2\n2\n2\n";
    InputStream is = new ByteArrayInputStream(pin.getBytes());
    System.setIn(is);
    terminal.putMoney(200);
    Thread.sleep(5000);
    terminal.withdrawMoney(300);
    Thread.sleep(6000);
    String pin2 = "1\n2\n3\n4\n";
    InputStream is2 = new ByteArrayInputStream(pin2.getBytes());
    System.setIn(is2);
    System.out.println(terminal.checkAccount());
    assertEquals(terminal.checkAccount(), 3000.0, 0.0);
  }

  @Test
  public void putMoney() {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    TerminalImpl terminal = context.getBean(TerminalImpl.class);
    String pin = "1\n2\n3\n4\n";
    InputStream is = new ByteArrayInputStream(pin.getBytes());
    System.setIn(is);
    assertEquals(terminal.checkAccount(), 3000.0, 0.0);
    terminal.putMoney(200);
    assertEquals(terminal.checkAccount(), 3200.0, 0.0);
    System.out.println("Внесение некорректной суммы");
    terminal.putMoney(99);
    assertEquals(terminal.checkAccount(), 3200.0, 0.0);
  }

  @Test
  public void getMoney() {
    ApplicationContext context = new AnnotationConfigApplicationContext(ConfigAvto.class);
    // ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
    TerminalImpl terminal = context.getBean(TerminalImpl.class);
    String pin = "1\n2\n3\n4\n";
    InputStream is = new ByteArrayInputStream(pin.getBytes());
    System.setIn(is);
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

package ru.sbrf.jschool.multithread.part1.hw;


import org.nta.lessons.lesson6.calc.CalculatorImpl;
import org.nta.lessons.lesson6.metric.Metric;

import java.math.BigDecimal;

public class RunF implements Runnable {
  Integer num;



  public void countFactorial(int a) {
    CalculatorImpl calc = new CalculatorImpl();
//    try {
//      Thread.sleep(1000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    System.out.println(Thread.currentThread().getName() + ": " + calc.countFactorial(BigDecimal.valueOf(a)));
  }


  public RunF(Integer num) {
    this.num = num;
  }
  @Override
  public void run() {
      countFactorial(num);
  }
}
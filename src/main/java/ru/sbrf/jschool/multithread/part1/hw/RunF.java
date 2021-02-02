package ru.sbrf.jschool.multithread.part1.hw;


import org.nta.lessons.lesson12.FailedMethodCall;
import org.nta.lessons.lesson6.calc.CacheAndMetricProxy;
import org.nta.lessons.lesson6.calc.Calculator;
import org.nta.lessons.lesson6.calc.CalculatorImpl;
import org.nta.lessons.lesson6.calc.PerformanceProxy;
import org.nta.lessons.lesson6.metric.Metric;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.List;

public class RunF implements Runnable {
  Integer num;


  public void countFactorial(int a) {
   // CalculatorImpl delegate = new CalculatorImpl();
    if(a<0){throw new FailedMethodCall();}
    CalculatorImpl calculator = new CalculatorImpl();
  //  Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), delegate.getClass().getInterfaces(), new CacheAndMetricProxy(delegate));

//    try {
//      Thread.sleep(1000);
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
    System.out.println(Thread.currentThread().getName() + ": factorial "+ a + " = " + calculator.countFactorial(BigDecimal.valueOf(a)));
  }


  public RunF(Integer num) {
    this.num = num;
  }
  @Override
  public void run() {
      countFactorial(num);
  }
}

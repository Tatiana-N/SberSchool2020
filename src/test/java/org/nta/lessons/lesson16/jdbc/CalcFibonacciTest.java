package org.nta.lessons.lesson16.jdbc;

import org.junit.Test;
import org.nta.lessons.lesson6.calc.Calculator;

public class CalcFibonacciTest{
@Test
  public void test(){
  Calculator calculator = new CalcFibonacci();
  calculator.fibonacci(10);
}
}
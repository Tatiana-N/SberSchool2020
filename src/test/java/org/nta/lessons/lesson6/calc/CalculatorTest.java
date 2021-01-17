package org.nta.lessons.lesson6.calc;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.math.BigDecimal.*;

public class CalculatorTest {
  Calculator calculator = new CalculatorImpl();

  @Test
  public void testCountSum() {
    Assert.assertEquals(calculator.count(BigDecimal.valueOf(1), BigDecimal.valueOf(3), "+"), BigDecimal.valueOf(4));
  }

  @Test
  public void testCountDivision() {
    BigDecimal res = BigDecimal.ONE.divide(BigDecimal.valueOf(3), 20, BigDecimal.ROUND_CEILING);
    Assert.assertEquals(calculator.count(BigDecimal.valueOf(1), BigDecimal.valueOf(3), "/"), res);
  }

  @Test
  public void testCountMultiplication() {
    Assert.assertEquals(calculator.count(BigDecimal.valueOf(1), BigDecimal.valueOf(3), "*"), BigDecimal.valueOf(3));
  }

  @Test
  public void testCountDifference() {
    Assert.assertEquals(calculator.count(BigDecimal.valueOf(1), BigDecimal.valueOf(3), "-"), BigDecimal.valueOf(-2));
  }

  @Test
  public void testCountFactorial() {
    Assert.assertEquals(calculator.countFactorial(BigDecimal.valueOf(15)), BigDecimal.valueOf(1307674368000L));
  }

  @Test
  public void testCacheAndMetricAnnotation() {
    Calculator delegate = new CalculatorImpl();
    Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), delegate.getClass().getInterfaces(), new CacheAndMetricProxy(delegate));
    BigDecimal b = calculator.countFactorial(BigDecimal.valueOf(100)).setScale(-40, RoundingMode.HALF_UP)
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(100000000000000L))
      .setScale(0, ROUND_DOWN);

    Assert.assertEquals(b, BigDecimal.valueOf(933262154439441526L));
    Assert.assertEquals(calculator.countFactorial(BigDecimal.valueOf(200))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(100000000000000L))

        .setScale(0, ROUND_DOWN)
      , BigDecimal.valueOf(7886578673647905035L));
    Assert.assertEquals(calculator.countFactorial(BigDecimal.valueOf(200))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(100000000000000L))

      .setScale(0, ROUND_DOWN), BigDecimal.valueOf(7886578673647905035L));

  }

  @Test
  public void testMetricAnnotation() {
    Calculator delegate = new CalculatorImpl();
    Calculator calculator = (Calculator) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), delegate.getClass().getInterfaces(), new PerformanceProxy(delegate));
    BigDecimal b = calculator.countFactorial(BigDecimal.valueOf(100)).setScale(-40, RoundingMode.HALF_UP)
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(100000000000000L))
      .setScale(0, ROUND_DOWN);

    Assert.assertEquals(b, BigDecimal.valueOf(933262154439441526L));
    Assert.assertEquals(calculator.countFactorial(BigDecimal.valueOf(200))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
        .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(100000000000000L))

        .setScale(0, ROUND_DOWN)
      , BigDecimal.valueOf(7886578673647905035L));
    Assert.assertEquals(calculator.countFactorial(BigDecimal.valueOf(200))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(1000000000000000000L))
      .divide(BigDecimal.valueOf(1000000000000000000L)).divide(BigDecimal.valueOf(100000000000000L))

      .setScale(0, ROUND_DOWN), BigDecimal.valueOf(7886578673647905035L));


  }
}
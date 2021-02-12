package org.nta.lessons.lesson6.calc;

import org.nta.lessons.lesson16.jdbc.Cacheable;
import org.nta.lessons.lesson16.jdbc.MySQLDB;
import org.nta.lessons.lesson6.metric.Metric;
import org.nta.lessons.lesson8.Cache;
import org.nta.lessons.lesson8.This;

import java.math.BigDecimal;
import java.util.List;

public interface Calculator {
  BigDecimal count(BigDecimal firstNumber, BigDecimal secondNumber, String sign);

  @Cache
  @Metric
  BigDecimal countFactorial(@This BigDecimal firstNumber);

  @Cacheable(db = MySQLDB.class)
  default List<Integer> fibonacci(int n) {
    return null;
  }
}

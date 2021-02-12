package org.nta.lessons.lesson16.jdbc;

import org.nta.lessons.lesson6.calc.CalculatorImpl;

import java.util.List;
import java.util.stream.Collectors;

public class CalcFibonacci extends CalculatorImpl {
  List<Integer> list;

  public CalcFibonacci() {
    this.list = LoadFromDB.load();
  }

  @Override
  public List<Integer> fibonacci(int n) {
    if (list.size() > n) {
      return list.stream().limit(n).collect(Collectors.toList());
    }
    while (list.size() <= n) {
      countNext(list.size());
    }
    AddToDB.saveValues(list);
    return list;
  }

  public void countNext(int n) {
    System.out.println("считаем число Фибоначчи под номером " + n);
    if (n == 0) {
      list.add(1);
    }
    if (n == 1) {
      list.add(1);
    }
    if (n > 1) {
      list.add(list.get(n - 1) + list.get(n - 2));
    }
  }
}

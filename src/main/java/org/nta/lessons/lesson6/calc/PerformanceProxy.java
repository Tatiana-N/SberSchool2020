package org.nta.lessons.lesson6.calc;

import org.nta.lessons.lesson6.metric.Metric;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Date;

public class PerformanceProxy implements InvocationHandler {
  private final Object delegate;
  private long time;


  public PerformanceProxy(Object delegate) {
    this.delegate = delegate;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (!method.isAnnotationPresent(Metric.class)) {
      return method.invoke(delegate, args);
    }
    Date date = new Date();
    Object invoke = method.invoke(delegate, args);
    time = new Date().getTime() - date.getTime();
    System.out.println(time);
    return invoke;
  }
}

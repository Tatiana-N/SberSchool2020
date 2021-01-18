package org.nta.lessons.lesson6.calc;

import org.nta.lessons.lesson6.metric.Metric;
import org.nta.lessons.lesson8.CachedInvocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class CacheAndMetricProxy extends CachedInvocationHandler implements InvocationHandler {
  private final Object delegate;
  private long time;


  public CacheAndMetricProxy(Object delegate) {
    super(delegate);
    this.delegate = delegate;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
    if (!method.isAnnotationPresent(Metric.class)) {
      return super.invoke(proxy, method, args);
    }
    long startTime = System.nanoTime();

    Object invoke = super.invoke(proxy, method, args);
    long endTime = System.nanoTime();
    time = endTime - startTime;
    System.out.println("Время работы метода: " + time + " наносек.");
    return invoke;
  }
}

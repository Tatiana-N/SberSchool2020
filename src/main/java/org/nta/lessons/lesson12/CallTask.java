package org.nta.lessons.lesson12;

import java.util.concurrent.Callable;

public class CallTask<T> implements Callable<T> {
  T object;

  public CallTask(T object) {
    this.object = object;
  }

  @Override
  public T call() throws Exception {
    System.out.println(Thread.currentThread().getName());
    Thread.sleep(2000);
    return object;
  }
}

package org.nta.lessons.lesson12.Task;

import org.nta.lessons.lesson12.FailedMethodCall;

import java.util.concurrent.Callable;

public class CallTask<T> implements Callable<T> {
  T object;

  public void putTask(T object) {
    this.object = object;
  }

  @Override
  public T call() throws FailedMethodCall {
    System.out.println(Thread.currentThread().getName() + " выполняет код в методе call");
    if (object.toString().length() > 20) {
      throw new FailedMethodCall();
    }
    // Thread.sleep(1000);
    return object;
  }
}

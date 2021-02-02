package org.nta.lessons.lesson12;

import java.util.concurrent.Callable;

public class CallTask<T> implements Callable<T> {
  T object;

  public CallTask() {
  }

  public void putTask(T object) {
    this.object = object;
  }

  @Override
  public T call() throws FailedMethodCall {
    System.out.println(Thread.currentThread().getName());
    if(object.hashCode()>0){
      throw new FailedMethodCall("метод завершен с ошибкой");
    }
    // Thread.sleep(1000);
    return object;
  }
}

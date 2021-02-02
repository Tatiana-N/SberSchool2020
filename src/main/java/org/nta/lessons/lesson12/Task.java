package org.nta.lessons.lesson12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Task<T> {

  private T value;
  final Callable<? extends T> callable;

  public Task(Callable<? extends T> callable) {
    this.callable = callable;
  }

  public T get() throws Exception {
    T temp = this.value;
    if (temp == null) {
      synchronized (callable) {
        if (this.value == null) {
          try {
            System.out.println(" вход в метод get -> " + Thread.currentThread().getName());
            Thread.sleep(2000);
            this.value = temp = callable.call();
            System.out.println("нужное значение получено \n "
                + " выполняется остальная работа в методе  -> "
                + Thread.currentThread().getName());
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " <- вышла из get");
          } catch (RuntimeException E) {
            throw new RuntimeException("Новое");
          }
        }
      }
    }
    return temp;
  }
}

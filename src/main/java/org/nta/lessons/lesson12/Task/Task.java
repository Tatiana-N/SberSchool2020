package org.nta.lessons.lesson12.Task;

import org.nta.lessons.lesson12.FailedMethodCall;

import java.util.concurrent.Callable;


public class Task<T> {
  private FailedMethodCall myException;
  private volatile T value;
  final Callable<? extends T> callable;

  public Task(Callable<? extends T> callable) {
    this.callable = callable;
  }

  public T get() throws FailedMethodCall {
    if (this.value == null) {
      if (myException == null) {
        synchronized (callable) {
          if (this.value == null) {
            if (myException == null) {
              System.out.println(" вход в метод get -> " + Thread.currentThread().getName());
              // sleep чтобы несколько потоков зависло на synchronized
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              try {
                this.value = callable.call();
              } catch (Exception e) {
                if (e instanceof FailedMethodCall) {
                  myException = (FailedMethodCall) e;
                  throw new FailedMethodCall();
                }
                e.printStackTrace();
              }
              System.out.println("нужное значение получено \n "
                + " выполняется остальная работа в методе  -> "
                + Thread.currentThread().getName());
              // sleep чтобы проверить что потоки которые пришли позже сразу получают значение
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
              System.out.println(Thread.currentThread().getName() + " <- вышла из get");
            }
          }
        }
      }
    }
    if (myException != null) {
      throw new FailedMethodCall();
    }
    return this.value;
  }
}

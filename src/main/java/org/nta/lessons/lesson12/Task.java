package org.nta.lessons.lesson12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;


public class Task<T> {
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_RESET = "\u001B[0m";
 // static Object lock = new Object();
  private T value;
  Callable<? extends T> callable;

  public Task(Callable<? extends T> callable) {
    this.callable = callable;

  }


  public T get() throws Exception {
    synchronized (Task.class) {
      T temp = this.value;
      if (temp == null) {
        synchronized (Task.class) {
          if (this.value == null) {
            try {
              this.value = temp = callable.call();
          } catch (RuntimeException E){
              throw new RuntimeException("Новое");
            }}
        }
      }
      return temp;
    }
  }

  public static void main(String[] args) throws Exception {
    List<Integer> list = new ArrayList<>();
    Thread thread1 = new Thread(() ->  {
        Task<Integer> task = new Task<>(new CallTask<>(1));
        try {
          System.out.println(task.get());
        } catch (Exception e) {
          e.printStackTrace();
        }
    });
    Thread thread2 = new Thread(() -> {
        Task<Integer> task = new Task<>(new CallTask<>(2));
        try {
          System.out.println(task.get());
        } catch (Exception e) {
          e.printStackTrace();
        }
    });
    Thread thread3 = new Thread(() -> {
        Task<Integer> task = new Task<>(new CallTask<>(3));
        try {
          System.out.println(task.get());
        } catch (Exception e) {
          e.printStackTrace();
        }
    });
    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
      System.out.println(ANSI_PURPLE + thread1.getState() +  " " + thread2.getState() + " " + thread3.getState());
      System.out.println(ANSI_RESET);

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }}
    });
    serviceThread.setDaemon(true);
    serviceThread.start();
    thread1.start();
    thread2.start();
    thread3.start();


    Thread.sleep(4000);


}
}

package org.nta.lessons.lesson12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Task<T> {
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_RESET = "\u001B[0m";
  // private Object lock = new Object();
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
            //Thread.sleep(2000);
            this.value = temp = callable.call();
            System.out.println("метод get захвачен " + Thread.currentThread().getName());
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " вышла из get");
          } catch (RuntimeException E) {
            throw new RuntimeException("Новое");
          }
        }
      }
    }
    return temp;
  }


  public static void main(String[] args) throws Exception {
    List<Thread> threads = new ArrayList<>();
    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        System.out.println(ANSI_PURPLE);
        threads.forEach(t -> System.out.print(t.getState() + " "));
        //System.out.println(ANSI_PURPLE + " " + thread2.getState() + " " + thread3.getState());
        System.out.println(ANSI_RESET);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    serviceThread.setDaemon(true);
    // serviceThread.start();
    CallTask<Integer> integerCallTask = new CallTask<>();
    Task<Integer> task = new Task<>(integerCallTask);
    integerCallTask.putTask(1);
    Thread thread = new Thread(() -> {
      try {
        System.out.println(task.get() + " " + Thread.currentThread().getName());
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    thread.start();
    for (int i = 0; i < 6; i++) {
      Thread.sleep(400);
      Thread thread1 = new Thread(() -> {
        try {
          System.out.println(task.get() + " " + Thread.currentThread().getName());
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
      thread1.start();
      threads.add(thread1);
    }
//    Thread thread3 = new Thread(() -> {
//      try {
//        System.out.println(task.get());
//      } catch (Exception e) {
//        e.printStackTrace();
//      }
//    });

    Thread.sleep(2000);


  }
}

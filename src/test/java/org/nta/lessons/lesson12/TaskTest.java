package org.nta.lessons.lesson12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskTest {
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_RESET = "\u001B[0m";

  @Test
  public void test() throws InterruptedException {
    CallTask<Integer> integerCallTask = new CallTask<>();
    Task<Integer> task = new Task<>(integerCallTask);
    integerCallTask.putTask(1);
    Thread firstTread = new Thread(() -> {
      try {
        System.out.println(task.get() + " " + Thread.currentThread().getName());
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    firstTread.start();
    List<Thread> threads = new ArrayList<>();
    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        System.out.println(ANSI_PURPLE);
        System.out.println(firstTread.getState());
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
     serviceThread.start();

    for (int i = 0; i < 10; i++) {
      Thread.sleep(400);
      Thread thread1 = new Thread(() -> {
        try {
          System.out.println(task.get() + " " + Thread.currentThread().getName());
        } catch (Exception e) {
          e.printStackTrace();
        }
      });
      System.out.println(thread1.getName() + " created ");
      thread1.start();
      System.out.println(thread1.getName() + " запущена ");
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
package org.nta.lessons.lesson12;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class TaskTest {
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_RESET = "\u001B[0m";

  @Test
  public void test() throws InterruptedException {
    CallTask<String> integerCallTask = new CallTask<>();
    Task<String> task = new Task<>(integerCallTask);
    integerCallTask.putTask("Это наше значение ");
    // создание первой нити которая зайдет в get
    Thread firstTread = new Thread(() -> {
      System.out.println(task.get() + " " + Thread.currentThread().getName());
    });
    firstTread.start();
    List<Thread> threads = new ArrayList<>();
    /**serviceThread для контроля */
    /*
    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        System.out.println(ANSI_PURPLE);
        System.out.println(firstTread.getState());
        threads.forEach(t -> System.out.print(t.getState() + " "));
        System.out.println(ANSI_RESET);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    serviceThread.setDaemon(true);
     serviceThread.start();*/
    // создание потоков которые пойдут в след за первым в метод get
    for (int i = 0; i < 10; i++) {
      Thread.sleep(400);
      Thread thread1 = new Thread(() -> {
        System.out.println(task.get() + " " + Thread.currentThread().getName());
      });
      System.out.print(ANSI_PURPLE + thread1.getName() + " created ");
      thread1.start();
      System.out.println(thread1.getName() + " запущена " + ANSI_RESET);
      threads.add(thread1);
    }
    //дожидаемся пока все потоки закончат свою работу
    while (threads.stream().filter(t -> !t.getState().equals(Thread.State.TERMINATED)).count() > 0) {
      Thread.sleep(1000);
    }
  }

  @Test//(expected = FailedMethodCall.class)
  public void testWithExceptions() throws InterruptedException, FailedMethodCall {
    // обработка исключений
    Thread.setDefaultUncaughtExceptionHandler(
      (Thread thread, Throwable throwable) -> {
        if (throwable instanceof FailedMethodCall) {
          {
            System.out.println("---------------Перехвачено исключение из потока " + Thread.currentThread().getName() + "-------------------");
            System.out.println("-------------------------" + throwable.getMessage() + "--------------------------");
          }
        }
      });
    CallTask<String> integerCallTask = new CallTask<>();
    Task<String> task = new Task<>(integerCallTask);
    integerCallTask.putTask("Это наше значение для Exception ");
    // создание первой нити которая зайдет в get
    Thread firstTread = new Thread(() -> {
      System.out.println(task.get() + " " + Thread.currentThread().getName());
    });
    firstTread.start();
    List<Thread> threads = new ArrayList<>();
    /**serviceThread для контроля */
    /*
    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        System.out.println(ANSI_PURPLE);
        System.out.println(firstTread.getState());
        threads.forEach(t -> System.out.print(t.getState() + " "));
        System.out.println(ANSI_RESET);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    serviceThread.setDaemon(true);
     serviceThread.start();*/
    // создание потоков которые пойдут в след за первым в метод get
    for (int i = 0; i < 10; i++) {
      Thread.sleep(400);
      Thread thread1 = new Thread(() -> {
        System.out.println(task.get() + " " + Thread.currentThread().getName());
      });
      System.out.print(ANSI_PURPLE + thread1.getName() + " created ");
      thread1.start();
      System.out.println(thread1.getName() + " запущена " + ANSI_RESET);
      threads.add(thread1);
    }
    //дожидаемся пока все потоки закончат свою работу
    while (threads.stream().filter(t -> !t.getState().equals(Thread.State.TERMINATED)).count() > 0) {
      Thread.sleep(1);
    }
  }
}
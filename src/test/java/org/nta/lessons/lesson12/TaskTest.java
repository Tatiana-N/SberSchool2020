package org.nta.lessons.lesson12;

import org.junit.Test;
import org.nta.lessons.lesson12.hw1.CallTask;
import org.nta.lessons.lesson12.hw1.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskTest {
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_YELLOW = "\u001B[33m";

  @Test
  public void test() throws InterruptedException {
    CallTask<String> stringCallTask = new CallTask<>();
    Task<String> task = new Task<>(stringCallTask);
    stringCallTask.putTask("Это наше значение ");
    CallTask<Integer> integerCallTask = new CallTask<>();
    Task<Integer> taskInteger = new Task<>(integerCallTask);
    integerCallTask.putTask(12);
    // создание первой нити которая зайдет в get
    Thread firstTread = new Thread(() -> System.out.println(task.get() + " " + Thread.currentThread().getName()));
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
      Thread thread1 = new Thread(() -> System.out.println(task.get() + " " + Thread.currentThread().getName()));
      System.out.print(ANSI_PURPLE + thread1.getName() + " created ");
      thread1.start();
      System.out.println(thread1.getName() + " запущена " + ANSI_RESET);
      threads.add(thread1);
      if (i < 2 || i > 8) {
        Thread thread2 = new Thread(() ->
          System.out.println(ANSI_YELLOW + taskInteger.get() + " " + Thread.currentThread().getName() + ANSI_RESET));
        System.out.print(ANSI_YELLOW + thread2.getName() + " created ");
        thread2.start();
        System.out.println(thread2.getName() + " запущена " + ANSI_RESET);
        threads.add(thread2);
      }
    }
    //дожидаемся пока все потоки закончат свою работу
    while (threads.stream().anyMatch(t -> !t.getState().equals(Thread.State.TERMINATED))) {
      Thread.sleep(1000);
    }
  }

  @Test//(expected = FailedMethodCall.class)
  public void testWithExceptions() throws InterruptedException {
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
    CallTask<String> stringCallTask = new CallTask<>();
    Task<String> task = new Task<>(stringCallTask);
    stringCallTask.putTask("Это наше значение для Exception ");
    CallTask<Integer> integerCallTask = new CallTask<>();
    Task<Integer> taskInteger = new Task<>(integerCallTask);
    integerCallTask.putTask(12);
    // создание первой нити которая зайдет в get
    Thread firstTread = new Thread(() -> System.out.println(task.get() + " " + Thread.currentThread().getName()));
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
      Thread thread1 = new Thread(() -> System.out.println(task.get() + " " + Thread.currentThread().getName()));
      System.out.print(ANSI_PURPLE + thread1.getName() + " created ");
      thread1.start();
      System.out.println(thread1.getName() + " запущена " + ANSI_RESET);
      threads.add(thread1);
      if (i < 2) {
        Thread thread2 = new Thread(() -> System.out.println(taskInteger.get() + " " + Thread.currentThread().getName()));
        System.out.print(ANSI_PURPLE + thread2.getName() + " created ");
        thread2.start();
        System.out.println(thread2.getName() + " запущена " + ANSI_RESET);
        threads.add(thread2);
      }
    }
    //дожидаемся пока все потоки закончат свою работу
    while (threads.stream().anyMatch(t -> !t.getState().equals(Thread.State.TERMINATED))) {
      Thread.sleep(1);
    }
  }
}
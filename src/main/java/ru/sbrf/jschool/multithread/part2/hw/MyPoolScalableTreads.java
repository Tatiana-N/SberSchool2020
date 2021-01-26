package ru.sbrf.jschool.multithread.part2.hw;


import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class MyPoolScalableTreads implements MyPool {
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_RESET = "\u001B[0m";
  private final Set<Thread> myThreads;
  private int min;
  private final int max;
  static private int timeRemoving = 1000;
//конструкторы

  public MyPoolScalableTreads() {
    this(1, 200);
  }

  public MyPoolScalableTreads(int min) {
    this(min, 200);
  }

  public MyPoolScalableTreads(int min, int max) {
    this.myThreads = new HashSet<>(min);
    this.min = min;
    this.max = max;
  }

  public Set<Thread> getMyThreads() {
    return myThreads;
  }

  public void setTimeRemoving(int timeRemoving) {
    this.timeRemoving = timeRemoving;
  }

  // создание min Threads
  private void start(int min) {
    for (int i = 0; i < min; i++) {
      if (max > myThreads.size()) {
        MyThread myThread = new MyThread();
        myThread.start();
        myThreads.add(myThread);
      }
    }
  }

  public void start() {
    removing(timeRemoving); // если есть TERMINATED потоки -> удалять
    if (min == 0) {
      min = max;
    }
    start(min);
  }

  //принудительная остановка MyPool
  public void stop() {
    for (Thread myThread : myThreads) {
      myThread.interrupt();
    }
  }

  public void execute(Queue<Runnable> queue) {
    while (!queue.isEmpty()) {
      execute(queue.poll());
    }
  }

  public void execute(Runnable task) {
    MyThread.tasks.add(task);
    if (myThreads.size() < MyThread.tasks.size()) {
      start(MyThread.tasks.size());
    }
  }

  @Override
  public void join() {
    while (!MyThread.tasks.isEmpty()) {
    }
  }

  private void removing(int timeRemoving) {
    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        synchronized (this) {
          Set<Thread> myThreads = this.getMyThreads();
          System.out.println(ANSI_PURPLE + "Количество нитей в данный момент : " + myThreads.size());
          List<Thread> collect = myThreads.stream()
            .filter(t -> ((MyThread) t).canBeTerminate)
            .limit(myThreads.size() - min)
            .collect(Collectors.toList());
          collect.stream().forEach(t -> ((MyThread) t).running = false);
          myThreads.removeAll(myThreads.stream().filter(t -> t.getState().equals(Thread.State.TERMINATED)).collect(Collectors.toList()));
          myThreads.stream().forEach(y -> System.out.print(y.getState() + " "));
          System.out.println(ANSI_RESET);
        }
        try {
          Thread.sleep(timeRemoving);

        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    serviceThread.setDaemon(true);
    serviceThread.start();
  }
}

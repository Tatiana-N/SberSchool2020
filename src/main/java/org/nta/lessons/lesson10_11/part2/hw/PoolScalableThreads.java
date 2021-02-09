package org.nta.lessons.lesson10_11.part2.hw;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class PoolScalableThreads implements MyPool { // нормально не работает
  private static final String ANSI_PURPLE = "\u001B[35m";
  private static final String ANSI_RESET = "\u001B[0m";
  private final int minThreads;
  private final int maxThreads;
  private final Set<Thread> threads = new HashSet<>();
  private final Queue<Runnable> tasks = new LinkedBlockingQueue();
  static private int timeRemoving = 1000;
  static private int timeThreadIsDying = 6000;

  public PoolScalableThreads() {
    this(1, 200);
  }

  public PoolScalableThreads(int minThreads, int maxThreads) {
    this.minThreads = minThreads;
    this.maxThreads = maxThreads;
  }

  public PoolScalableThreads(int minThreads) {
    this(minThreads, 200);
  }

  @Override
  public Set<Thread> getMyThreads() {
    return threads;
  }

  // настройка удаления потоков
  public static void setTimeGarbageRemoving(int timeRemoving) {
    PoolScalableThreads.timeRemoving = timeRemoving;
  }
// настройка времени жизни потока
  public static void setTimeThreadIsDying(int timeThreadIsDying) {
    PoolScalableThreads.timeThreadIsDying = timeThreadIsDying;
  }

  public void start(int min) {
    for (int i = 0; i < min; i++) {
      if (threads.size() < maxThreads) {
        PoolWorker poolWorker = new PoolWorker();
        threads.add(poolWorker);
        poolWorker.start();
      }
    }
  }

  @Override // создание min Threads
  public void start() {
    removing(timeRemoving); // если есть TERMINATED потоки -> удалять
    if (minThreads == 0) {
      start(maxThreads);
    }
    start(minThreads);
  }


  @Override
  public void execute(Queue<Runnable> queue) {
    while (!queue.isEmpty()) {
      execute(queue.poll());
    }
  }

  public void execute(Runnable r) {
    synchronized (tasks) {
      tasks.add(r);
      tasks.notify();
        start(maxThreads);
      }
    }


  @Override
  public void join() {
    while (!tasks.isEmpty()) {
    }
  }

  @Override   //принудительная остановка MyPool
  public void stop() {
    for (Thread myThread : threads) {
      myThread.interrupt();
    }
  }

  private void removing(int timeRemoving) {
    Thread serviceThread = new Thread(() -> {
      while (!Thread.interrupted()) {
        synchronized (this) {
          Set<Thread> myThreads = this.getMyThreads();
          System.out.println(ANSI_PURPLE + "Количество нитей в данный момент : " + myThreads.size() + " лист заданий" + tasks.size());
          synchronized (tasks) { myThreads.stream()
            .filter(t -> ((PoolWorker) t).canBeTerminate)
              .limit(myThreads.size() - minThreads)
              .forEach(t -> t.interrupt());}
          myThreads.removeAll(myThreads.stream().filter(t -> t.getState().equals(Thread.State.TERMINATED)).collect(Collectors.toList()));
          // состояние текущих потоков
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


  private class PoolWorker extends Thread {
    private boolean running = true;
    private boolean canBeTerminate = false;
    private long lastProcess = 0;

    @Override
    protected Object clone() throws CloneNotSupportedException {
      return super.clone();
    }

    @Override
    public void interrupt() {
      running = false;
    }

    public void run() {
      Runnable r;
      while (running) {
        if ( new Date().getTime() - this.lastProcess > timeRemoving) {
          canBeTerminate = true;
        }
        synchronized (tasks) {
          while (tasks.isEmpty()) {
            try {
              tasks.wait();
            } catch (InterruptedException ignored) {
              ignored.printStackTrace();
            }
          }
          r = tasks.poll();
        }
        try {
          r.run();
          Thread.sleep(2000);
          lastProcess = new Date().getTime();
        } catch (InterruptedException | RuntimeException e) {
          e.printStackTrace();
        }

      }
    }
  }
}

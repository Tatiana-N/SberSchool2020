package ru.sbrf.jschool.multithread.part2.hw;

import java.util.Date;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyThread extends Thread {
  static final Queue<Runnable> tasks = new LinkedBlockingQueue<>();
  boolean running = true;
  boolean canBeTerminate = false;
  long lastProcess = 0;
  static private int timeRemoving = 6000;

  public MyThread() {
    this.lastProcess = new Date().getTime();
  }

  public static void setTimeRemoving(int timeRemoving) {
    MyThread.timeRemoving = timeRemoving;
  }

  public void run() {
    while (running) {
      Runnable runs = tasks.poll();
      try {
        runs.run();
        // System.out.println(Thread.currentThread().getName() + " do hard work");
        Thread.sleep(2000);
        lastProcess = new Date().getTime();
      } catch (Throwable t) {
      }
      if (new Date().getTime() - this.lastProcess > timeRemoving) {
        canBeTerminate = true;
      }
    }
  }

  @Override
  public void interrupt() {
    running = false;
  }
}



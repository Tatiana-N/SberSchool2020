package ru.sbrf.jschool.multithread.part2.hw;


import java.util.ArrayDeque;
import java.util.Queue;

public class MyThread extends Thread {
  final Queue<Runnable> tasks = new ArrayDeque<>();

  public void doThis(Runnable runs) {
    tasks.add(runs);
  }

  public void run() {
    while (true) {
      Runnable runs = tasks.poll();
      try {
        runs.run();
       // System.out.println(Thread.currentThread().getName() + " do hard work");
        Thread.sleep(2000);
      } catch (Throwable t) {
        // handles t
      }
    }
  }
}



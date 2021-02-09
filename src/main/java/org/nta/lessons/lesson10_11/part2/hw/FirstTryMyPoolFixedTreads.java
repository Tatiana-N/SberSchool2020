package org.nta.lessons.lesson10_11.part2.hw;

import java.util.*;

public class FirstTryMyPoolFixedTreads implements MyPool {
  private Set<Thread> myThreads;
  private int min;

  public FirstTryMyPoolFixedTreads() {
    new FirstTryMyPoolFixedTreads(1);
  }

  public FirstTryMyPoolFixedTreads(int min) {
    this.myThreads = new HashSet<>();
    this.min = min;
  }

  public FirstTryMyPoolFixedTreads(int min, int max) {
    this.myThreads = new HashSet<>(max);
    this.min = min;
  }

  @Override
  public Set<Thread> getMyThreads() {
    return myThreads;
  }

  public void start() {
    if (min == 0) {
      min = 5;
    }
    for (int i = 0; i < min; i++) {
      MyThread myThread = new MyThread();
      myThread.start();
      myThreads.add(myThread);
    }
  }

  public void stop() {
    for (Thread myThread : myThreads) {
      myThread.interrupt();
    }
  }

  public void execute(Queue<Runnable> queue) {
    while (!queue.isEmpty()) {
        MyThread.tasks.add(queue.poll());
    }
  }

  @Override
  public void join() {
    while (!MyThread.tasks.isEmpty()) {
    }
  }

}

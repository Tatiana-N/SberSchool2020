package org.nta.lessons.lesson10_11.part2.hw;

import java.util.Queue;
import java.util.Set;

public class MyPoolFixedTreads implements MyPool {
  private MyPoolScalableTreads myPoolScalableTreads;

  public MyPoolFixedTreads(int i) {
    if (i == 0) {
      throw new RuntimeException(" как будем работать без нитей? ");
    }
    this.myPoolScalableTreads = new MyPoolScalableTreads(i, i);
  }

  @Override
  public Set<Thread> getMyThreads() {
    return myPoolScalableTreads.getMyThreads();
  }

  @Override
  public void start() {
    myPoolScalableTreads.start();
  }

  @Override
  public void execute(Queue<Runnable> queue) {
    myPoolScalableTreads.execute(queue);
  }

  @Override
  public void join() {
    myPoolScalableTreads.join();
  }

  @Override
  public void stop() {
    myPoolScalableTreads.stop();
  }
}

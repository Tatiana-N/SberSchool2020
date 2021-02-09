package org.nta.lessons.lesson10_11.part2.hw;

import java.util.Queue;
import java.util.Set;

public interface MyPool {
  Set<Thread> getMyThreads();

  void start();

  void execute(Queue<Runnable> queue);

  void join();

  void stop();
}

package ru.sbrf.jschool.multithread.part2.hw;

import java.util.Queue;
import java.util.Set;

public interface MyPool {
  Set<Thread> getMyThreads();

  void start();

  void execute(Queue<Runnable> queue);

  void join();

  void stop();
}
